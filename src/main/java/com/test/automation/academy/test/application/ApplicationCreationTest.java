package com.test.automation.academy.test.application;

import com.test.automation.academy.generator.ApplicationGenerator;
import com.test.automation.academy.webservice.client.ApplicationClient;
import com.test.automation.academy.webservice.payload.Application;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class ApplicationCreationTest {
    private final int application_id = ThreadLocalRandom.current().nextInt(1, 5);
    private final Application application = ApplicationGenerator.generateValidApplication();
    private final ApplicationClient client = new ApplicationClient();


    @Test
    public void verifyApplicationCreation() {
        application.setApplication_id(application_id);
        application.setOwner(UUID.randomUUID().toString());
        application.setName(UUID.randomUUID().toString());
        Assert.assertEquals(client.createApplication(application).statusCode(), 201);
    }
}
