package com.service.imp;


import com.domain.Person;
import com.repository.PersonRepository;
import com.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
@Configurable
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean addPerson(Person person) {
        personRepository.save(person);
        return true;
    }

    @Override
    public boolean updatePerson(Person person) {
        personRepository.save(person);
        return true;
    }

    @Override
    public boolean deletePerson(long id) {
        personRepository.delete(id);
        return true;
    }

    @Override
    public long countPerson() {
        return personRepository.count();
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public boolean addPerson(List<Person> personList) {
        personRepository.save(personList);
        return true;
    }
}
