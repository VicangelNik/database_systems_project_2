package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.impl;

import java.util.Set;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.CrimeReportDocument;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.CrimeRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class CrimeRepositoryImpl implements CrimeRepository {

  private final MongoTemplate mongoTemplate;

  @Override
  public long insertMany(Set<CrimeReportDocument> documents) {
    return mongoTemplate.insertAll(documents).size();
  }

  @Override
  public boolean isCollectionEmpty() {
    return mongoTemplate.count(new Query().limit(1), CrimeReportDocument.class) > 0;
  }
}
