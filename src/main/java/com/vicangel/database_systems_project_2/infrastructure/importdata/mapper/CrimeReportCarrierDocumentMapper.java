
package com.vicangel.database_systems_project_2.infrastructure.importdata.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Named;

import com.vicangel.database_systems_project_2.infrastructure.importdata.dto.CrimeReportCarrierDTO;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.CrimeReportDocument;

@Mapper(componentModel = ComponentModel.SPRING)
public abstract class CrimeReportCarrierDocumentMapper {

  protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

  { // MongoDB stores times in UTC by default, and converts any local time representations into this form.
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
  }

  @Mapping(target = "dateReported", source = "dto.dateReported", qualifiedByName = "parseDateStringToDate")
  @Mapping(target = "dateOCC", source = "dto.dateOCC", qualifiedByName = "parseDateStringToDate")
  @Mapping(target = "upvoteByOfficers", ignore = true)
  public abstract CrimeReportDocument mapToDocument(CrimeReportCarrierDTO dto) throws ParseException;

  @Named("parseDateStringToDate")
  Date parseDateStringToDate(String dateString) throws ParseException {
    if (dateString == null || dateString.isBlank()) return null;

    return simpleDateFormat.parse(dateString);
  }
}
