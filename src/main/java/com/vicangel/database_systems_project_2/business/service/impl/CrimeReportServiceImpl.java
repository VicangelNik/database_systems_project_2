package com.vicangel.database_systems_project_2.business.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vicangel.database_systems_project_2.business.mapper.CrimeReportModelEntityMapper;
import com.vicangel.database_systems_project_2.business.model.CrimeReport;
import com.vicangel.database_systems_project_2.business.service.CrimeReportService;
import com.vicangel.database_systems_project_2.common.dto.ResultPerDayDTO;
import com.vicangel.database_systems_project_2.common.dto.ResultQ3DTO;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.CrimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
final class CrimeReportServiceImpl implements CrimeReportService {

  private final CrimeRepository repository;
  private final CrimeReportModelEntityMapper mapper;

  @Override
  public long insertMany(Set<CrimeReport> reports) {
    return repository.insertMany(reports.stream().map(mapper::mapToDocument).collect(Collectors.toSet()));
  }

  @Override
  public List<CrimeReport> q1(Date fromDate, Date toDate) {
    return repository.q1(fromDate, toDate).stream().map(mapper::mapToModel).toList();
  }

  @Override
  public List<ResultPerDayDTO> q2(Integer crimeCode1, Date fromDate, Date toDate) {
    return repository.q2(crimeCode1, fromDate, toDate);
  }

  @Override
  public List<ResultQ3DTO> q3(Date day) {
    return repository.q3(day);
  }
}
