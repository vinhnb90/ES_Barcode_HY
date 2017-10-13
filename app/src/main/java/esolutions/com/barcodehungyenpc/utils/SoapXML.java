package esolutions.com.barcodehungyenpc.utils;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static android.text.Html.toHtml;
import static esolutions.com.barcodehungyenpc.utils.Log.*;

import esolutions.com.barcodehungyenpc.entity.Test;

/**
 * Created by VinhNB on 8/31/2017.
 */

public class SoapXML {

    public static String URL;

    public static String getURL(String address) {
//        return "http://" + address + "/servicedodemHungYen1509/ServiceTienIch_DoDem.asmx";
//        return "http://" + address + "/ServiceTienIchDoDem/ServiceTienIch_DoDem.asmx";

//                "http://" + address + "/servicedodem28.8/ServiceTienIch_DoDem.asmx";
//        return "http://" + address + "/WebService1.asmx";
//        return "http://" + address + "/ServiceTienIchDoDem/ServiceTienIch_DoDem.asmx";
        return "http://" + address + "/ServiceTienIchDoDem/ServiceTienIch_DoDem.asmx";
//        return "http://192.168.68.103:9999/serTienIch_DoDem/ServiceTienIch_DoDem.asmx";
    }

    public static int TIME_OUT = 30000;

    public enum METHOD {
        CTO_PB("Select_Info_PBCT", new String[]{"strMaDViQLy", "strMaCTo", "Check"}),
        CTO_KD("Select_Info_Send_KDCT_MTB", new String[]{"strDVi", "strMaCTo"}),

        Select_MADVIQLY("Select_MADVIQLY", new String[]{}),
        Select_DangNhap("Select_DangNhap", new String[]{"user", "pass", "Ma_DViQLy"}),
        Update_GuiKD_CTO_MTB("Update_GuiKD_CTO", new String[]{"a"}),
        Update_PBCT_MTB("Update_PBCT_MTB", new String[]{"a"});


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
        private boolean isPrimitive = false;

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
//                envelope.addMapping(NAMESPACE, "CTO1", new CToKDResponse().getClass());

                HttpTransportSE ht;
                try {
                    try {
                        ht = new HttpTransportSE(URL, TIME_OUT);
                        ht.debug = true;
                        ht.call(SOAP_ACTION, envelope);

                    } catch (Exception ex) {
                        throw new Exception(Common.MESSAGE.ex06.getContent());
                    }

                    SoapObject result = null;
                    SoapPrimitive primitive = null;
                    String requestDump = ht.requestDump;
                    String responseDump = ht.responseDump;
//                    int field = classTypeData.getDeclaredFields().length;
//                    if (field == 2) {
////                        SoapFault soapFault = (SoapFault) envelope.bodyIn;
//                        primitive = (SoapPrimitive) envelope.getResponse();
//                        Object DataTable1 =
//                        jsonResponse = convertDataSoapPrimitive(primitive);
//                    } else {
//                        result = (SoapObject) envelope.getResponse();
//                        jsonResponse = convertDataSoapObject(result);
//                        if (jsonResponse == null)
//                            throw new Exception(Common.MESSAGE.ex05.getContent());
//                    }

                    //kiểm tra là kiểu nguyên thủy hay kiểu object
                    if (isWrapperType(classTypeData)) {
                        isPrimitive = true;
                        primitive = (SoapPrimitive) envelope.getResponse();
                        if (primitive == null)
                            throw new Exception(Common.MESSAGE.ex06.getContent());
                        jsonResponse = primitive.toString();

                    } else {
                        isPrimitive = false;
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
                    throw e;
                }

            } catch (Exception e) {
                publishProgress(e.getMessage());
                e.printStackTrace();
                try {
                    getInstance().loge(SoapXML.class, "Xảy ra vấn đề khi thao tác server API " + METHOD_NAME + " \n has content \n" + e.getMessage());
                } catch (Exception e1) {
                    publishProgress(e1.getMessage());
                    e1.printStackTrace();
                }
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
                    throw new Exception(Common.MESSAGE.ex061.getContent());
                }

