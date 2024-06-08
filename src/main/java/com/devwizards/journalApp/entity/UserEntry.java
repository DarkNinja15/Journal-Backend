package com.devwizards.journalApp.entity;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;


@Data
@Entity
@Table(name="users")
public class UserEntry {
    @Id
    private long id;

    @Column(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<JournalEntry> journals = new ArrayList<>();

    UserEntry() {
    }
}
