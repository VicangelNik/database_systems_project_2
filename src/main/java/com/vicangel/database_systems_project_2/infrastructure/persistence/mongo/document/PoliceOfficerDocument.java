package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;

@Document("police_officers")
@Builder
public record PoliceOfficerDocument(@Id Long id,
                                    String name,
                                    String email,
                                    Integer badgeNumber,
                                    Set<Long> reportsUpvote) {
}
