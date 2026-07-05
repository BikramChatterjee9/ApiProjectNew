package com.qa.api.client;

import com.qa.api.constraints.AuthType;
import com.qa.api.manager.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestClient {

    ResponseSpecification responseSpec200 = expect().statusCode(200);
    ResponseSpecification responseSpec201 = expect().statusCode(201);
    ResponseSpecification responseSpec204 = expect().statusCode(204);
    ResponseSpecification responseSpec201_200 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));


    public RequestSpecification SetupRequest(String baseURL, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = given().baseUri(baseURL).log().all()
                .contentType(contentType)
                .accept(contentType);

        switch (authType)
        {
            case BEARER_TOKEN:
                request.header("Authorization","Bearer "+ ConfigManager.get("bearerToken"));
                break;
            case NO_AUTH:
                System.out.print("No auth");
                break;
            default:
                break;
        }
        return request;
    }

    public void applyParams(RequestSpecification request, Map<String,String> queryParams,
                            Map<String,String> pathParams)
    {
        if(queryParams!=null)
        {
            request.queryParams(queryParams);
        }
        if(pathParams!=null)
        {
            request.pathParams(queryParams);
        }
    }

    public Response get(String baseURL, String endPoint, Map<String,String> queryParams,
                        Map<String,String> pathParams, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = SetupRequest(baseURL,authType,contentType);
        applyParams(request, queryParams,pathParams);
        return request.get(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public <T>Response post(String baseURL, String endPoint, T body,
                         Map<String,String> queryParams,
                         Map<String,String> pathParams, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = SetupRequest(baseURL,authType,contentType);
        applyParams(request, queryParams,pathParams);
        return request.body(body).post(endPoint).then().log().all().spec(responseSpec201).extract().response();
    }

    public <T>Response put(String baseURL, String endPoint, T body,
                            Map<String,String> queryParams,
                            Map<String,String> pathParams, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = SetupRequest(baseURL,authType,contentType);
        applyParams(request, queryParams,pathParams);
        return request.body(body).put(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public <T>Response patch(String baseURL, String endPoint, T body,
                           Map<String,String> queryParams,
                           Map<String,String> pathParams, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = SetupRequest(baseURL,authType,contentType);
        applyParams(request, queryParams,pathParams);
        return request.body(body).patch(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public Response delete(String baseURL, String endPoint,
                            Map<String,String> queryParams,
                            Map<String,String> pathParams, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = SetupRequest(baseURL,authType,contentType);
        applyParams(request, queryParams,pathParams);
        return request.patch(endPoint).then().log().all().spec(responseSpec204).extract().response();
    }


}
