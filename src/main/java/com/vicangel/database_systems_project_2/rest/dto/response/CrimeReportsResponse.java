package com.vicangel.database_systems_project_2.rest.dto.response;

import java.util.List;

public record CrimeReportsResponse(List<CrimeReportResponseDTO> reports) {
}
