package com.service;

import com.domain.Person;

import java.util.List;

public interface PersonService {
    boolean addPerson(Person person);
    boolean updatePerson(Person person);
    boolean deletePerson(long id);
    long countPerson();
    Iterable<Person> findAll();
    boolean addPerson(List<Person> personList);
}
