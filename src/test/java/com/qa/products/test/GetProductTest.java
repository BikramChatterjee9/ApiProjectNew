package com.qa.products.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.api.constraints.AuthType;
import com.qa.api.pojo.Products;
import com.qa.api.utils.JsonUtils;
import com.qa.base.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class GetProductTest extends BaseTest {

    @Test
    public void getAllProducts() throws JsonProcessingException {
        Response response =  restClient.get(PRODUCTS_BASE_URL,PRODUCTS_END_POINT,null,null, AuthType.NO_AUTH, ContentType.JSON);

        Assert.assertEquals(response.statusCode(),200);

        Products[] products = JsonUtils.deserelise(response, Products[].class);

        for(Products p:products)
        {
            System.out.print(p.getId());
            System.out.print(p.getTitle());
            System.out.print(p.getPrice());
            System.out.print(p.getDescription());
        }
    }

}
