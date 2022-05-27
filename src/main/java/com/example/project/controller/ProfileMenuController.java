package com.example.project.controller;

import com.example.project.model.Database;
import com.example.project.model.User;

public class ProfileMenuController {
    private Database database;

    public ProfileMenuController(Database database) {
        this.database = database;
    }
    public String usernameChange(String username, User user){
        if (database.getUserByUsername(username) != null)
            return "this user already exists!";
        user.setUsername(username);
        return null;
    }

    public void deleteAccount(User user){
        database.removeUser(user);
    }
}
