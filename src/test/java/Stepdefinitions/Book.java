package Stepdefinitions;

import Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
public class Book {

    @Test(dataProvider = "Booksdata" )
    public void book(String isbn, String aisle ) {

        RestAssured.baseURI = "http://216.10.245.166";
        String Response = given().header("Content-Type", "application/json")
                .body(Payload.addbook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = new JsonPath(Response);
        String id = js.get("ID");
        System.out.println(id);

    }

    @DataProvider(name= "Booksdata")
    public Object[][] getData() {
        //array - collection of elements
        // multidiOmensionsal array = collection of arrays
        return new Object[][]{{"EAT", "23234" }, {"YES", "23233" }, {"NO", "2342" }};
    }


    @Test
    //content of the file to string -> content of file can convert into byte -> Byte date to string
    public void PassingJsonInfile() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String Response = given().header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\HARIPRIYA\\Desktop\\API files\\addPlace.json"))))
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
       System.out.println(Response);

    }


    @Test
    //content of the file to string -> content of file can convert into byte -> Byte date to string
    public void PassingJsonIn() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String Response = given().header("Content-Type", "application/json")
                .body(GenerateStringFromResource("C:\\Users\\HARIPRIYA\\Desktop\\API files\\addPlace.json"))
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println(Response);
        System.out.println("$");

    }

    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

}