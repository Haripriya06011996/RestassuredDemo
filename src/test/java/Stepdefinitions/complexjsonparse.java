package Stepdefinitions;

import Files.Payload;
import io.restassured.path.json.JsonPath;

import java.util.List;

public class complexjsonparse {
    public static void main (String args[]){

        JsonPath js2 = new JsonPath(Payload.price());
        //1. Print No of courses returned by API
        int Count= js2.getInt("courses.size()");
        System.out.println(Count);
        //2.Print Purchase Amount
        int PurchaseAmount = js2.getInt("dashboard.purchaseAmount");
        System.out.println(PurchaseAmount);
        //3. Print Title of the first course
        String Title1 = js2.get("courses[0].title");
        System.out.println(Title1);

       // 4. Print All course titles and their respective Prices
        for(int i=0; i<=Count-1;i++){
            String Title= js2.get("courses["+i+"].title");
            js2.get("courses["+i+"].price");
            System.out.println(Title);
            System.out.println(js2.get("courses["+i+"].price").toString());
        }

       // 5. Print no of copies sold by RPA Course
        for(int i=0; i<=Count-1;i++){
            String CourseTitle= js2.get("courses["+i+"].title");
            if (CourseTitle.equals("RPA")){
              System.out.println("RPAcoursecopies"+js2.get("courses["+i+"].copies").toString());
              break;
            }
        }
          int sum=0;
    //6. Verify if Sum of all Course prices matches with Purchase Amount
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
