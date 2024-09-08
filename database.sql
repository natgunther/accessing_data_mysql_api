CREATE TABLE person (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE friendships (
    person_id INT,
    friend_id INT,
    PRIMARY KEY (person_id, friend_id),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (friend_id) REFERENCES person(id)
);
