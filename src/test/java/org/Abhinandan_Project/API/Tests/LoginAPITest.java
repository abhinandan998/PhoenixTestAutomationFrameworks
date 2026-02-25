package org.Abhinandan_Project.API.Tests;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.Abhinandan_Project.API.POJOS.UserCredentials;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LoginAPITest {

    @Test
    public void LoginAPITest(){

        UserCredentials userCredentials = new UserCredentials("iamfd", "password");
        given().
                baseUri("http://64.227.160.186:9000/v1")
                .and()
                .contentType(ContentType.JSON)
                .and()
                .accept(ContentType.JSON)
                .and()
                .body(userCredentials)
                .log().all()

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
