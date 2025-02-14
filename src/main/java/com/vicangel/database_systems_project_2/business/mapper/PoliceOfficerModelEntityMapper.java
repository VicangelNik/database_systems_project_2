package com.vicangel.database_systems_project_2.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import com.vicangel.database_systems_project_2.business.model.PoliceOfficer;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.PoliceOfficerDocument;

@Mapper(componentModel = ComponentModel.SPRING)
public interface PoliceOfficerModelEntityMapper {

  PoliceOfficer mapToModel(PoliceOfficerDocument document);

  PoliceOfficerDocument mapToDocument(PoliceOfficer model);
}
