package Stepdefinitions;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.lang.annotation.Target;

public class Sumofpurshcaseamount {

    @Test
    public void sumofpurchase(){
        JsonPath js2 = new JsonPath(Payload.price());
        int Count= js2.getInt("courses.size()");
        System.out.println(Count);
        int sum=0;
        int PurchaseAmount = js2.getInt("dashboard.purchaseAmount");
        for(int i=0; i<=Count-1;i++){
            int price = js2.get("courses["+i+"].price");
            int copies =   js2.get("courses["+i+"].copies");
            sum = sum+ price*copies;
        }
        System.out.println("actual"+sum);
        if (PurchaseAmount==sum){
            System.out.println("Sum of all Course prices matches with Purchase Amount");
        }else {
            System.out.println("Sum of all Course prices not matches with Purchase Amount");
        }
    }
}


