package esolutions.com.barcodehungyenpc.entity;

public class AmazonSearchClient {
//    private static final String NAMESPACE = "http://webservices.amazon.com/AWSECommerceService/2006-05-17";
//    private static final String AMAZON_WEBSERVICE_KEY = "";
//
//    public AmazonSearchClient() {
//        if (AMAZON_WEBSERVICE_KEY.length() == 0) {
//            System.out.println("Please substitute your own amazon webservice key before running this code.");
//        } else {
//            Request requestObject = new Request();
//            requestObject.author = "Whte";
//            requestObject.searchIndex = "Books";
//
//            SoapObject request = new SoapObject(NAMESPACE, "ItemSearch");
//            request.addProperty("SubscriptionId", AMAZON_WEBSERVICE_KEY);
//            request.addProperty("Request", requestObject);
//
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//            envelope.setOutputSoapObject(request);
//            requestObject.register(envelope);
//
//            registerObjects(envelope);
//
//            HttpTransportSE httpTransportSE =
//                    new HttpTransportSE("http://soap.amazon.com/onca/soap?Service=AWSECommerceService");
//            httpTransportSE.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//            try {
//                httpTransportSE.call("http://soap.amazon.com", envelope);
//                BookItems response = (BookItems) envelope.getResponse();
//                System.out.println(response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void registerObjects(SoapSerializationEnvelope envelope) {
//        new ItemSearchResponse().register(envelope);
//    }
//
//    public static void main(String[] args) {
//        new AmazonSearchClient();
//    }
}