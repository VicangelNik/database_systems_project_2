package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository;

import java.util.Optional;
import java.util.Set;

import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.PoliceOfficerDocument;

public interface PoliceOfficerDocumentRepository {

  PoliceOfficerDocument castUpvote(long officerId, long reportId);

  long insertMany(final Set<PoliceOfficerDocument> documents);

  boolean isCollectionEmpty();

  Optional<PoliceOfficerDocument> findOfficer(long officerId);
}
