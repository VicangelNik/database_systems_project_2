package com.vicangel.database_systems_project_2.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import com.vicangel.database_systems_project_2.business.model.CrimeReport;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.CrimeReportDocument;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CrimeReportModelEntityMapper {

  CrimeReport mapToModel(CrimeReportDocument document);

  CrimeReportDocument mapToDocument(CrimeReport model);
}
