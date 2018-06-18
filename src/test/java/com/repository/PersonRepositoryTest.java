package com.repository;

import com.domain.Person;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
@DataJpaTest
@ComponentScan
public class PersonRepositoryTest {

    private PersonRepository personRepository;

    @Autowired
    public void setProductRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    public void testDb(){
        Person person = new Person();
        person.setPersonId(1212);
        person.setFirstName("Test");
        person.setSurname("Tester");

        personRepository.save(person);
        assertNotNull(person.getPersonId());

        Person dbPerson = personRepository.findOne(person.getPersonId());
        assertNotNull(dbPerson);

        assertEquals(person.getPersonId(), dbPerson.getPersonId());
        assertEquals(person.getFirstName(), dbPerson.getFirstName());

        long personCount = personRepository.count();
        assertEquals(personCount, 1);

        Iterable<Person> products = personRepository.findAll();

        int count = 0;

        for(Person p : products){
            count++;
        }

        assertEquals(count, 1);
    }
}