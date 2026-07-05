package com.qa.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class BaseTest {

    protected static RestClient restClient;

    protected static String GO_REST_BASE_URL = "https://gorest.co.in";

    protected static String GO_REST_END_POINT = "/public/v2/users";

    protected static String CONTACTS_BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";

    protected static String CONTACTS_END_POINT = "/contacts";

    protected static String CONTACTS_LOGIN_END_POINT = "/users/login";

    protected static String PRODUCTS_BASE_URL = "https://fakestoreapi.com";

    protected static String PRODUCTS_END_POINT = "/products";

    @BeforeSuite
    public void setupAllureReport()
    {
        RestAssured.filters(new AllureRestAssured());
    }

    @BeforeTest
    public void setup()
    {
        restClient=new RestClient();
    }
}
