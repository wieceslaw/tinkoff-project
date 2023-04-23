package ru.tinkoff.edu.java.scrapper;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class IntegrationEnvironment {
    private static final PostgreSQLContainer<?> DB_CONTAINER;
    private static final DataSource DATA_SOURCE;
    private static final String MASTER_PATH = "migrations/master.xml";

    static {
        DB_CONTAINER = new PostgreSQLContainer<>("postgres:15");
        DB_CONTAINER.start();

        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(DB_CONTAINER.getDriverClassName());
            dataSource.setUrl(DB_CONTAINER.getJdbcUrl());
            dataSource.setUsername(DB_CONTAINER.getUsername());
            dataSource.setPassword(DB_CONTAINER.getPassword());
            DATA_SOURCE = dataSource;

            Connection connection = getConnection();
            Path path = new File("../").toPath().toAbsolutePath().normalize();
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new liquibase.Liquibase(MASTER_PATH,
                    new DirectoryResourceAccessor(path), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected static DataSource getDataSource() {
        return DATA_SOURCE;
    }

    protected static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    @DynamicPropertySource
    static void jdbcProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", DB_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", DB_CONTAINER::getUsername);
        registry.add("spring.datasource.password", DB_CONTAINER::getPassword);
        registry.add("spring.liquibase.enabled", () -> false);
    }
}
