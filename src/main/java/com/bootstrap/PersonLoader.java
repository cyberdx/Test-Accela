package com.bootstrap;

import com.domain.Person;
import com.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PersonLoader implements ApplicationListener<ContextRefreshedEvent> {

    private PersonRepository personRepository;

    @Autowired
    public void setProductRepository(PersonRepository personDemographicsRepository) {
        this.personRepository = personDemographicsRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Person person = new Person();
        person.setPersonId(1000);
        person.setFirstName("Test");
        person.setSurname("Tested");
        personRepository.save(person);
    }
}
