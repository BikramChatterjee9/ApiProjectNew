package com.qa.gorest.test;

import com.qa.api.constraints.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;
import com.qa.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateUserTest extends BaseTest {

    @Test
    public void createUser()
    {
        User user = new User("Jagmeet Guneta", StringUtils.getEmail(),"female","active");
        Response response = restClient.post(GO_REST_BASE_URL,GO_REST_END_POINT,user,null,null,AuthType.BEARER_TOKEN,ContentType.JSON);
        Assert.assertEquals(response.jsonPath().get("name"),"Jagmeet Guneta");
        Assert.assertNotNull(response.jsonPath().get("id"));
    }




}
