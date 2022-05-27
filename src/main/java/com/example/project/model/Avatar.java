package com.example.project.model;

import com.example.project.App;

import java.net.URL;

public enum Avatar {
    AVATAR1(App.class.getResource("/project/images/1.png")),
    AVATAR2(App.class.getResource("/project/images/2.png")),
    AVATAR3(App.class.getResource("/project/images/3.png")),
    AVATAR4(App.class.getResource("/project/images/4.png"));

    private URL avatarUrl;

    Avatar(URL avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public URL getAvatarUrl() {
        return this.avatarUrl;
    }
}
