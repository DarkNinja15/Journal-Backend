package com.devwizards.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devwizards.journalApp.entity.JournalEntry;
import com.devwizards.journalApp.repo.JournalRepository;

@Component
public class JournalService {
    @Autowired
    private JournalRepository repo;

    public List<JournalEntry> getJournals(){
        return repo.findAll();
    }
    
    public void createJournal(JournalEntry body){
        repo.save(body);
    }

    public Optional<JournalEntry> getJournal(Long id){
        return repo.findById(id);
    }

    public void updateJournal(JournalEntry body){
        repo.save(body);
    }

    public void deleteJournal(Long id){
        repo.deleteById(id);
    }
}
