package org.Abhinandan_Project.API.Utils;

import io.restassured.http.ContentType;
import org.Abhinandan_Project.API.POJOS.UserCredentials;
import org.Abhinandan_Project.API.constants.Role;

import static org.Abhinandan_Project.API.constants.Role.*;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class AuthTokenProvider {

    private AuthTokenProvider(){

    }
    public static String getToken(Role role){

        //I want to make the request for the loginAPi and we want to extract the token and
        //print it on the console

        UserCredentials userCredentials =  null;
        if(role == FD)
        {
            userCredentials = new UserCredentials("iamfd", "password");
        }
        else if(role == SUP)
        {
            userCredentials = new UserCredentials("iamsup", "password");
        }
        else if(role == ENG)
        {
            userCredentials = new UserCredentials("iameng", "password");
        }
        else if(role == QC)
        {
            userCredentials = new UserCredentials("iamqc", "password");
        }
         String token = given()
                .baseUri(ConfigManager.getProperty("BASE_URI"))
                .contentType(ContentType.JSON)
                .body(new UserCredentials("iamfd", "password"))
                .when()
                .post("login")
                .then()
                .log().ifValidationFails()
                 .statusCode(200)
                 .body("message", equalTo("Success"))
                .extract()
                .body()
                .jsonPath()
                .getString("data.token");
        return token;
    }
}
