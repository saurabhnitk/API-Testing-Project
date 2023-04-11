package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
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

import java.io.File;
import java.util.LinkedHashMap;
import java.util.UUID;
public class testCase_API_02 {
    @Test
    public void searchCity(){
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "api/v1/cities";
        RequestSpecification request = RestAssured.given().queryParam("q","beng");
        Response res = request.get();
        int responseStatusCode = res.getStatusCode();
        System.out.println(res.asString());
        Assert.assertEquals(responseStatusCode, 200);
        System.out.println(res.statusCode());
        
    }



}
