package com.context;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Value("#{systemProperties.login}")
    private String login;
    @Value("#{systemProperties.password}")
    private String password;
    @Value("#{systemProperties.host}")
    private String host;
    @Value("#{systemProperties.db}")
    private String db;

    @Override
    public String getDatabaseName() {
        return db;
    }

    @Override
    public UserCredentials getUserCredentials() {
        if (login != null && password != null && !login.isEmpty() && !password.isEmpty()) {
            return new UserCredentials(login, password);
        }
        return null;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Mongo(host);
    }

    @Override
    public String getMappingBasePackage() {
        return "com.context.model";
    }
}
