package esolutions.com.barcodehungyenpc.utils;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by VinhNB on 8/31/2017.
 */

public class SoapXML {

    public static String URL;

    public static String getURL(String address) {
        return "http://" + address + "/servicedodemHungYen1509/ServiceTienIch_DoDem.asmx";
//                "http://" + address + "/servicedodem28.8/ServiceTienIch_DoDem.asmx";
//        return "http://" + address + "/ServiceTienIch_DoDem.asmx";
    }

    public static int TIME_OUT = 30000;

    public enum METHOD {
        CTO_PB("Select_Info_PBCT", new String[]{"strMaDViQLy", "strMaCTo"}),
        CTO_KD("Select_Info_Send_KDCT_MTB", new String[]{"strDVi", "strMaCTo"}),

        Select_MADVIQLY("Select_MADVIQLY", new String[]{}),
        Select_DangNhap("Select_DangNhap", new String[]{"user", "pass", "Ma_DViQLy"});

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

    public static class AsyncSoap<K, V> extends AsyncTask<String, String, String> {

//        public Class<K> getClassTypeData() {
//            return objectTypeData;
//        }
//
//        public Class<V> getClassTypeError() {
//            return objectTypeError;
//        }

        private K objectTypeData;
        private V objectTypeError;
        private Class<V> classTypeError;
        private Class<K> classTypeData;
        //request action to eStore
        private String METHOD_NAME;
        private String URL;
        private static final String NAMESPACE = "http://tempuri.org/";
        private String SOAP_ACTION;
        private String[] METHOD_PARAM;
        private String keyDataSetServerError;
        private AsyncSoapCallBack callBack;

        //giá trị phân biệt là server data hay server response
        private boolean isServerErrorResponse = false;

        public AsyncSoap(Class<K> classTypeData, Class<V> classTypeError, String keyDataSetServerError, AsyncSoapCallBack callBack, String methodName, String URL, String... nameParams) throws Exception {
            this.callBack = callBack;
//            this.objectTypeData = objectTypeData;
//            this.objectTypeError = objectTypeError;
            this.keyDataSetServerError = keyDataSetServerError;
            this.classTypeData = classTypeData;
            this.classTypeError = classTypeError;
//            objectTypeData = classTypeData.newInstance();
//            objectTypeError = classTypeError.newInstance();
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
        protected String doInBackground(String... argParams) {
            //check size argParams with nameParams
            String jsonResponse = "";

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

                    SoapObject result = null;
                    SoapPrimitive primitive = null;
//                    int field = classTypeData.getDeclaredFields().length;
//                    if (field == 2) {
////                        SoapFault soapFault = (SoapFault) envelope.bodyIn;
//                        primitive = (SoapPrimitive) envelope.getResponse();
//                        Object a =
//                        jsonResponse = convertDataSoapPrimitive(primitive);
//                    } else {
//                        result = (SoapObject) envelope.getResponse();
//                        jsonResponse = convertDataSoapObject(result);
//                        if (jsonResponse == null)
//                            throw new Exception(Common.MESSAGE.ex05.getContent());
//                    }

                    //kiểm tra là kiểu nguyên thủy hay kiểu object
                    if (isWrapperType(classTypeData)) {
                        primitive = (SoapPrimitive) envelope.getResponse();
                        if (primitive == null)
                            throw new Exception(Common.MESSAGE.ex06.getContent());
                        jsonResponse = primitive.toString();

                    } else {
                        result = (SoapObject) envelope.getResponse();
                        if (result == null)
                            throw new Exception(Common.MESSAGE.ex06.getContent());
                        jsonResponse = convertDataSoapObject(result);
                    }

                    if (jsonResponse == null)
                        throw new Exception(Common.MESSAGE.ex05.getContent());
//                    if (envelope.bodyIn instanceof SoapObject) {
//                        // SoapObject = SUCCESS
//                        result = (SoapObject) envelope.bodyIn;
//                        jsonResponse = convertDataSoapObject(result);
//                        if (jsonResponse == null)
//                            throw new Exception(Common.MESSAGE.ex05.getContent());
////                        response = (SoapObject) envelope.bodyIn;
//                    } else if (envelope.bodyIn instanceof SoapFault) { // SoapFault =
//                        // FAILURE
//                        SoapFault soapFault = (SoapFault) envelope.bodyIn;
//                        throw new Exception(soapFault.getMessage());
//                    }



                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }

            } catch (Exception e) {
                publishProgress(e.getMessage());
                e.printStackTrace();
            }

