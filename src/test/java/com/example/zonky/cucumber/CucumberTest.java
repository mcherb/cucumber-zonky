package com.example.zonky.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features"},
        plugin = {"pretty", "html:build/cucumber.html", "json:build/cucumber.json"}
)
public class CucumberTest {
}
