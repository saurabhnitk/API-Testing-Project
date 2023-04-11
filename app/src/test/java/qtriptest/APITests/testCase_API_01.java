package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import java.util.UUID;



public class testCase_API_01 {
    @Test //Combine into one test
    public void TestCase01(){

        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/register";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", "saurabh14@gmail.com");
        jsonBody.put("password","password123");        
        jsonBody.put("confirmpassword","password123");

        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody.toString());
        Response res = request.post();
        System.out.println(res.statusCode());
        System.out.println(res.asString());
        String responseType = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseType);
        // Assert.assertTrue(jsonPath.getBoolean("success"));
        Assert.assertEquals(jsonPath.getBoolean("success"), false, "Error in success message JSON");
        userlogin();

    }

    // @Test //Combine into one test
    public void userlogin(){
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/login";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", "saurabh14@gmail.com");
        jsonBody.put("password","password123"); 
        
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody.toString());
        Response res = request.post();
        System.out.println(res.statusCode());
        System.out.println(res.asString());
        String responseType = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseType);
        Assert.assertTrue(jsonPath.getBoolean("success"));
     
    }
   
}
