package com.petstore.qa.runner;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/*@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    plugin = {"pretty"},
    features = "src/test/resources/features")*/

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value =
        "io.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/test-results/timeline")
public class TestRunner {}
