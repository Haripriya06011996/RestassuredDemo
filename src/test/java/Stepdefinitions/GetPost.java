package Stepdefinitions;

import Files.Payload;
import Pojo.CustomerDetails;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetPost {

    public static void main(String[] args){

        //given - all input details
        //when - submit the API--resourse. http method
        //Then - Validate the response
        RestAssured.baseURI ="https://rahulshettyacademy.com";
        String postResponse =given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
                .body(Payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                //.then().log().all().assertThat().statusCode(200)
                .then().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server" ,"Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        System.out.println("postresponse+++++++"+postResponse);
        JsonPath js = new JsonPath(postResponse); //passing json- whcih take string as input and convert the json
        String Place_id= js.getString("place_id");
        System.out.println("PlaceId is :+++++++++++++++"+Place_id);

        String new_address = "Summer Wak Africa";
        String putResponse=given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+Place_id+"\",\n" +
                        "\"address\":\""+new_address+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n" +
                        " \n")
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                        .extract().response().asString();

        System.out.println("putresponse+++++++"+putResponse);


        String GetplaceResponse=   given().log().all()
                .queryParam("place_id",Place_id)
                .queryParam("key","qaclick123")
                .when().get("/maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .extract().response().asString();
        System.out.println("getresponse+++++++"+GetplaceResponse);
        JsonPath js1= new JsonPath(GetplaceResponse);
        String ActualAddress = js1.getString("address");
        System.out.println("ActualAddress is "+ActualAddress);
       //cucumber junit, Testng
      // assertequals(ActualAddress,new_address);


        //passing pojo class ->coverted resonse body into java object
        CustomerDetails Rc =   given().log().all()
                .queryParam("place_id",Place_id)
                .queryParam("key","qaclick123").expect().defaultParser(Parser.JSON)
                .when()
                .get("/maps/api/place/get/json")
                .as(CustomerDetails.class);

      System.out.println(Rc.getMaxResults());
        System.out.println(Rc.getCustomerParameters().getFilters().get(0).getValue());


    }
}
