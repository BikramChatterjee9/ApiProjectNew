package com.qa.gorest.test;

import com.qa.api.constraints.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;
import com.qa.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserTest extends BaseTest {

    @Test
    public void UpdateUser()
    {
        User user = new User("Jagmeet Guneta", StringUtils.getEmail(),"female","active");
        Response response = restClient.post(GO_REST_BASE_URL,GO_REST_END_POINT,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        int userId = response.jsonPath().get("id");
        System.out.print(userId);

        Response responseGet = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT+"/"+userId,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(responseGet.statusLine().contains("OK"));
        int userIdGet = responseGet.jsonPath().get("id");

        Assert.assertEquals(userId,userIdGet);

        user.setName("naveen");
        user.setStatus("inactive");

        Response responsePut =restClient.put(GO_REST_BASE_URL,GO_REST_END_POINT+"/"+userId,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(responsePut.statusLine().contains("OK"));
        String putResponseName = responsePut.jsonPath().get("name");
        String putResponseStatus = responsePut.jsonPath().get("status");

        Assert.assertEquals(putResponseName,"naveen");
        Assert.assertEquals(putResponseStatus,"inactive");

    }
}
