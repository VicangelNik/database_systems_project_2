package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("police_officers")
public record PoliceOfficerDocument(@Id Long id,
                                    String name,
                                    String email,
                                    Integer badgeNumber) {
}
