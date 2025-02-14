package com.vicangel.database_systems_project_2.rest.dto.request;

import java.util.Set;

public record CrimeReportBatchRequest(Set<CrimeReportRequestDTO> reports) {
}
