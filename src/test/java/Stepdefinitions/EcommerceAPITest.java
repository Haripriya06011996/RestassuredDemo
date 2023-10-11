package Stepdefinitions;

import Pojo.LoginRequest;
import Pojo.LoginResponse;
import Pojo.OrderDetails;
import Pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.given;
import static io.restassured.RestAssured.*;
public class EcommerceAPITest {

    public static void main(String args[]) {
//SSL sometimes when ur api expect to have ssl certification then your test will fail saying that certification is not valid
      //it will bypasseses then you will ahve smooth run
        RequestSpecification Res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        LoginRequest LR = new LoginRequest();
        LR.setUserEmail("haripriyaa06@gmail.com");
        LR.setUserPassword("Paris@0120");

        RequestSpecification re = given().relaxedHTTPSValidation().log().all().spec(Res).body(LR);


        LoginResponse loginresponse = re.when().post("/api/ecom/auth/login")
                .then().log().all().spec(resp).extract().response()
                .as(LoginResponse.class);
        String Loginrespnse = loginresponse.getToken();
        System.out.println(Loginrespnse);
        String UserId = loginresponse.getUserId();

        //add Product

        RequestSpecification addproduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", Loginrespnse)
                .build();

        RequestSpecification reqAddProduct = given().log().all().spec(addproduct)
                .param("productName", "Frocks")
                .param("productAddedBy", UserId)
                .param("productCategory", "fashion")
                .param("productSubCategory", "dress")
                .param("productPrice", "2343")
                .param("productDescription", "Addias Originals")
                .param("productFor", "women")
                .multiPart("productImage", new File("C://Users//HARIPRIYA//Desktop//sample image data//2.jpg"));

        String AddProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
                .then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(AddProductResponse);
        String productId = js.get("productId");

       //create order

        RequestSpecification createorder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", Loginrespnse)
                .setContentType(ContentType.JSON)
                .build();

        OrderDetails details = new OrderDetails();
        details.setCountry("India");
        details.setProductOrderedId(productId);

        List<OrderDetails> Orderlist = new ArrayList<OrderDetails>();
        Orderlist.add(details);
        Orders orders = new Orders();
        orders.setOrders(Orderlist);

    RequestSpecification createreq= given().log().all().spec(createorder)
                .body(orders);
        String ResponseOrder = createreq.when().post("/api/ecom/order/create-order")
                .then().extract().response().asString();
        System.out.println(ResponseOrder);


        //Deleteorder

        RequestSpecification deleteorder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", Loginrespnse)
                .setContentType(ContentType.JSON)
                .build();

        RequestSpecification deleteprodreq =  given().log().all().spec(deleteorder)
                .pathParams("productId",productId);

       String deleteresponse = deleteprodreq.when().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().asString();

        JsonPath js1 = new JsonPath(deleteresponse);
        Assert.assertEquals("Product Deleted Successfully", js1.get("message"));

    }
}
