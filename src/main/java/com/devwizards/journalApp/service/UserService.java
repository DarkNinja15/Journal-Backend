package com.devwizards.journalApp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devwizards.journalApp.entity.UserEntry;
import com.devwizards.journalApp.repo.UserRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository repo;


    public void deleteUser(Long id){
        repo.deleteById(id);
    }

    public void updateUser(UserEntry body){
        repo.save(body);
    }

    public Optional<UserEntry> getUser(Long id){
        return repo.findById(id);
    }

    public void createUser(UserEntry body){
        // find by user name
        Optional<UserEntry> user = repo.findAll().stream().filter(u -> u.getUserName().equals(body.getUserName())).findFirst();

        if(user.isPresent()){
            throw new IllegalArgumentException("User already exists");
        }
        repo.save(body);
    }

    public List<UserEntry> getUsers(){
        return repo.findAll();
    }
}
