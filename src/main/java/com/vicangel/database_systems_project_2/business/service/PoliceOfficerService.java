package com.vicangel.database_systems_project_2.business.service;

import java.util.Set;

import com.vicangel.database_systems_project_2.business.model.PoliceOfficer;

public interface PoliceOfficerService {

  PoliceOfficer castUpvote(long officerId, long reportId);

  long insertMany(Set<PoliceOfficer> policeOfficers);
}
