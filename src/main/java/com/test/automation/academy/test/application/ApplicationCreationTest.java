package com.test.automation.academy.test.application;

import com.test.automation.academy.generator.ApplicationGenerator;
import com.test.automation.academy.webservice.client.ApplicationClient;
import com.test.automation.academy.webservice.payload.Application;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ApplicationCreationTest {
  private final int application_id = new Random().nextInt(20);
  private Application application = ApplicationGenerator.generateValidApplication();
  private ApplicationClient client = new ApplicationClient();


  @Test
  public void verifyApplicationCreation() {
    int application_id = ThreadLocalRandom.current().nextInt(1, 5);
    System.out.println(application_id);
    application.setApplication_id(application_id);
    application.setOwner(UUID.randomUUID().toString());
    application.setName(UUID.randomUUID().toString());
    Assert.assertEquals(client.createApplication(application).statusCode(), 201);
  }

  @Test
  public void verifyApplicationIsPresent() {
    RestAssured.defaultParser = Parser.JSON;
    Application app = client.getApplicationAsResponse(application_id, 200);
    Assert.assertEquals(app.getApplication_id().intValue(), application_id);
  }

}
