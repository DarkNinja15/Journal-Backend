package com.devwizards.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devwizards.journalApp.entity.JournalEntry;
import com.devwizards.journalApp.service.JournalEntryService;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService service;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getJournals() {
        try {
            List<JournalEntry> journals = service.getJournals();
            return new ResponseEntity<List<JournalEntry>>(journals, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable Long id) {
        try {
            Optional<JournalEntry> journal = service.getJournal(id);
            if (journal.isPresent()) {
                return new ResponseEntity<JournalEntry>(journal.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry body) {
        try {
            service.createJournal(body);
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournal(@RequestBody JournalEntry body, @PathVariable Long id) {
        try {
            JournalEntry old = service.getJournal(id).orElse(null);
            if (old != null) {
                old.setTitle((body.getTitle() == null || body.getTitle() == "") ? old.getTitle() : body.getTitle());
                old.setContent(
                        (body.getContent() == null || body.getContent() == "") ? old.getContent() : body.getContent());
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            service.updateJournal(body);
            return new ResponseEntity<JournalEntry>(old, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable Long id) {
        try {
            service.deleteJournal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
