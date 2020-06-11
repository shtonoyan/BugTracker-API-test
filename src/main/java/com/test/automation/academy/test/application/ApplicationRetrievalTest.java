package com.test.automation.academy.test.application;

import com.test.automation.academy.generator.ApplicationGenerator;
import com.test.automation.academy.webservice.client.ApplicationClient;
import com.test.automation.academy.webservice.payload.Application;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ApplicationRetrievalTest {
    private final int application_id = ThreadLocalRandom.current().nextInt(1, 5);
    private final Application application = ApplicationGenerator.generateValidApplication();
    private final ApplicationClient client = new ApplicationClient();
    private final String owner = UUID.randomUUID().toString();
    private final String name = UUID.randomUUID().toString();


    @BeforeClass
    public void verifyApplicationCreation() {
        System.out.println(application_id);
        application.setApplication_id(application_id);
        application.setOwner(owner);
        application.setName(name);
    }

    @Test
    public void verifyApplicationIsPresent() {
        RestAssured.defaultParser = Parser.JSON;
        Application app = client.getApplicationAsResponse(application_id, 200);
        Assert.assertEquals(app.getOwner(), owner);
        Assert.assertEquals(app.getName(), name);
    }
}