                //kiểm tra nếu có property 'CTO' thì lấy dữ liệu dataset
                //ngược lại nếu là 'Table1" thì lấy dữ liệu thông báo
                //2 giá trị này được cung cấp bởi server, nên debug các giá trị cây của soapObject để nắm rõ
                ArrayList<HashMap<String, String>> dataList = new ArrayList<>();
                for (int j = 0; j < proInfoLv1.getPropertyCount(); j++) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    SoapObject finalObject = (SoapObject) proInfoLv1.getProperty(j);
                    for (int i = 0; i < finalObject.getPropertyCount(); i++) {
                        Object object = finalObject.getProperty(i);
                        PropertyInfo propertyInfo = new PropertyInfo();
                        finalObject.getPropertyInfo(i, propertyInfo);
                        if (object instanceof SoapPrimitive) {
                            hashMap.put(propertyInfo.name, object.toString());
                        } else {
                            hashMap.put(propertyInfo.name, null);
                        }
                    }
                    dataList.add(hashMap);
                }

                HashMap<String, SoapObject> result = null;
                SoapObject proInfoLv2 = null;

                //check and get all data by key field project and put to gson object
                JSONArray jsonArray = new JSONArray();

                //kiểm tra nếu key dataReal là CTO thì sẽ thao tác với classType
                Field[] allFields = null;

                int countProInfoLv1 = proInfoLv1.getPropertyCount();

                for (int i = 0; i < countProInfoLv1; i++) {
                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty(i);

                    if (proInfoLv2.hasProperty(keyDataSetServerError)) {
                        isServerErrorResponse = true;
                        break;
                    }
                }

                if (isServerErrorResponse) {
                    allFields = classTypeError.getDeclaredFields();
                } else {
                    allFields = classTypeData.getDeclaredFields();
                }

                if (allFields == null) {
                    return jsonReponse;
                }

//
//
//                if (dataList.size() == 1 && dataList.get(0).containsKey(keyDataSetServerError)) {
//                    isServerErrorResponse = true;
//                    allFields = classTypeError.getDeclaredFields();
//                } else {
//                    isServerErrorResponse = false;
//                    allFields = classTypeData.getDeclaredFields();
//                }
                //[{"thongbao":"Không tìm thấy công tơ","$change":null,"serialVersionUID":null}]

