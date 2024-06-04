package com.devwizards.journalApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="journals")
public class JournalEntry {
    @Id
    private long id;
    private String title;
    private String content;
    
}
