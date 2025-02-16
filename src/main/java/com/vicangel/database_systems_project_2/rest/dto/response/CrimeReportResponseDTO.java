package com.vicangel.database_systems_project_2.rest.dto.response;

import java.util.Set;

public record CrimeReportResponseDTO(Integer drNO,
                                     String dateReported,
                                     String dateOCC,
                                     Integer timeOCC,
                                     String areaID,
                                     String areaName,
                                     String reportingDistrictNo,
                                     Integer part1_2,
                                     Integer crimeCode1,
                                     String crimeCodeDesc,
                                     String mocodes,
                                     Integer victimAge,
                                     Character victimSex,
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
                                     String crossStreet,
                                     Double latitude,
                                     Double longitude,
                                     Set<Long> upvoteByOfficers) {
}
