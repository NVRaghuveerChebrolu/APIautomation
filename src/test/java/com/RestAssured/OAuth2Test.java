package com.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuth2Test {

    // Replace these with your OAuth credentials
    private static final String CLIENT_ID = "your_client_id";
    private static final String CLIENT_SECRET = "your_client_secret";
    private static final String TOKEN_URL = "https://your-auth-server.com/oauth/token";
    private static final String API_URL = "https://api.your-service.com/secure-endpoint";
    private static final String REDIRECT_URI = "your_redirect_uri";

    public static void main(String[] args) {

        // Step 1: Obtain OAuth 2.0 access token
        Response tokenResponse = given()
                .auth()
                .preemptive()
                .basic(CLIENT_ID, CLIENT_SECRET)
                .formParam("grant_type", "client_credentials")
                .formParam("redirect_uri", REDIRECT_URI)
                .post(TOKEN_URL);

        // Extract the access token
        String accessToken = tokenResponse.jsonPath().getString("access_token");

        System.out.println("Access Token: " + accessToken);

        // Step 2: Make an API request using the access token
        Response apiResponse = given()
                .auth()
                .oauth2(accessToken)
                .when()
                .get(API_URL)
                .then()
                .statusCode(200)  // Expecting a 200 OK response
                .body("data", notNullValue())  // Checking if response has valid data
                .extract()
                .response();

        System.out.println("API Response: " + apiResponse.prettyPrint());
    }
}
