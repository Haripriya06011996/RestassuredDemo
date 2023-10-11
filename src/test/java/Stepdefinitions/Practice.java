package Stepdefinitions;

import Pojo.Practice_payload;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Practice {

    @Test
    public static void practice1() {
        RestAssured.baseURI="http://216.10.245.166";
        String Response = given().log().all().header("Content-Type", "Application/json")
                .body(Practice_payload.AddPlace("bcd","227"))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .body("Msg",equalTo("Book Already Exists"))
                .extract().response().asString();

        JsonPath js = new JsonPath(Response);
        String id =js.get("ID");
        Assert.assertEquals(id, "bcd227");

}


}
