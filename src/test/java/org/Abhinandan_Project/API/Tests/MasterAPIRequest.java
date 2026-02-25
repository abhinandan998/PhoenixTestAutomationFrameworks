package org.Abhinandan_Project.API.Tests;

import static org.Abhinandan_Project.API.Utils.ConfigManager.*;

import static org.Abhinandan_Project.API.Utils.AuthTokenProvider.*;
import static org.Abhinandan_Project.API.constants.Role.*;

import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class MasterAPIRequest {

    @Test
    public void masterAPITest(){

        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .headers("Authorization", getToken(FD))
                .and()
                .contentType("")
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Success"))
                .time(lessThan(1000L))
                .body("data", notNullValue())
                .body("data", hasKey("mst_oem"))
                .body("data", hasKey("mst_model"))
                .body("$", hasKey("message"))
                .body("$", hasKey("data"))
                .body("data.mst_oem.size()", greaterThan(0))//check the size of the json array
                .body("data.mst_model.size()", greaterThan(0))
                .body("data.mst_oem.id", everyItem(notNullValue()))
                .body("data.mst_oem.name", everyItem(notNullValue()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/masterAPIResponseSchema-FD.json"));


    }


    @Test
    public void invalidTokenMasterAPITest(){
        given()
                .baseUri(getProperty("BASE_URI"))
                .and()
                .headers("Authorization", "")
                .and()
                .contentType("")
                .when()
                .post("master")
                .then()
                .log().all()
                .statusCode(401);
    }
}
