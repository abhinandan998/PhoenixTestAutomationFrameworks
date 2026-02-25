package org.Abhinandan_Project.API.Tests;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.Abhinandan_Project.API.POJOS.UserCredentials;
import static org.Abhinandan_Project.API.Utils.ConfigManager.*;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginAPITest {

    @Test
    public void LoginAPITest()  {

        // read the property value that is going to run from terminal


        UserCredentials userCredentials = new UserCredentials("iamfd", "password");
        given().
                baseUri(getProperty("BASE_URI"))
                .and()
                .contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and()
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()

                .when()
                .post("login")
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(2000L))
                .and()
                .body("message", equalTo("Success"))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/LoginResponseSchema.json"));



    }
}
