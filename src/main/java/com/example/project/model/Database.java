package com.example.project.model;

import java.util.ArrayList;

public class Database {
    private ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getUserByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username))
                return users.get(i);
        }
        return null;
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
