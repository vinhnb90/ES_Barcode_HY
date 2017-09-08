package esolutions.com.barcodehungyenpc.utils;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import esolutions.com.barcodehungyenpc.entity.CToPBResponse;
import esolutions.com.barcodehungyenpc.entity.ResponseSoap;
import esolutions.com.barcodehungyenpc.entity.ThongBaoResponse;

/**
 * Created by VinhNB on 8/31/2017.
 */

public class SoapXML {

    public static String URL;

    public static String getURL(String address) {
        return "http://" + address + "/servicedodem28.8/ServiceTienIch_DoDem.asmx";
//        return "http://" + address + "/ServiceTienIch_DoDem.asmx";
    }

    public static int TIME_OUT = 30000;

    public enum METHOD {
        CTO_PB("Select_Info_PBCT", new String[]{"strMaDViQLy", "strMaCTo"}),
        CTO_KD("Select_Info_Send_KDCT_MTB", new String[]{"strDVi", "strMaCTo"});

        private String nameMethod;
        private String[] nameParams;

        METHOD(String nameMethod, String[] nameParams) {
            this.nameMethod = nameMethod;
            this.nameParams = nameParams;
        }

        public String getNameMethod() {
            return nameMethod;
        }

        public String[] getNameParams() {
            return nameParams;
        }
    }

    public static class AsyncSoap<T extends ResponseSoap> extends AsyncTask<String, String, T> {

        Class<T> classType;

        //request action to eStore
        private String METHOD_NAME;
        private String URL;
        private static final String NAMESPACE = "http://tempuri.org/";
        private String SOAP_ACTION;
        private String[] METHOD_PARAM;
        private AsyncSoapCallBack callBack;

        public AsyncSoap(Class<T> type, AsyncSoapCallBack callBack, String methodName, String URL, String... nameParams) throws Exception {
            this.callBack = callBack;
            this.classType = type;

            this.METHOD_NAME = methodName;
            this.URL = URL;
            this.METHOD_PARAM = nameParams;
            this.SOAP_ACTION = NAMESPACE + METHOD_NAME;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callBack.onPre(this);
        }

        @Override
        protected T doInBackground(String... argParams) {
            //check size argParams with nameParams
            T responeObject = null;

            try {
                if (argParams.length != METHOD_PARAM.length) {
                    throw new Exception(Common.MESSAGE.ex04.getContent());
                }

                //truyền đủ các tham số tương ứng
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                for (int i = 0; i < argParams.length; i++) {
                    request.addProperty(METHOD_PARAM[i], argParams[i]);
                }

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                envelope.dotNet = true;
                envelope.implicitTypes = true;
                envelope.setAddAdornments(false);
//                envelope.addMapping(NAMESPACE, "CTO1", new CToPBResponse().getClass());

                HttpTransportSE ht;
                try {
                    ht = new HttpTransportSE(URL, TIME_OUT);
                    ht.call(SOAP_ACTION, envelope);

                    SoapObject result = (SoapObject) envelope.getResponse();

                    if (result == null)
                        throw new Exception(Common.MESSAGE.ex06.getContent());

                    HashMap<String, SoapObject> dataRealResult = callBack.filterDataReal(result);
                    if(dataRealResult == null)
                        throw new Exception(Common.MESSAGE.ex05.getContent());

                    //check and get all data by key field project and put to gson object
                    JSONObject jsonObject = new JSONObject();

                    //kiểm tra nếu key dataReal là CTO thì sẽ thao tác với classType
                    Field[] allFields;
                    final GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();


                    if (dataRealResult.containsKey("CONG_TO")) {
                        SoapObject dataReal = dataRealResult.get("CONG_TO");
                        allFields = classType.getDeclaredFields();
                        for (Field field : allFields) {
                            if (dataReal.hasProperty(field.getName())) {
                                jsonObject.accumulate(field.getName(), dataReal.getPropertyAsString(field.getName()));
                            } else {
                                jsonObject.accumulate(field.getName(), JSONObject.NULL);
                            }
                        }

                        //convert to object
                        responeObject = gson.fromJson(jsonObject.toString(), classType);
                    }

                    if (dataRealResult.containsKey("CTO")) {
                        SoapObject dataReal = dataRealResult.get("CTO");
                        allFields = classType.getDeclaredFields();
                        for (Field field : allFields) {
                            if (dataReal.hasProperty(field.getName())) {
                                jsonObject.accumulate(field.getName(), dataReal.getPropertyAsString(field.getName()));
                            } else {
                                jsonObject.accumulate(field.getName(), JSONObject.NULL);
                            }
                        }

                        //convert to object
                        responeObject = gson.fromJson(jsonObject.toString(), classType);
                    }

                    //kiểm tra nếu key dataReal là CTO thì sẽ thao tác với string  server trả về thông báo tới UI
                    if (dataRealResult.containsKey("Table1")) {
                        SoapObject dataReal = dataRealResult.get("Table1");
                        allFields = ThongBaoResponse.class.getDeclaredFields();
                        for (Field field : allFields) {
                            if (dataReal.hasProperty(field.getName())) {
                                jsonObject.accumulate(field.getName(), dataReal.getPropertyAsString(field.getName()));
                            } else {
                                jsonObject.accumulate(field.getName(), JSONObject.NULL);
                            }
                        }

                        //convert to object
                        ThongBaoResponse thongBaoResponse = null;
                        thongBaoResponse = gson.fromJson(jsonObject.toString(), ThongBaoResponse.class);

                        publishProgress(thongBaoResponse.getThongbao());
                        return null;
                    }

                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }

            } catch (Exception e) {
                publishProgress(e.getMessage());
                e.printStackTrace();
            }

            return responeObject;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String message = values[0];
            callBack.onUpdate(message);
        }

