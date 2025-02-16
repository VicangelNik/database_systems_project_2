package com.vicangel.database_systems_project_2.rest.dto.request;

public record PoliceOfficerRequestDTO(Long id,
                                      String name,
                                      String email,
                                      Integer badgeNumber) {
}
