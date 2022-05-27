package com.example.project.controller;

import com.example.project.model.Database;
import com.example.project.model.User;
import com.example.project.view.MainMenuControllerView;

public class LoginMenuController {
    private Database database;

    public LoginMenuController(Database database) {
        this.database = database;
    }

    public String register(String username, String password) {
        if (username.length() == 0 || password.length() == 0)
            return "fields are empty!";
        if (database.getUserByUsername(username) != null) {
            return "this username already exists!";
        }
        User newUser = new User(username, password);
        database.getUsers().add(newUser);
        return null;
    }

    public String login(String username, String password) {
        if (username.length() == 0 || password.length() == 0)
            return "fields are empty!";
        if (database.getUserByUsername(username) == null)
            return "this username doesn't exist!";
        if (!database.getUserByUsername(username).getPassword().equals(password))
            return "incorrect username or password!";
        MainMenuControllerView.setUser(database.getUserByUsername(username));
        return null;
    }
}
