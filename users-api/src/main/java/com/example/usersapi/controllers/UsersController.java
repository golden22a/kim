package com.example.usersapi.controllers;

import com.example.usersapi.models.User;
import com.example.usersapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

  //Ask spring to get dependency injection
  @Autowired
  private com.example.usersapi.repositories.UserRepository userRepository;

  @GetMapping("/")
  public Iterable<User> findAllUsers() {
    return userRepository.findAll();
  }

  //Same name as long as add @PathVariable
  @GetMapping("/{userId}")
  public User findUserById(@PathVariable Long userId) {
    return userRepository.findOne(userId);
  }

  //Delete a user by ID
  @DeleteMapping("/{userId}")
  public HttpStatus deleteUserById(@PathVariable Long userId) {
    userRepository.delete(userId);
    return HttpStatus.OK;
  }

  //Posting
  @PostMapping("/")
  public User createNewUser(@RequestBody User newUser) {
    return userRepository.save(newUser);
  }

  //Naive update
  @PatchMapping("/{userId}")
  public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) {

    User userFromDb = userRepository.findOne(userId);

    userFromDb.setUserName(userRequest.getUserName());
    userFromDb.setFirstName(userRequest.getFirstName());
    userFromDb.setLastName(userRequest.getLastName());

    return userRepository.save(userFromDb);
  }

}
