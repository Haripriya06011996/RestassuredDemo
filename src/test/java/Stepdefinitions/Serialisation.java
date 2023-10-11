package Stepdefinitions;

import Files.Payload;
import Pojo.AddPlace;
import Pojo.CustomerDetails;
import Pojo.location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Serialisation {
    public static void main(String[] args) {

        AddPlace p = new AddPlace();
        location a = new location();
        p.setAccuracy(50);
        p.setAddress("9, side layout, cohen 09");
        p.setLanguage("French");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setName("Frontline house");
        List<String> mylist = new ArrayList<String>();
        mylist.add("Shoe park");
        mylist.add("shop");
        p.setTypes(mylist);
        a.setLat(-38.342344);
        a.setLng(33.426456);
        p.setLocation(a);

           RestAssured.baseURI = "https://rahulshettyacademy.com";
    //RequestSpecification req = new RequestSpecBuilder().addQueryParam("key","qaclick123").


            Response res = given().log().all().queryParam("key", "qaclick123")
                    .body(p)
                    .when().post("/maps/api/place/add/json")
                    .then().assertThat().statusCode(200).extract().response();


            String resString = res.asString();
            System.out.println(resString);






    }
}
