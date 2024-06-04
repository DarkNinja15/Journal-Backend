package com.devwizards.journalApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.devwizards.journalApp.entity.JournalEntry;

@RepositoryRestController
public interface JournalRepository extends JpaRepository<JournalEntry,Long>{

}
