package com.vicangel.database_systems_project_2.business.model;

import java.util.Date;

import lombok.Builder;

@Builder
public record CrimeReport(Integer drNO,
                          Date dateReported,
                          Date dateOCC,
                          Integer timeOCC,
                          String areaID,
                          String areaName,
                          String reportingDistrictNo,
                          Integer part1_2,
                          Integer crimeCode1,
                          String crimeCodeDesc,
                          String mocodes,
                          Integer victimAge,
                          Character Sex,
                          Character victimDescent,
                          Integer premisCode,
                          String premisDesc,
                          Integer weaponUsedCode,
                          String weaponUsedDesc,
                          String status,
                          String statusDesc,
                          Integer crimeCode1Copy,
                          Integer crimeCode2,
                          Integer crimeCode3,
                          Integer crimeCode4,
                          String location,
                          Double latitude,
                          Double longitude) {
}
