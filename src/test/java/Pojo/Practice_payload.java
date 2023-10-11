package Pojo;

public class Practice_payload {

    public static String AddPlace(String isbn,String aisle){

        String Payload ="{\n" +
                " \n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}";

        return Payload;
    }
}
