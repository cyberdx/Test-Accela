package com.helper;

import com.domain.Person;
import com.helper.imp.XmlHelperImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class XmlHelperImpTest {

    @Test
    public void parsXml() {

        XmlHelper xmlHelper = new XmlHelperImp();
        List<Person> personList = xmlHelper.parsXml(getClass().getResourceAsStream("/persons.xml"));

        assertNotNull(personList);
        Person person = personList.get(0);
        assertEquals(person.getFirstName(), "Tester02");
    }
}