package com.vicangel.database_systems_project_2.rest.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vicangel.database_systems_project_2.business.service.PoliceOfficerService;
import com.vicangel.database_systems_project_2.rest.dto.request.InsertManyRequestDTO;
import com.vicangel.database_systems_project_2.rest.dto.request.UpvoteRequestDTO;
import com.vicangel.database_systems_project_2.rest.dto.response.InsertManyResponseDTO;
import com.vicangel.database_systems_project_2.rest.dto.response.UpvoteResponseDTO;
import com.vicangel.database_systems_project_2.rest.mapper.PoliceOfficerRequestModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/officers")
@RequiredArgsConstructor
@Slf4j
final class PoliceOfficerController {

  private final PoliceOfficerService policeOfficerService;
  private final PoliceOfficerRequestModelMapper mapper;

  @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<InsertManyResponseDTO> insertMany(@RequestBody final InsertManyRequestDTO request) {

    return new ResponseEntity<>(
      new InsertManyResponseDTO(policeOfficerService.insertMany(request.policeOfficerRequests().stream().map(mapper::mapToModel).collect(Collectors.toSet())))
      , HttpStatus.CREATED
    );
  }

  /**
   * Police officers should be able to cast upvotes to each record, to highlight their importance.
   * Upvotes are accompanied by the name, e-mail and badge number of the police officer.
   */
  @PatchMapping(value = "/upvote", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpvoteResponseDTO> castUpvote(@RequestBody final UpvoteRequestDTO request) {

    return new ResponseEntity<>(
      mapper.mapToResponse(policeOfficerService.castUpvote(request.officerId(), request.officerId()))
      , HttpStatus.OK
    );
  }
}
