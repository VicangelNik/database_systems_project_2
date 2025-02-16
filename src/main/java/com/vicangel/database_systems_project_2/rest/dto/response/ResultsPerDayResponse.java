package com.vicangel.database_systems_project_2.rest.dto.response;

import java.util.List;

import com.vicangel.database_systems_project_2.common.dto.ResultPerDayDTO;

public record ResultsPerDayResponse(List<ResultPerDayDTO> resultsPerDay) {
}