            return jsonResponse;
        }

//        private String convertDataSoapPrimitive(SoapPrimitive primitive) throws Exception {
//            if (primitive == null)
//                return null;
//            String jsonReponse = "";
//            try {
//                JSONObject o = new JSONObject();
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new Exception(Common.MESSAGE.ex06.getContent());
//            }
//            return jsonReponse;
//        }

        private String convertDataSoapObject(SoapObject response) throws Exception {
            if (response == null)
                return null;
            String jsonReponse = "";
            try {
                SoapObject soapLv1 = (SoapObject) response.getProperty("diffgram");
                SoapObject proInfoLv1 = null;
                try {
                    proInfoLv1 = (SoapObject) soapLv1.getProperty("NewDataSet");
                } catch (Exception e) {
                    throw new Exception(Common.MESSAGE.ex06.getContent());
                }

                //kiểm tra nếu có property 'CTO' thì lấy dữ liệu dataset
                //ngược lại nếu là 'Table1" thì lấy dữ liệu thông báo
                //2 giá trị này được cung cấp bởi server, nên debug các giá trị cây của soapObject để nắm rõ

                HashMap<String, SoapObject> result = null;
                SoapObject proInfoLv2 = null;

                //check and get all data by key field project and put to gson object
                JSONArray jsonArray = new JSONArray();

                //kiểm tra nếu key dataReal là CTO thì sẽ thao tác với classType
                Field[] allFields = null;

//                Field[] allFieldsFAKE = null;
                if (proInfoLv1.hasProperty(keyDataSetServerError)) {
                    isServerErrorResponse = true;
                    allFields = classTypeError.getDeclaredFields();
                } else {
                    isServerErrorResponse = false;
                    allFields = classTypeData.getDeclaredFields();
                }

                if (allFields == null) {
                    return jsonReponse;
                }

                int countProInfoLv1 = proInfoLv1.getPropertyCount();

                for (int i = 0; i < countProInfoLv1; i++) {
                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty(i);
                    JSONObject jsonObject = new JSONObject();

                    for (Field field : allFields) {
                        if (proInfoLv2.hasProperty(field.getName())) {
                            jsonObject.accumulate(field.getName(), proInfoLv2.getPropertyAsString(field.getName()));
                        } else {
                            jsonObject.accumulate(field.getName(), JSONObject.NULL);
                        }
                    }

                    jsonArray.put(jsonObject);
                }

                jsonReponse = jsonArray.toString();
            } catch (JSONException e) {
                e.printStackTrace();
                throw new Exception(Common.MESSAGE.ex06.getContent());
            }
            return jsonReponse;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            String message = values[0];
            callBack.onUpdate(message);
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            super.onPostExecute(jsonResponse);
            if (TextUtils.isEmpty(jsonResponse))
                return;
            //Xử lý kết quả
            final GsonBuilder gsonBuilder = new GsonBuilder();
            final Gson gson = gsonBuilder.create();
            Type type = null;
            try {
                List<V> objectTypeError = null;

                if (isServerErrorResponse) {
                    if(isWrapperType(classTypeError))
                    {
                        V primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeError);
                        callBack.onPostData(primitiveTypeData);
                    }else {
                        List<V> objectTypeData = null;
                        objectTypeData = toList(jsonResponse, classTypeError);
                        callBack.onPostData(objectTypeData);
                    }
                    objectTypeError = toList(jsonResponse, classTypeError);
                    callBack.onPostMessageSever(objectTypeError);
                } else {
                    if(isWrapperType(classTypeData))
                    {
                        K primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeData);
//                        K newT1 = (K)(Object)jsonResponse;
//                        string newT2 = (string)(object)t;
//                        K primitiveTypeData = (K)Convert.ChangeType(jsonResponse, typeof(K));
                        callBack.onPostData(primitiveTypeData);
                    }else {
                        List<K> objectTypeData = null;
                        objectTypeData = toList(jsonResponse, classTypeData);
                        callBack.onPostData(objectTypeData);

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                callBack.onUpdate(e.getMessage());
            }

        }

