package com.example.accessing_data_mysql.service;

import com.example.accessing_data_mysql.dto.PersonDTO;
import com.example.accessing_data_mysql.entity.Person;
import com.example.accessing_data_mysql.mapper.PersonMapper;
import com.example.accessing_data_mysql.repository.PersonRepository;
import com.example.accessing_data_mysql.request.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO savePerson(PersonRequest personRequest) {
        Person p = new Person(personRequest.getName());
        return PersonMapper.toDTO(personRepository.save(p));
    }

    public Iterable<PersonDTO> findAll() {
        return PersonMapper.toDTOList(personRepository.findAll());
    }

    public PersonDTO addFriend(Integer personId1, Integer personId2){
        Person person = personRepository.findById(personId1).orElseThrow(() -> new RuntimeException("Person not found: " + personId1));
        Person friend = personRepository.findById(personId2).orElseThrow(()-> new RuntimeException("Person not found: " + personId2));

        person.getFriends().add(friend);
        friend.getFriends().add(person);

        personRepository.save(person);
        personRepository.save(friend);

        return PersonMapper.toDTO(person);
    }

    public List<PersonDTO> findMutualFriends(Integer personId1, Integer personId2){
        Person person1 = personRepository.findById(personId1).orElseThrow(() -> new RuntimeException("Person not found: " + personId1));
        Person person2 = personRepository.findById(personId2).orElseThrow(()-> new RuntimeException("Person not found: " + personId2));

        Set<Person> mutualFriends = new HashSet<>(person1.getFriends());
        mutualFriends.retainAll(person2.getFriends());

        return PersonMapper.toDTOList(mutualFriends);
    }


    public Optional<PersonDTO> findById(Integer personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            return Optional.of(PersonMapper.toDTO(personOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    public void delete(Integer personId) {
        Optional<Person> personToDelete = personRepository.findById(personId);

        if (personToDelete.isPresent()) {
            Set<Person> friends = personToDelete.get().getFriends();
            friends.forEach(friend -> {
                friend.getFriends().remove(personToDelete.get());
                personRepository.save(friend);
            });
        }

        personRepository.deleteById(personId);
    }
}
