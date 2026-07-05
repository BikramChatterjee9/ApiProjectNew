package com.qa.gorest.test;

import com.qa.api.constraints.AuthType;
import com.qa.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserTest extends BaseTest {

    @Test
    public void getAllUsers()
    {
        Response response = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }

    @Test
    public void getAllUsersQueryParam()
    {
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("name","Subhasini Agarwal");
        queryMap.put("status","active");
        Response response = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT,queryMap,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }

    @Test
    public void getUsersPathParam()
    {
        int userId=8533479;
        Response response = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT+"/"+userId,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
        int id = response.jsonPath().get("id");
        System.out.print(id);
        Assert.assertEquals(id, userId);
    }




}
