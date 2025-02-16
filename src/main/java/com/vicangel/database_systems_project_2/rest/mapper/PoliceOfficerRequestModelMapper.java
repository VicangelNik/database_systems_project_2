package com.vicangel.database_systems_project_2.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

import com.vicangel.database_systems_project_2.business.model.PoliceOfficer;
import com.vicangel.database_systems_project_2.rest.dto.request.PoliceOfficerRequestDTO;

@Mapper(componentModel = ComponentModel.SPRING)
public interface PoliceOfficerRequestModelMapper {

  PoliceOfficer mapToModel(PoliceOfficerRequestDTO request);
}
