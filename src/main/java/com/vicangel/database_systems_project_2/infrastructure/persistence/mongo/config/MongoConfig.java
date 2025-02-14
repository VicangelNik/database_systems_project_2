package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "com.vicangel.database_systems_project_2.infrastructure.persistence.mongo")
class MongoConfig extends AbstractMongoClientConfiguration {

  @Value("${spring.data.mongodb.uri}")
  private String mongoUri;
  @Value("${spring.data.mongodb.database}")
  private String database;

  @Override
  @NonNull
  public MongoClient mongoClient() {

    ConnectionString connectionString = new ConnectionString(mongoUri);
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
      .applyConnectionString(connectionString)
      .build();

    return MongoClients.create(mongoClientSettings);
  }

  @Override
  protected boolean autoIndexCreation() {
    return true;
  }

  @Override
  @NonNull
  public Collection<String> getMappingBasePackages() {
    return Collections.singleton("com.vicangel.database_systems_project_2.infrastructure.persistence.mongo");
  }

  @Override
  @NonNull
  protected String getDatabaseName() {
    return database;
  }
}
