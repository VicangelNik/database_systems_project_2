package com.vicangel.database_systems_project_2.rest.dto.response;

import java.util.Set;

public record UpvoteResponseDTO(Long id,
                                String name,
                                String email,
                                Integer badgeNumber,
                                Set<Long> reportsUpvote) {
}
