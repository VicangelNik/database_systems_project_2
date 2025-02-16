package com.vicangel.database_systems_project_2.rest.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vicangel.database_systems_project_2.business.service.CrimeReportService;
import com.vicangel.database_systems_project_2.helper.ThrowingFunction;
import com.vicangel.database_systems_project_2.rest.dto.request.CrimeReportBatchRequest;
import com.vicangel.database_systems_project_2.rest.dto.response.CrimeReportResponseDTO;
import com.vicangel.database_systems_project_2.rest.dto.response.CrimeReportsResponse;
import com.vicangel.database_systems_project_2.rest.dto.response.InsertManyResponseDTO;
import com.vicangel.database_systems_project_2.rest.dto.response.ResultsPerDayResponse;
import com.vicangel.database_systems_project_2.rest.mapper.CrimeReportRequestModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Slf4j
final class CrimeReportController {

  private final CrimeReportService service;
  private final CrimeReportRequestModelMapper mapper;

  /**
   * Find the total number of reports per ‘Crm Cd” that occurred within a specified time range
   * and sort them in descending order.
   */
  @GetMapping(value = "/total/range", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CrimeReportsResponse> q1(
    @RequestParam("from") @DateTimeFormat(pattern = "MM/dd/yyyy") final Date fromDate,
    @RequestParam("to") @DateTimeFormat(pattern = "MM/dd/yyyy") final Date toDate) {

    final List<CrimeReportResponseDTO> crimeReports = service.q1(fromDate, toDate).stream()
      .map(mapper::mapToCrimeReportResponse)
      .toList();

    return new ResponseEntity<>(new CrimeReportsResponse(crimeReports), HttpStatus.OK);
  }

  /**
   * Find the total number of reports per day for a specific “Crm Cd” and time range.
   */
  @GetMapping(value = "/day/crime/{crimeCode1}/range", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ResultsPerDayResponse> q2(
    @PathVariable final Integer crimeCode1,
    @RequestParam("from") @DateTimeFormat(pattern = "MM/dd/yyyy") final Date fromDate,
    @RequestParam("to") @DateTimeFormat(pattern = "MM/dd/yyyy") final Date toDate
  ) {
    return new ResponseEntity<>(new ResultsPerDayResponse(service.q2(crimeCode1, fromDate, toDate)), HttpStatus.OK);
  }

  /**
   * Find the three most common crimes committed –regardless of code 1, 2, 3, and 4– per area for a specific day.
   */
  @GetMapping(value = "/crimes/day/three-common", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q3() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Find the two least common crimes committed in regard to a given time range.
   */
  @GetMapping(value = "/crimes/least-two-common", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q4() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Find the types of weapon that have been used for the same crime “Crm Cd” in more than one area
   */
  @GetMapping(value = "/crimes/weapon/least-two-common", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q5() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Find the fifty most upvoted reports for a specific day.
   */
  @GetMapping(value = "/reports/day/fifty-most-upvoted", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q6() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Find the fifty most active police officers, with regard to the total number of upvotes.
   */
  @GetMapping(value = "/officers/fifty-most-active/upvoted", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q7() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Find the top fifty police officers, with regard to the total number of areas for which they have upvoted reports.
   */
  @GetMapping(value = "/officers/fifty-most-active/areas", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q8() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Find all reports for which the same e-mail has been used for more than one badge numbers when casting an upvote.
   */
  @GetMapping(value = "/reports/officers/fifty-most-active/areas", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q9() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Find all areas for which a given name has casted a vote for a report involving it.
   */
  @GetMapping(value = "/officers/areas/vote", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> q10() {

    // TODO
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InsertManyResponseDTO> createNewReports(@RequestBody final CrimeReportBatchRequest request) {

    return new ResponseEntity<>(
      new InsertManyResponseDTO(service.insertMany(
        request.reports().stream().map(ThrowingFunction.throwingFunctionWrapper(mapper::mapToModel)).collect(Collectors.toSet()))
      ),
      HttpStatus.CREATED
    );
  }
}