        <T> List<T> toList(String json, Class<T> typeClass) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            final Gson gson = gsonBuilder.serializeNulls().create();
            List<T> t = null;
//            Object o = null;
//            if (t instanceof ArrayList<?>) {
            t = gson.fromJson(json, new ListOfJson<T>(typeClass));
//            } else {
//                o = gson.fromJson(json, new ObjectOfJson<T>(typeClass));
//                List<T> list = gson.fromJson(json, new ListOfJson<T>(typeClass));
//                o = list.get(0);
//            }
            return t;

        }

        private static final Set<Class<?>> WRAPPER_TYPES = checkWrapperTypes();

        public static boolean isWrapperType(Class<?> clazz) {
            return WRAPPER_TYPES.contains(clazz);
        }

        private static Set<Class<?>> checkWrapperTypes() {
            Set<Class<?>> ret = new HashSet<Class<?>>();
            ret.add(Boolean.class);
            ret.add(Character.class);
            ret.add(Byte.class);
            ret.add(Short.class);
            ret.add(Integer.class);
            ret.add(Long.class);
            ret.add(Float.class);
            ret.add(Double.class);
            ret.add(Void.class);
            return ret;
        }

        @SuppressWarnings("unchecked")
        public static <T> T setValueWrapperTypes(String input, Class<T> clazz) throws Exception {
            T value = null;
            if (clazz.isAssignableFrom(String.class)) {
                value = (T) input;
            } else if (clazz.isAssignableFrom(Integer.class)) {
                value = (T) Integer.valueOf(input);
            } else if (clazz.isAssignableFrom(Boolean.class)) {
                value = (T) Boolean.valueOf(input);
            } else if (clazz.isAssignableFrom(Double.class)) {
                value = (T) Double.valueOf(input);
            } else {
                throw new IllegalArgumentException("Bad type.");
            }

           if(value == null)
               throw new IllegalArgumentException("Null data.");
            return value;
        }


        public class ObjectOfJson<T> implements ParameterizedType {
            private Class<?> wrapped;

            public ObjectOfJson(Class<T> wrapper) {
                this.wrapped = wrapper;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{wrapped};
            }

            @Override
            public Type getRawType() {
                return wrapped;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        }


        public class ListOfJson<T> implements ParameterizedType {
            private Class<?> wrapped;

            public ListOfJson(Class<T> wrapper) {
                this.wrapped = wrapper;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{wrapped};
            }

            @Override
            public Type getRawType() {
                return List.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        }

        public static abstract class AsyncSoapCallBack<K, V> {

            public abstract void onPre(final AsyncSoap soap);

            public abstract void onUpdate(String message);

            public abstract void onPostData(K dataResponse);

