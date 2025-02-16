package com.vicangel.database_systems_project_2.business.service;

import java.util.Set;

import com.vicangel.database_systems_project_2.business.model.PoliceOfficer;

public interface PoliceOfficerService {

  long insertMany(Set<PoliceOfficer> policeOfficers);
}
