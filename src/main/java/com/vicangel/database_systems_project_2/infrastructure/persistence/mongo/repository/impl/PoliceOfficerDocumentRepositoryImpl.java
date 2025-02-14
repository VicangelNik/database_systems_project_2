package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.impl;

import java.util.Set;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.PoliceOfficerDocument;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.PoliceOfficerDocumentRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class PoliceOfficerDocumentRepositoryImpl implements PoliceOfficerDocumentRepository {

  private final MongoTemplate mongoTemplate;

  @Override
  public PoliceOfficerDocument castUpvote(final long officerId, final long reportId) {
    return mongoTemplate.findAndModify(
      Query.query(Criteria.where(Fields.UNDERSCORE_ID).is(officerId)),
      new Update().addToSet("reportsUpvote", reportId),
      FindAndModifyOptions.options().returnNew(true),
      PoliceOfficerDocument.class
    );
  }

  public long insertMany(final Set<PoliceOfficerDocument> documents) {
    return mongoTemplate.insertAll(documents).size();
  }

  public boolean isCollectionEmpty() {
    return mongoTemplate.count(new Query().limit(1), PoliceOfficerDocument.class) > 0;
  }
}
