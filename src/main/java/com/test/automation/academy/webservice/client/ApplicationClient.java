package com.test.automation.academy.webservice.client;

import com.google.gson.Gson;
import com.test.automation.academy.webservice.payload.Application;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.with;

public class ApplicationClient {

    public Response createApplication(Application application) {
        RestAssured.baseURI = "http://localhost:8080";
        Gson gson = new Gson();
        return RestAssured
                .given()
                .contentType("application/json")
                .body(gson.toJson(application))
                .post("/v1/application");
    }

    public void updateApplication(Application application) {

    }

    public void getApplicationAsEntity(int applicationId) {

    }

    public Application getApplicationAsResponse(int applicationId, int expectedStatusCode) {
        RestAssured.baseURI = "http://localhost:8080";
        Application application = RestAssured
                .given()
                .log()
                .all()
                .get("/v1/application/" + applicationId).then().extract().as(Application.class);

        return application;
    }

    public Response deleteApplication(int applicationIds) {
        return RestAssured
                .given()
                .log().all()
                .contentType("application/json")
                .delete("/v1/application/" + applicationIds);
    }
}
