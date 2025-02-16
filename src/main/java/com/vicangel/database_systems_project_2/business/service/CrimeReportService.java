package com.vicangel.database_systems_project_2.business.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.vicangel.database_systems_project_2.business.model.CrimeReport;
import com.vicangel.database_systems_project_2.common.dto.ResultPerDayDTO;
import com.vicangel.database_systems_project_2.common.dto.ResultQ3DTO;

public interface CrimeReportService {

  long insertMany(Set<CrimeReport> reports);

  List<CrimeReport> q1(Date fromDate, Date toDate);

  List<ResultPerDayDTO> q2(Integer crimeCode1, Date fromDate, Date toDate);

  List<ResultQ3DTO> q3(Date day);
}
