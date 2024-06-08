package com.devwizards.journalApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.devwizards.journalApp.entity.UserEntry;

@RepositoryRestController
public interface UserRepository extends JpaRepository<UserEntry, Long>{

}
