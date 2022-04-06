package com.example.zonky.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest(
        properties = "spring.datasource.url=jdbc:postgresql://localhost:55555/mimita?currentSchema=mimita"
)
@ContextConfiguration(initializers = DbInitializer.class)
@CucumberContextConfiguration
public class CucumberTestDefinitions {

    @Autowired
    private AnyEntityRepository repository;

    @Given("^Create entities$")
    public void saveData() {

        System.out.println("Create entities");
        AnyEntity entity1 = new AnyEntity();
        entity1.setId(1L);
        entity1.setName("E1");

        AnyEntity entity2 = new AnyEntity();
        entity2.setId(2L);
        entity2.setName("E2");

        AnyEntity entity3 = new AnyEntity();
        entity3.setId(3L);
        entity3.setName("E3");

        repository.saveAll(List.of(entity1, entity2, entity3));
    }

    @Then("^Count entities equals 3$")
    public void assertDataCount() {
        System.out.println("Assert entities");
        Assertions.assertThat(repository.count()).isEqualTo(3);
    }

}
