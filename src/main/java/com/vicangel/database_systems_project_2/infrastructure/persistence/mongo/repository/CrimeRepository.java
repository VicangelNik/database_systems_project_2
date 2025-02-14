package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository;

import java.util.Set;

import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.CrimeReportDocument;

public interface CrimeRepository {

  long insertMany(final Set<CrimeReportDocument> documents);
}
