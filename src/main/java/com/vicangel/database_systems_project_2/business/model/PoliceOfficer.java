package com.vicangel.database_systems_project_2.business.model;

import java.util.Set;

public record PoliceOfficer(Long id,
                            String name,
                            String email,
                            Integer badgeNumber,
                            Set<Long> reportsUpvote) {
}
