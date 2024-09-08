package com.example.accessing_data_mysql.dto;

import java.util.Set;

public class PersonDTO {

    private Integer id;
    private String name;
    private Set<Integer> friendIds; // Store only the IDs of friends to avoid recursion

    // Default constructor
    public PersonDTO() {}

    // Constructor with parameters
    public PersonDTO(Integer id, String name, Set<Integer> friendIds) {
        this.id = id;
        this.name = name;
        this.friendIds = friendIds;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
