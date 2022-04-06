package com.example.zonky.cucumber;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.Connection;
import java.sql.Statement;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

public class DbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            EmbeddedPostgres pg = EmbeddedPostgres.builder()
                    .setCleanDataDirectory(true)
                    .setPort(55555)
                    .start();
            initAnyDatabase(pg);
        } catch (Exception e) {
            e.printStackTrace();
            rethrowRuntimeException(e);
        }
    }

    private void initAnyDatabase(EmbeddedPostgres pg) {
        try (Connection connection = pg.getPostgresDatabase().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE ROLE mimita WITH LOGIN SUPERUSER PASSWORD ''");
            statement.execute("CREATE DATABASE mimita OWNER mimita ENCODING = 'utf8'");
        } catch (Exception e) {
            e.printStackTrace();
            rethrowRuntimeException(e);
        }
    }
}
