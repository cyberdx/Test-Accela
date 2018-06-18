package com.helper.imp;

import com.domain.Person;
import com.helper.XmlHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlHelperImp implements XmlHelper {
    private static final Logger LOGGER = Logger.getLogger(ConsoleHelperImp.class);

    @Override
    public List<Person> parsXml(InputStream stream) {
        List<Person> personList = new ArrayList<>();
        Person person = null;
        String text = null;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(stream);

            while (reader.hasNext()) {
                int Event = reader.next();

                switch (Event) {
                    case XMLStreamConstants.START_ELEMENT: {
                        if ("person".equals(reader.getLocalName())) {
                            person = new Person();
                            person.setPersonId(Long.valueOf(reader.getAttributeValue(0)));
                        }

                        if ("persons".equals(reader.getLocalName()))
                            personList = new ArrayList<>();
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS: {
                        text = reader.getText().trim();
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        switch (reader.getLocalName()) {
                            case "person":
                                personList.add(person);
                                break;
                            case "firstname":
                                person.setFirstName(text);
                                break;
                            case "surname":
                                person.setSurname(text);
                                break;
                        }
                    }
                    break;
                }
            }
        }catch (XMLStreamException e) {
            LOGGER.error(e.getMessage());
        }
        return personList;
    }
}
