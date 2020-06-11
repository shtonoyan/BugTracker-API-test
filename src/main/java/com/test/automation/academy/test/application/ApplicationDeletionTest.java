package com.test.automation.academy.test.application;

import com.test.automation.academy.generator.ApplicationGenerator;
import com.test.automation.academy.webservice.client.ApplicationClient;
import com.test.automation.academy.webservice.payload.Application;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ApplicationDeletionTest {
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
    public void verifyApplicationDeletion() {
        ApplicationClient client = new ApplicationClient();
        Response response = client.deleteApplication(application_id);
        Assert.assertEquals(response.statusCode(), 204);
    }

}
