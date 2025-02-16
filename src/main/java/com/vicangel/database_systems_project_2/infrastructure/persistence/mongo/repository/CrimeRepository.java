package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vicangel.database_systems_project_2.common.dto.ResultPerDayDTO;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.CrimeReportDocument;

public interface CrimeRepository {

  long insertMany(final Set<CrimeReportDocument> documents);

  boolean isCollectionEmpty();

  List<CrimeReportDocument> q1(Date fromDate, Date toDate);

  List<ResultPerDayDTO> q2(Integer crimeCode1, Date fromDate, Date toDate);
}
