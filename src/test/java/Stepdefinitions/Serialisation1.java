package Stepdefinitions;

import Pojo.AddPlace;
import Pojo.location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Serialisation1 {
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

          // RestAssured.baseURI = "https://rahulshettyacademy.com";
            RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
            .addQueryParam("key","qaclick123")
            .setContentType(ContentType.JSON).build();

         ResponseSpecification Resp = new ResponseSpecBuilder()
         .expectContentType(ContentType.JSON)
         .expectStatusCode(200).build();


        RequestSpecification re = given().log().all().spec(req)
                    .body(p);

              Response res =  re.when().post("/maps/api/place/add/json")
                      .then().spec(Resp).extract().response();

            String resString = res.asString();
            System.out.println(resString);






    }
}
