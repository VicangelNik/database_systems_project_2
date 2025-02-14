package com.vicangel.database_systems_project_2.infrastructure.persistence.mongo;

import java.util.HashSet;
import java.util.Set;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.document.PoliceOfficerDocument;
import com.vicangel.database_systems_project_2.infrastructure.persistence.mongo.repository.PoliceOfficerDocumentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <a href="https://github.com/andygibson/datafactory/blob/master/README.md">datafactory</a>
 */
@Component
@RequiredArgsConstructor
@Slf4j
final class PoliceOfficersGenerator implements CommandLineRunner {

  private final PoliceOfficerDocumentRepository repository;

  @Override
  public void run(String... args) {
    if (repository.isCollectionEmpty()) return;
    log.info("Start importing generated data...");

    final var df = new DataFactory();
    final Set<PoliceOfficerDocument> policeOfficers = new HashSet<>();

    for (long i = 0; i < 100; i++) {
      String name = df.getFirstName() + " " + df.getLastName();
      policeOfficers.add(
        new PoliceOfficerDocument(i, name, df.getEmailAddress(), Integer.parseInt(df.getNumberText(5)), Set.of())
      );
    }

    repository.insertMany(policeOfficers);

    log.info("Importing data finished.");
  }
}