        @Override
        protected void onPostExecute(T response) {
            super.onPostExecute(response);
            if (response == null)
                return;
            callBack.onPost(response);
        }

        public static abstract class AsyncSoapCallBack<T extends ResponseSoap> {
            public abstract void onPre(final AsyncSoap soap);

            public abstract void onUpdate(String message);

            public abstract void onPost(T response);

            public abstract HashMap<String, SoapObject> filterDataReal(SoapObject response);

        }
    }

//    public class AsyncSoapCtoPB extends AsyncTask<Void, String, Boolean> {
//        //request action to eStore
//        private String METHOD_NAME = ;
//        private String NAMESPACE = "http://tempuri.org/";
//        private String URL = ENDPOINT_URL;
//        private String SOAP_ACTION = "http://tempuri.org/Check_Info_Send_KDCT_MTB";
//        private String METHOD_PARAM = "strDVi";
//        private String METHOD_PARAM_1 = "strMaCTo";
//
//        public AsyncSoapCtoPB() throws Exception {
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... voids) {
//
//            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//            request.addProperty(METHOD_PARAM, maDonVi);
//            request.addProperty(METHOD_PARAM_1, maCongTo);
//
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.setOutputSoapObject(request);
//
//            HttpTransportSE ht;
//            SoapPrimitive response = null;
//
//            try {
//                ht = new HttpTransportSE(URL, 30000);
//                ht.call(SOAP_ACTION, envelope);
//                response = (SoapPrimitive) envelope.getResponse();
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.e(TAG, "doInBackground:  " + e.getMessage());
//            }
//
//            if (response == null) {
//                publishProgress("Null trả về");
//                return null;
//            }
//
//            String filterDataReal = response.toString();
//
//            if (filterDataReal.isEmpty()) {
//                publishProgress("Dữ liệu trả về rỗng!");
//                return null;
//            }
//
//            return Boolean.parseBoolean(filterDataReal);
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            String message = values[0];
//        }
//
//        @Override
//        protected void onPostExecute(Boolean result) {
//            try {
//                super.onPostExecute(result);
//                ((ListView) MainActivity.this.findViewById(R.id.lv_1)).setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new String[]{result.toString()}));
//            } catch (Exception e) {
//                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
