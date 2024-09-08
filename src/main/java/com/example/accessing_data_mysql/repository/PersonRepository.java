package com.example.accessing_data_mysql.repository;

import com.example.accessing_data_mysql.entity.Person;
import org.springframework.data.repository.CrudRepository;
//Repositories connects to our database and facilitates communication between database and user
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
