package com.vicangel.database_systems_project_2.infrastructure.importdata;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;
import com.vicangel.database_systems_project_2.helper.ThrowingFunction;
import com.vicangel.database_systems_project_2.infrastructure.importdata.dto.CrimeReportCarrierDTO;
import com.vicangel.database_systems_project_2.infrastructure.importdata.mapper.CrimeReportCarrierDocumentMapper;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.CrimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
final class CrimeReportDataLoader implements CommandLineRunner {

  private final CrimeRepository repository;
  private final CrimeReportCarrierDocumentMapper mapper;
  @Value("classpath:Crime_Data_from_2020_to_Present_20250214.csv")
  private Resource CSV_FILE;
  private static final char COMMA_DELIMITER = ',';
  private static final int READ_LIMIT = 50_000;

  @Override
  public void run(String... args) throws IOException {
    if (repository.isCollectionEmpty()) return;
    log.info("Start importing crime reports...");

    repository.insertMany(
      this.readFile()
        .map(ThrowingFunction.throwingFunctionWrapper(mapper::mapToDocument))
        .limit(READ_LIMIT)
        .collect(Collectors.toSet()));

    log.info("Importing crime reports finished.");
  }

  private Stream<CrimeReportCarrierDTO> readFile() throws IOException {
    log.info("Reading file: {}", CSV_FILE.getFilename());

    try (InputStream inputStream = CSV_FILE.getInputStream();
         MappingIterator<CrimeReportCarrierDTO> iterator = new CsvMapper()
           .readerFor(CrimeReportCarrierDTO.class)
           .with(getCsvSchema())
           .readValues(inputStream)) {
      return iterator.readAll().stream();
    }
  }

  private static CsvSchema getCsvSchema() {
    return CsvSchema.builder()
      .addColumn("drNO", ColumnType.NUMBER)
      .addColumn("dateReported", ColumnType.STRING)
      .addColumn("dateOCC", ColumnType.STRING)
      .addColumn("timeOCC", ColumnType.NUMBER)
      .addColumn("areaID", ColumnType.STRING)
      .addColumn("areaName", ColumnType.STRING)
      .addColumn("reportingDistrictNo", ColumnType.STRING)
      .addColumn("part1_2", ColumnType.NUMBER)
      .addColumn("crimeCode1", ColumnType.NUMBER)
      .addColumn("crimeCodeDesc", ColumnType.STRING)
      .addColumn("mocodes", ColumnType.STRING)
      .addColumn("victimAge", ColumnType.NUMBER)
      .addColumn("victimSex", ColumnType.STRING_OR_LITERAL)
      .addColumn("victimDescent", ColumnType.STRING_OR_LITERAL)
      .addColumn("premisCode", ColumnType.NUMBER)
      .addColumn("premisDesc", ColumnType.STRING)
      .addColumn("weaponUsedCode", ColumnType.NUMBER)
      .addColumn("weaponUsedDesc", ColumnType.NUMBER)
      .addColumn("status", ColumnType.STRING)
      .addColumn("statusDesc", ColumnType.STRING)
      .addColumn("crimeCode1Copy", ColumnType.STRING)
      .addColumn("crimeCode2", ColumnType.NUMBER)
      .addColumn("crimeCode3", ColumnType.NUMBER)
      .addColumn("crimeCode4", ColumnType.NUMBER)
      .addColumn("location", ColumnType.NUMBER)
      .addColumn("crossStreet", ColumnType.STRING)
      .addColumn("latitude", ColumnType.NUMBER)
      .addColumn("longitude", ColumnType.NUMBER)

      .build()
      .withHeader()
      .withColumnSeparator(COMMA_DELIMITER);
  }
}
