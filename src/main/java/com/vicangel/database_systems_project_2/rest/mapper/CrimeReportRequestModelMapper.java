
package com.vicangel.database_systems_project_2.rest.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Named;

import com.vicangel.database_systems_project_2.business.model.CrimeReport;
import com.vicangel.database_systems_project_2.rest.dto.request.CrimeReportRequestDTO;
import com.vicangel.database_systems_project_2.rest.dto.response.CrimeReportResponseDTO;

@Mapper(componentModel = ComponentModel.SPRING)
public abstract class CrimeReportRequestModelMapper {

  protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

  { // MongoDB stores times in UTC by default, and converts any local time representations into this form.
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  @Mapping(target = "dateReported", source = "request.dateReported", qualifiedByName = "parseDateStringToDate")
  @Mapping(target = "dateOCC", source = "request.dateOCC", qualifiedByName = "parseDateStringToDate")
  public abstract CrimeReport mapToModel(CrimeReportRequestDTO request) throws ParseException;

  @Named("parseDateStringToDate")
  Date parseDateStringToDate(String dateString) throws ParseException {
    if (dateString == null || dateString.isBlank()) return null;

    return simpleDateFormat.parse(dateString);
  }

  public abstract CrimeReportResponseDTO mapToCrimeReportResponse(CrimeReport report);
}
