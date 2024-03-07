package com.petstore.qa.steps;

import com.petstore.qa.services.common.BaseService;
import io.cucumber.java.Before;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

public class Hooks {

  private EnvironmentVariables env;

  @Before
  public void setUp() {
    BaseService.properties = EnvironmentSpecificConfiguration.from(env);
  }
}
