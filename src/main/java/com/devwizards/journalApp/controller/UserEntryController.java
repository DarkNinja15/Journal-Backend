package com.devwizards.journalApp.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.devwizards.journalApp.common.ErrorResponse;
import com.devwizards.journalApp.entity.UserEntry;
import com.devwizards.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserEntry>> getUsers() {
        try {
            List<UserEntry> users = service.getUsers();
            return new ResponseEntity<List<UserEntry>>(users, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntry body) {
        try {
            service.createUser(body);
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
            ErrorResponse errorResponse = new ErrorResponse("User already exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<UserEntry> updateUser(@RequestBody UserEntry body, @PathVariable Long id) {
        try {
            Optional<UserEntry> user = service.getUser(id);
            if (user.isPresent()) {
                user.get().setPassword(body.getPassword());
                service.updateUser(user.get());
                return new ResponseEntity<>(body, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
