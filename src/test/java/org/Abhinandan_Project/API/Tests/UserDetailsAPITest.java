package org.Abhinandan_Project.API.Tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import static org.Abhinandan_Project.API.Utils.ConfigManager.*;

import static  org.Abhinandan_Project.API.Utils.AuthTokenProvider.*;

import static org.Abhinandan_Project.API.constants.Role.*;
import org.testng.annotations.Test;


public class UserDetailsAPITest {
 @Test
    public void userDetailsAPITest() {


        Header authHeader = new Header("Authorization", getToken(FD));
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .header(authHeader)
                .and()
                .accept(ContentType.JSON)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()
                .when()
                .get("userdetails")
                .then()
                .log().all()

                .statusCode(200)
                .time(lessThan(2000L))
                .and()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));
    }
}
