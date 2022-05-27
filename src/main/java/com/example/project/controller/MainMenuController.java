package com.example.project.controller;

import com.example.project.model.Database;
import com.example.project.model.User;

public class MainMenuController {
    private User user;
    private Database database;

    public MainMenuController(Database database) {
        this.database = database;
    }
}
