package com.example.accessing_data_mysql.mapper;

import com.example.accessing_data_mysql.dto.PersonDTO;
import com.example.accessing_data_mysql.entity.Person;
import org.springframework.data.util.StreamUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonMapper {

    public static PersonDTO toDTO(Person person) {
        // Convert friends to a set of friend IDs
        Set<Integer> friendIds = person.getFriends().stream()
                .map(Person::getId)
                .collect(Collectors.toSet());

        // Create and return the DTO
        return new PersonDTO(
                person.getId(),
                person.getName(),
                friendIds
        );
    }

    // Convert a Set of Person entities to a Set of PersonDTO
    public static List<PersonDTO> toDTOList(Iterable<Person> persons) {
        return StreamUtils.createStreamFromIterator(persons.iterator())
                .map(PersonMapper::toDTO)
                .collect(Collectors.toList());
    }
}
