package com.example.accessing_data_mysql.request;

import java.util.Set;

public class PersonRequest {

    private String name;
    private Set<Integer> friendIds;

    // Default constructor
    public PersonRequest() {}

    // Constructor with parameters
    public PersonRequest(String name, Set<Integer> friendIds) {
        this.name = name;
        this.friendIds = friendIds;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Integer> getFriendIds() {
        return friendIds;
    }

    public void setFriendIds(Set<Integer> friendIds) {
        this.friendIds = friendIds;
    }
}
