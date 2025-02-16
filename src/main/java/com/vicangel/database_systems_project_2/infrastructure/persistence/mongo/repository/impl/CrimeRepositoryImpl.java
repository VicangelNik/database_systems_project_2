package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.vicangel.database_systems_project_2.common.dto.ResultPerDayDTO;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.CrimeReportDocument;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.CrimeRepository;
import lombok.RequiredArgsConstructor;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

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

  @Override
  public List<CrimeReportDocument> q1(Date fromDate, Date toDate) {
    final MatchOperation matchStage = match(new Criteria("dateReported").gte(fromDate).lte(toDate));
    final SortOperation sortStage = sort(Sort.Direction.DESC, "dateReported");
    final var aggregation = newAggregation(matchStage, sortStage);
    return mongoTemplate.aggregate(aggregation, "crime_reports", CrimeReportDocument.class).getMappedResults();
  }

  @Override
  public List<ResultPerDayDTO> q2(Integer crimeCode1, Date fromDate, Date toDate) {
    final MatchOperation matchStage = match(new Criteria("dateReported").gte(fromDate).lte(toDate)
                                              .and("crimeCode1").is(crimeCode1));
    final GroupOperation groupStage = group("day", "month", "year", "dateReported").count().as("count");
    final ProjectionOperation projectStage = Aggregation.project("dateReported", "count")
      .and(DateOperators.IsoDayOfWeek.isoDayOfWeek("dateReported")).as("day")
      .and(DateOperators.Month.monthOf("dateReported")).as("month")
      .and(DateOperators.Year.yearOf("dateReported")).as("year");

    final var aggregation = newAggregation(matchStage, groupStage, projectStage);
    return mongoTemplate.aggregate(aggregation, "crime_reports", ResultPerDayDTO.class).getMappedResults();
  }
}
