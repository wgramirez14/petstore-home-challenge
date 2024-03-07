package com.petstore.qa.services.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;

@Getter
public abstract class BaseService {

  protected RequestSpecification httpRequest;
  protected ObjectMapper objectMapper;
  public static EnvironmentSpecificConfiguration properties;

  protected BaseService() {
    objectMapper = new ObjectMapper();
    RestAssured.baseURI = properties.getProperty("api.base.url");
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    httpRequest =
        SerenityRest.given().log().all().header("Content-Type", "application/json;charset=utf-8");
  }
}
