package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.UUID;

public class testCase_API_03 {

    String token;
    String id;
    String adventure;

    public void registerUser(){

        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/register";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", "saurabh15@gmail.com");
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
    }

    public void userlogin(){
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/login";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("email", "saurabh15@gmail.com");
        jsonBody.put("password","password123"); 
        
        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody.toString());
        Response res = request.post();
        String responseType = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseType);
        token = jsonPath.getString("token");
        id = jsonPath.getString("id");
    }

    @Test
    public void makeReservation(){
        registerUser();
        userlogin();
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/reservations/new";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("userId", id);
        jsonBody.put("name","testUser2"); 
        jsonBody.put("date","2023-09-12");
        jsonBody.put("person","1");
        jsonBody.put("adventure","1773524915");

        RequestSpecification request = RestAssured.given().contentType(ContentType.JSON).body(jsonBody.toString()).header("Authorization","Bearer"+token);
        Response res = request.post();
        //int responseStatusCode = res.getStatusCode();
        String responseBody = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        //Boolean successMessage = jsonPath.getBoolean("success");
        //SoftAssert.assertTrue(successMessage, "Error in success message JSON");
        getReservation();
        
    }

    public void getReservation(){
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/reservations";
        RequestSpecification request = RestAssured.given().queryParam("id",id).header("Authorization","Bearer"+token);;
        Response res = request.get();
        String responseBody = res.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        //String adv = jsonPath.getString("adventure");
        //Assert.assertEquals(adv, "1773524915");
    }
}
