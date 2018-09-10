/*
Copyright 2015-Present Entando Inc. (http://www.entando.com) All rights reserved.
This library is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 2.1 of the License, or (at your option)
any later version.
This library is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.
 */
package org.entando.selenium.contracttests;

import au.com.dius.pact.consumer.ConsumerPactBuilder;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerificationResult;
import au.com.dius.pact.consumer.dsl.PactDslRequestWithPath;
import au.com.dius.pact.consumer.dsl.PactDslResponse;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.MockProviderConfig;
import au.com.dius.pact.model.RequestResponsePact;
import org.entando.selenium.pages.*;
import org.entando.selenium.utils.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static au.com.dius.pact.consumer.ConsumerPactRunnerKt.runConsumerTest;
import static org.entando.selenium.contracttests.PactUtil.*;

/**
 * This class perform a test to add a user
 *
 * @version 1.01
 */
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserAddProvider", port = "8080")
public class UserAddConsumerTest extends UsersTestBase {
    private Random generator = new Random();
    private int randomNumber = generator.nextInt(9999);
    private String username = "1SLNM_TEST_" + randomNumber;
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;

    @Autowired
    public DTUsersPage dTUsersPage;

    @Autowired
    public DTUserAddPage dTUserAddPage;

    @Autowired
    public DTUserProfileTypePage dtUserProfileTypePage;

    @Autowired
    public DTUserProfileTypeAddPage dtUserProfileTypeAddPage;

    @BeforeAll
    public void setupSessionAndNavigateToUserManagement (){
        PactDslWithProvider builder = ConsumerPactBuilder.consumer("LoginConsumer").hasPactWith("LoginProvider");
        PactDslResponse accessTokenResponse = buildGetAccessToken(builder);
        PactDslResponse getUsersResponse = PactUtil.buildGetUsers(accessTokenResponse,1,1);
        getUsersResponse = PactUtil.buildGetUsers(getUsersResponse,1,10);
        PactDslResponse getPagesResponse = buildGetPages(getUsersResponse);
        PactDslResponse getPageStatusResponse = buildGetPageStatus(getPagesResponse);
        PactDslResponse getWidgetsResponse = buildGetWidgets(getPageStatusResponse);
        PactDslResponse getGroupsResponse = buildGetGroups(getWidgetsResponse);
        PactDslResponse getPageModelsResponse = buildGetPageModels(getGroupsResponse);
        PactDslResponse getLanguagesResponse = buildGetLanguages(getPageModelsResponse);
        PactDslResponse getProfileTypesResponse = buildGetProfileTypes(getLanguagesResponse);
        MockProviderConfig config = MockProviderConfig.httpConfig("localhost", 8080);
        PactVerificationResult result = runConsumerTest(getProfileTypesResponse.toPact(), config, mockServer -> {
            login();
            //Navigation to the page
            dTDashboardPage.SelectSecondOrderLinkWithSleep("User Management", "Users");
            Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
        });
    }
    @Pact(provider = "UserAddProvider", consumer = "UserAddConsumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        PactDslResponse postUserResponse = buildPostUser(builder);
        PactDslResponse getUsersResponse = buildGetUsers(postUserResponse,1,10);
        PactDslResponse getProfileTypesResponse = buildGetProfileTypes(getUsersResponse);
        return getProfileTypesResponse.toPact();
    }
    public static PactDslResponse buildGetUsers(PactDslResponse builder, int page, int pageSize) {
        PactDslRequestWithPath request = builder.uponReceiving("The User Query GET Interaction")
                .path("/entando/api/users")
                .method("GET")
                .matchQuery("page", "\\d+", "" + page)
                .matchQuery("pageSize",  "\\d+", ""+pageSize);
        return standardResponse(request, "{\"payload\":[{\"username\":\"UNIMPORTANT\",\"registration\":\"2018-08-31 00:00:00\",\"lastLogin\":null,\"lastPasswordChange\":null,\"status\":\"active\",\"accountNotExpired\":true,\"credentialsNotExpired\":true,\"profileType\":null,\"profileAttributes\":{},\"maxMonthsSinceLastAccess\":-1,\"maxMonthsSinceLastPasswordChange\":-1}],\"errors\":[],\"metaData\":{\"page\":1,\"pageSize\":1,\"lastPage\":1,\"totalItems\":1,\"sort\":\"username\",\"direction\":\"ASC\",\"filters\":[],\"additionalParams\":{}}}");
    }

