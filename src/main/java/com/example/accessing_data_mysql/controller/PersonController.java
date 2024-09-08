package com.example.accessing_data_mysql.controller;

import com.example.accessing_data_mysql.dto.PersonDTO;
import com.example.accessing_data_mysql.request.PersonRequest;
import com.example.accessing_data_mysql.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(path = "/person")
    public ResponseEntity<PersonDTO> addNewPerson(@RequestBody PersonRequest personRequest) {
        PersonDTO person = personService.savePerson(personRequest);
        return ResponseEntity.ok(person);
    }

    @GetMapping(path = "/persons")
    public ResponseEntity<Iterable<PersonDTO>> getAllPeople() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(path = "/person/{personId}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Integer personId) {
        Optional<PersonDTO> personOptional = personService.findById(personId);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(personOptional.get());
        } else {
            return ResponseEntity.ok(new PersonDTO());
        }
    }

    @GetMapping(path = "/mutualfriends/{personId1}/{personId2}")
    public ResponseEntity<List<PersonDTO>> getMutualFriends(@PathVariable Integer personId1, @PathVariable Integer personId2) {
        return ResponseEntity.ok(personService.findMutualFriends(personId1, personId2));
    }

    @PostMapping(path = "/addfriend")
    public ResponseEntity<PersonDTO> addFriend(@RequestParam Integer personId1, @RequestParam Integer personId2) {
        return ResponseEntity.ok(personService.addFriend(personId1, personId2));
    }

    @DeleteMapping(path = "/person/{personId}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer personId) {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }
}
