package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class testCase_API_04 {
    @Test(groups = {"API test"})
    public void registerUserTest(){

        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/register";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", "saurabh13@gmail.com");
        jsonBody.put("password","password123");        
        jsonBody.put("confirmpassword","password123");

        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody.toString());
        Response res = request.post();
        System.out.println(res.statusCode());
        System.out.println(res.asString());
        String responseType = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseType);
        // Assert.assertFalse(jsonPath.getBoolean("success"));
        Assert.assertEquals(jsonPath.getBoolean("success"), false, "Error in success message JSON");
        registerUserAgain();
    }

    public void registerUserAgain(){

        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/register";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", "saurabh13@gmail.com");
        jsonBody.put("password","password123");        
        jsonBody.put("confirmpassword","password123");

        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody.toString());
        Response res = request.post();
        System.out.println(res.statusCode());
        System.out.println(res.asString());
        String responseType = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseType);
        //Assert.assertTrue(jsonPath.getBoolean("success"));
        //String msg = jsonPath.getString("message");
        //Assert.assertEquals(msg, "Email already exists");
    }


    }

  