            public abstract void onPostMessageSever(V errorResponse);

        }
    }


//    public static class AsyncSoapExamp<T extends ResponseSoap> extends AsyncTask<String, String, T> {
//
//        Class<T> objectTypeData;
//
//        //request action to eStore
//        private String METHOD_NAME;
//        private String URL;
//        private static final String NAMESPACE = "http://tempuri.org/";
//        private String SOAP_ACTION;
//        private String[] METHOD_PARAM;
//        private AsyncSoapCallBack callBack;
//
//        public AsyncSoapExamp(Class<T> type, AsyncSoapCallBack callBack, String methodName, String URL, String... nameParams) throws Exception {
//            this.callBack = callBack;
//            this.objectTypeData = type;
//
//            this.METHOD_NAME = methodName;
//            this.URL = URL;
//            this.METHOD_PARAM = nameParams;
//            this.SOAP_ACTION = NAMESPACE + METHOD_NAME;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            callBack.onPre(this);
//        }
//
//        @Override
//        protected T doInBackground(String... argParams) {
//            //check size argParams with nameParams
//            T responeObject = null;
//
//            try {
//                if (argParams.length != METHOD_PARAM.length) {
//                    throw new Exception(Common.MESSAGE.ex04.getContent());
//                }
//
//                //truyền đủ các tham số tương ứng
//                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//                for (int i = 0; i < argParams.length; i++) {
//                    request.addProperty(METHOD_PARAM[i], argParams[i]);
//                }
//
//                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                envelope.setOutputSoapObject(request);
//                envelope.dotNet = true;
//                envelope.implicitTypes = true;
//                envelope.setAddAdornments(false);
////                envelope.addMapping(NAMESPACE, "CTO1", new CToPBResponse().getClass());
//
//                HttpTransportSE ht;
//                try {
//                    ht = new HttpTransportSE(URL, TIME_OUT);
//                    ht.call(SOAP_ACTION, envelope);
//
//                    SoapObject result = (SoapObject) envelope.getResponse();
//
//                    if (result == null)
//                        throw new Exception(Common.MESSAGE.ex06.getContent());
//
//                    HashMap<String, SoapObject> dataRealResult = callBack.convertDataSoapObject(result);
//                    if (dataRealResult == null)
//                        throw new Exception(Common.MESSAGE.ex05.getContent());
//
//                    //check and get all data by key field project and put to gson object
//                    JSONObject jsonObject = new JSONObject();
//
//                    //kiểm tra nếu key dataReal là CTO thì sẽ thao tác với objectTypeData
//                    Field[] allFields;
//                    final GsonBuilder gsonBuilder = new GsonBuilder();
//                    final Gson gson = gsonBuilder.create();
//
//                    if (dataRealResult.containsKey("CONG_TO")) {
//                        SoapObject dataReal = dataRealResult.get("CONG_TO");
//                        allFields = objectTypeData.getDeclaredFields();
//                        for (Field field : allFields) {
//                            if (dataReal.hasProperty(field.getName())) {
//                                jsonObject.accumulate(field.getName(), dataReal.getPropertyAsString(field.getName()));
//                            } else {
//                                jsonObject.accumulate(field.getName(), JSONObject.NULL);
//                            }
//                        }
//
//                        //convert to object
//                        responeObject = gson.fromJson(jsonObject.toString(), objectTypeData);
//                    }
//
//                    if (dataRealResult.containsKey("CTO")) {
//                        SoapObject dataReal = dataRealResult.get("CTO");
//                        allFields = objectTypeData.getDeclaredFields();
//                        for (Field field : allFields) {
//                            if (dataReal.hasProperty(field.getName())) {
//                                jsonObject.accumulate(field.getName(), dataReal.getPropertyAsString(field.getName()));
//                            } else {
//                                jsonObject.accumulate(field.getName(), JSONObject.NULL);
//                            }
//                        }
//
//                        //convert to object
//                        responeObject = gson.fromJson(jsonObject.toString(), objectTypeData);
//                    }
//
//                    //kiểm tra nếu key dataReal là CTO thì sẽ thao tác với string  server trả về thông báo tới UI
//                    if (dataRealResult.containsKey("Table1")) {
//                        SoapObject dataReal = dataRealResult.get("Table1");
//                        allFields = ThongBaoResponse.class.getDeclaredFields();
//                        for (Field field : allFields) {
//                            if (dataReal.hasProperty(field.getName())) {
//                                jsonObject.accumulate(field.getName(), dataReal.getPropertyAsString(field.getName()));
//                            } else {
//                                jsonObject.accumulate(field.getName(), JSONObject.NULL);
//                            }
//                        }
//
//                        //convert to object
//                        ThongBaoResponse thongBaoResponse = null;
//                        thongBaoResponse = gson.fromJson(jsonObject.toString(), ThongBaoResponse.class);
//
//                        publishProgress(thongBaoResponse.getThongbao());
//                        return null;
//                    }
//
//                } catch (Exception e) {
//                    throw new Exception(e.getMessage());
//                }
//
//            } catch (Exception e) {
//                publishProgress(e.getMessage());
//                e.printStackTrace();
//            }
//
//            return responeObject;
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//            String message = values[0];
//            callBack.onUpdate(message);
//        }
//
//        @Override
//        protected void onPostExecute(T response) {
//            super.onPostExecute(response);
//            if (response == null)
//                return;
//            callBack.onPost(response);
//        }
//
//        public static abstract class AsyncSoapCallBack<T extends ResponseSoap> {
//            public abstract void onPre(final AsyncSoap soap);
//
//            public abstract void onUpdate(String message);
//
//            public abstract void onPost(T response);
//
//            public abstract HashMap<String, SoapObject> convertDataSoapObject(SoapObject response);
//
//        }
//    }


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
//            String convertDataSoapObject = response.toString();
//
//            if (convertDataSoapObject.isEmpty()) {
//                publishProgress("Dữ liệu trả về rỗng!");
//                return null;
//            }
//
//            return Boolean.parseBoolean(convertDataSoapObject);
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
//                ((ListView) MainKiemDinhActivity.this.findViewById(R.id.lv_1)).setAdapter(new ArrayAdapter<String>(MainKiemDinhActivity.this, android.R.layout.simple_list_item_1, new String[]{result.toString()}));
//            } catch (Exception e) {
//                Toast.makeText(MainKiemDinhActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
