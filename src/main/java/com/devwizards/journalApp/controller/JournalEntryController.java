package com.devwizards.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devwizards.journalApp.entity.JournalEntry;
import com.devwizards.journalApp.repo.JournalRepository;

import java.util.*;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalRepository repo;

    @GetMapping
    public List<JournalEntry> getJournals(){
        return repo.findAll();
    }
    
    @PostMapping
    public void createJournal(@RequestBody JournalEntry body){
        repo.save(body);
    }
}