                for (int i = 0; i < dataList.size(); i++) {
                    JSONObject jsonObject = new JSONObject();

                    for (Field field : allFields) {
                        String fieldName = field.getName();
                        if (fieldName.equals("$change") || fieldName.equals("serialVersionUID"))
                            break;
                        Object data = dataList.get(i).get(fieldName);
                        jsonObject.put(fieldName, data == null ? JSONObject.NULL : data.toString());
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
            if (StringUtils.isEmpty(jsonResponse))
                return;
            //Xử lý kết quả
            try {
                if (isPrimitive) {
                    K primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeData);
                    callBack.onPostData(primitiveTypeData);
                }

                if (isServerErrorResponse) {
                    JSONArray jsonRoot = new JSONArray(jsonResponse);
                    String message = ((JSONObject) jsonRoot.get(0)).getString(keyDataSetServerError);
                    callBack.onPostMessageSever(message);
                } else {
                    final GsonBuilder gsonBuilder = new GsonBuilder();
                    final Gson gson = gsonBuilder.create();
                    Type type = null;

                    if (isWrapperType(classTypeData)) {
                        K primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeData);
                        callBack.onPostData(primitiveTypeData);
                    } else {
                        List<K> objectTypeData = null;
                        objectTypeData = toList(jsonResponse, classTypeData);
                        callBack.onPostData(objectTypeData);

                    }


                }

//
//                if (isServerErrorResponse) {
//                    if (isWrapperType(classTypeError)) {
//                        V primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeError);
//                        callBack.onPostData(primitiveTypeData);
//                    } else {
//
////                        objectTypeError = toList(jsonResponse, classTypeError);
////                        callBack.onPostData(objectTypeError);
//                        List<V> objectTypeError = null;
//                        objectTypeError = toList(jsonResponse, classTypeError);
//                        callBack.onPostMessageSever(objectTypeError);
//
////                        Type collectionType = new TypeToken<V[]>() {
////                        }.getType();
////                        V[] dataElement = gson.fromJson(jsonResponse, collectionType);
////                         objectTypeError = Arrays.asList(dataElement);
////                        callBack.onPostData(objectTypeError);
//                    }
//                    ;
//                } else {
//                    if (isWrapperType(classTypeData)) {
//                        K primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeData);
////                        K newT1 = (K)(Object)jsonResponse;
////                        string newT2 = (string)(object)t;
////                        K primitiveTypeData = (K)Convert.ChangeType(jsonResponse, typeof(K));
//                        callBack.onPostData(primitiveTypeData);
//                    } else {
//                        List<K> objectTypeData = null;
//                        objectTypeData = toList(jsonResponse, classTypeData);
//                        callBack.onPostData(objectTypeData);
//
//                    }
//                }

            } catch (Exception e) {
                e.printStackTrace();
                callBack.onUpdate(e.getMessage());
            }

        }

        public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
            T[] arr = new Gson().fromJson(s, clazz);
            return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for MA_CTO one-liner
        }


        <T> List<T> toList(String json, Class<T> typeClass) {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            final Gson gson = gsonBuilder.create();
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

            if (value == null)
                throw new IllegalArgumentException("Null data.");
            return value;
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

            public abstract void onPostMessageSever(String errorResponse);

        }
    }


    public static class AsyncSoapUpload<K, V> extends AsyncTask<Object, String, String> {


        private K objectTypeData;
        private V objectTypeError;
        private Class<V> classTypeError;
        private Class<K> classTypeData;
        //request action to eStore
        private METHOD method;
        private String URL_LINK;
        private static final String NAMESPACE = "http://tempuri.org/";
        private String SOAP_ACTION;
        private String keyDataSetServerError;
        private AsyncSoapCallBackUpload callBack;

        //giá trị phân biệt là server data hay server response
        private boolean isServerErrorResponse = false;

        public AsyncSoapUpload(Class<K> classTypeData, Class<V> classTypeError, String keyDataSetServerError, AsyncSoapCallBackUpload callBack, METHOD method, String URL_LINK) throws Exception {
            this.callBack = callBack;
//            this.objectTypeData = objectTypeData;
//            this.objectTypeError = objectTypeError;
            this.keyDataSetServerError = keyDataSetServerError;
            this.classTypeData = classTypeData;
            this.classTypeError = classTypeError;
//            objectTypeData = classTypeData.newInstance();
//            objectTypeError = classTypeError.newInstance();
            this.method = method;
            this.URL_LINK = URL_LINK;
//            this.URL_LINK = "192.168.68.103:9999";
            this.SOAP_ACTION = NAMESPACE + this.method.getNameMethod();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            callBack.onPre(this);
        }

        @Override
        protected String doInBackground(Object... objects) {
            //check size argParams with nameParams
            String jsonResponse = "";
            try {

                //truyền đủ các tham số tương ứng
                SoapSerializationEnvelope envelope = callBack.setupRequest(NAMESPACE, method);

                HttpTransportSE ht;
                try {
                    ht = new HttpTransportSE(URL_LINK, TIME_OUT);
                    ht.debug = true;
                    ht.call(SOAP_ACTION, envelope);

                    SoapObject result = null;
                    SoapPrimitive primitive = null;

                    String requestDump = ht.requestDump;
                    String responseDump = ht.responseDump;
                    getInstance().logi(SoapXML.class, "Response server API " + method.getNameMethod() + " \n has content \n" + responseDump);
                    String response = envelope.getResponse() + "";


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
                } catch (Exception ex) {
                    throw new Exception(ex.getMessage());
                }

            } catch (Exception e) {
                publishProgress(e.getMessage());
                e.printStackTrace();
                try {
                    getInstance().loge(SoapXML.class, "Xảy ra vấn đề khi thao tác server API " + method.getNameMethod() + " \n has content \n" + e.getMessage());
                } catch (Exception e1) {
                    publishProgress(e1.getMessage());
                    e1.printStackTrace();
                }
                return jsonResponse;
            }

            return jsonResponse;
        }

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

                int countProInfoLv1 = proInfoLv1.getPropertyCount();

                for (int i = 0; i < countProInfoLv1; i++) {
                    proInfoLv2 = (SoapObject) proInfoLv1.getProperty(i);

                    if (proInfoLv2.hasProperty(keyDataSetServerError)) {
                        isServerErrorResponse = true;
                        break;
                    }
                }

                if (isServerErrorResponse) {
                    allFields = classTypeError.getDeclaredFields();
                } else {
                    allFields = classTypeData.getDeclaredFields();
                }

                if (allFields == null) {
                    return jsonReponse;
                }

                countProInfoLv1 = proInfoLv1.getPropertyCount();

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
            if (StringUtils.isEmpty(jsonResponse))
                return;
            //Xử lý kết quả
            final GsonBuilder gsonBuilder = new GsonBuilder();
            final Gson gson = gsonBuilder.create();
            Type type = null;
            try {
                if (isServerErrorResponse) {
                    if (isWrapperType(classTypeError)) {
                        V primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeError);
                        callBack.onPostData(primitiveTypeData);
                    } else {
                        JSONArray response = new JSONArray(jsonResponse);
                        JSONObject responseObject = response.getJSONObject(0);
                        String messageServer = responseObject.getString(keyDataSetServerError);
                        callBack.onPostMessageSever(messageServer);
                    }
                } else {
                    if (isWrapperType(classTypeData)) {
                        K primitiveTypeData = setValueWrapperTypes(jsonResponse, classTypeData);
                        callBack.onPostData(primitiveTypeData);
                    } else {
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

        <T> List<T> toList(String json, Class<T> typeClass) throws IllegalAccessException, InstantiationException {
            final GsonBuilder gsonBuilder = new GsonBuilder();
            final Gson gson = gsonBuilder.serializeNulls().create();
            List<T> t = null;

            t = gson.fromJson(json, new ListOfJson<T>(typeClass));
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

            if (value == null)
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

        public static abstract class AsyncSoapCallBackUpload<K, V> {

            public abstract void onPre(final AsyncSoapUpload soap);

            public abstract void onUpdate(String message);

            public abstract void onPostData(K dataResponse);

            public abstract void onPostMessageSever(String errorResponse);

            public abstract SoapSerializationEnvelope setupRequest(String NAMESPACE, METHOD method);
        }
    }


//    public static class AsyncSoapExamp<T extends ResponseSoap> extends AsyncTask<String, String, T> {
//
//        Class<T> objectTypeData;
//
//        //request action to eStore
//        private String method;
//        private String URL_LINK;
//        private static final String NAMESPACE = "http://tempuri.org/";
//        private String SOAP_ACTION;
//        private String[] METHOD_PARAM;
//        private AsyncSoapCallBack callBack;
//
//        public AsyncSoapExamp(Class<T> type, AsyncSoapCallBack callBack, String methodName, String URL_LINK, String... nameParams) throws Exception {
//            this.callBack = callBack;
//            this.objectTypeData = type;
//
//            this.method = methodName;
//            this.URL_LINK = URL_LINK;
//            this.METHOD_PARAM = nameParams;
//            this.SOAP_ACTION = NAMESPACE + method;
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
//                SoapObject request = new SoapObject(NAMESPACE, method);
//                for (int i = 0; i < argParams.length; i++) {
//                    request.addProperty(METHOD_PARAM[i], argParams[i]);
//                }
//
//                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                envelope.setOutputSoapObject(request);
//                envelope.dotNet = true;
//                envelope.implicitTypes = true;
//                envelope.setAddAdornments(false);
////                envelope.addMapping(NAMESPACE, "CTO1", new CToKDResponse().getClass());
//
//                HttpTransportSE ht;
//                try {
//                    ht = new HttpTransportSE(URL_LINK, TIME_OUT);
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
//        private String method = ;
//        private String NAMESPACE = "http://tempuri.org/";
//        private String URL_LINK = ENDPOINT_URL;
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
//            SoapObject request = new SoapObject(NAMESPACE, method);
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
//                ht = new HttpTransportSE(URL_LINK, 30000);
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
//                ((ListView) MainActivity.this.findViewById(R.id.lv_1)).setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, new String[]{result.toString()}));
//            } catch (Exception e) {
//                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

}
