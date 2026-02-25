package org.Abhinandan_Project.API.Tests;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.Abhinandan_Project.API.Utils.AuthTokenProvider.*;
import static org.Abhinandan_Project.API.Utils.ConfigManager.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.Abhinandan_Project.API.constants.Role.*;

public class CountAPITest {

    @Test
    public void verifyCountAPIResponse(){

        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .headers("Authorization", getToken(FD))
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("/dashboard/count")
                .then()
                .log().all()
                .statusCode(200)
                .body("message",equalTo("Success"))
                .time(lessThan(2500L))
                .body("data", notNullValue())
                .body("data.size()", equalTo(3))
                .body("data.count", everyItem(greaterThanOrEqualTo(0)))
                .body("data.label", everyItem(not(blankOrNullString())))
                .body("data.key", containsInAnyOrder("pending_for_delivery","pending_fst_assignment", "created_today"))
                .body(matchesJsonSchemaInClasspath("response_schema/countAPIResponseSchema-FD.json"));


    }
    @Test
    public void countAPITest_MissingAuthToken(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .log().uri()
                .log().method()
                .log().headers()
                .when()
                .get("/dashboard/count")
                .then()
                .log().all()
                .statusCode(401);
    }
}
