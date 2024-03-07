package com.petstore.qa.common;

import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;

public abstract class BaseStepDefinitions {

  public void validateResponse(int httpResponseCode, String schemaName) {

    if (schemaName == null) {
      SerenityRest.lastResponse().then().assertThat().statusCode(httpResponseCode);
    } else {
      SerenityRest.lastResponse()
          .then()
          .assertThat()
          .statusCode(httpResponseCode)
          .and()
          .contentType("application/json")
          .and()
          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaName));
    }
  }
}
