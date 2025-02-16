package com.vicangel.database_systems_project_2.business.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vicangel.database_systems_project_2.business.mapper.PoliceOfficerModelEntityMapper;
import com.vicangel.database_systems_project_2.business.model.PoliceOfficer;
import com.vicangel.database_systems_project_2.business.service.PoliceOfficerService;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.PoliceOfficerDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
final class PoliceOfficerServiceImpl implements PoliceOfficerService {

  private final PoliceOfficerDocumentRepository repository;
  private final PoliceOfficerModelEntityMapper mapper;

  @Override
  public long insertMany(Set<PoliceOfficer> policeOfficers) {
    return repository.insertMany(policeOfficers.stream().map(mapper::mapToDocument).collect(Collectors.toSet()));
  }
}