    private PactDslResponse buildPostUser(PactDslWithProvider builder) {
        PactDslRequestWithPath optionsRequest = builder
                .uponReceiving("The User Add OPTIONS Interaction")
                .path("/entando/api/users/")
                .method("OPTIONS")
                .headers("Access-control-request-method", "POST")
                .body("");
        PactDslResponse optionsResponse = optionsResponse(optionsRequest);
        PactDslRequestWithPath request = optionsResponse
                .uponReceiving("The User Add POST Interaction")
                //TODO add the expectation for the incoming data
                .path("/entando/api/users/")
                .method("POST");
        return standardResponse(request, "{\"payload\":{\"username\":\"1SLNM_TEST_2354\",\"registration\":\"2018-09-03 00:00:00\",\"lastLogin\":null,\"lastPasswordChange\":null,\"status\":\"active\",\"accountNotExpired\":true,\"credentialsNotExpired\":true,\"profileType\":null,\"profileAttributes\":{},\"maxMonthsSinceLastAccess\":-1,\"maxMonthsSinceLastPasswordChange\":-1},\"errors\":[],\"metaData\":{}}");
    }

    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons

        //Final page title
        String pageTitle = "Add";

        //Usernames to set
        String falseUsername = "1SLNM_TEST-*-" + randomNumber;

        //Passwords to set
        String password = Double.toString(Math.pow(10, super.minPasswordLength - 1) + randomNumber);
        String falsePassword = Integer.toString((int) Math.round(Math.pow(10, super.minPasswordLength - 2) + randomNumber));
        String falseConfirmPassword = Integer.toString((int) Math.round(Math.pow(10, super.minPasswordLength - 3) + randomNumber));

        //Status string
        String statusString = " Active";


        /*
            Actions and asserts
        */

        dTUsersPage.getAddButton().click();

        Utils.waitUntilIsVisible(driver, dTUserAddPage.getPageTitle());


        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTUserAddPage.getPageTitle().getText());

        //Asserts the presence of the HELP button
        dTUserAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTUserAddPage.getTooltip());
        Assert.assertTrue(dTUserAddPage.getTooltip().isDisplayed());

        //Verify "field required" warning
        dTUserAddPage.setUsernameField(falseUsername);
        dTUserAddPage.setPasswordField(falsePassword);
        dTUserAddPage.setPasswordConfirmField(falseConfirmPassword);
        dTUserAddPage.getProfileType().click();
        dTUserAddPage.getUsernameField().click();
        Logger.getGlobal().info(falsePassword);
        Assert.assertTrue(dTUserAddPage.getUsernameFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getPasswordFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getPasswordConfirmFieldError().isDisplayed());
        Assert.assertTrue(dTUserAddPage.getProfileTypeError().isDisplayed());

        //Verify Switch
        Assert.assertFalse(dTUserAddPage.getStatusSwitch().isOn());

        //Verify Save buttons are disable
        Assert.assertFalse(dTUserAddPage.getSaveButton().isEnabled());


        //Compilation of the page
        dTUserAddPage.setUsernameField(this.username);
        dTUserAddPage.setPasswordField(password);
        dTUserAddPage.setPasswordConfirmField(password);
        dTUserAddPage.getProfileTypeSelect().selectByVisibleText(profileType);
        if (super.status) {
            dTUserAddPage.getStatusSwitch().setOn();
        } else {
            dTUserAddPage.getStatusSwitch().setOff();
        }


        //Save and come back to the Users list
        Assert.assertTrue(dTUserAddPage.getSaveButton().isEnabled());
        dTUserAddPage.getSaveButton().click();

//        Utils.waitUntilIsPresent(driver, dTUsersPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTUsersPage.spinnerTag);



        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if (Logger.getGlobal().getLevel() == Level.INFO) {
//            sleep(SLEEPTIME);
        }
        /** End Debug code**/

    }

}//end class
