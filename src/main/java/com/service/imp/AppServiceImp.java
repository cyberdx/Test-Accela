package com.service.imp;

import com.domain.Person;
import com.helper.ConsoleHelper;
import com.helper.imp.ConsoleHelperImp;
import com.helper.MenuHelper;
import com.helper.XmlHelper;
import com.service.AppService;
import com.service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

@Service
@Configurable
public class AppServiceImp implements AppService {

    private static final Logger LOGGER = Logger.getLogger(AppServiceImp.class);
    private ConsoleHelper console = new ConsoleHelperImp(new Scanner(System.in));
    private MenuHelper menuHelper;
    private PersonService personService;
    private XmlHelper xmlHelper;

    @Autowired
    public void set(MenuHelper menuHelper) {
        this.menuHelper = menuHelper;
    }

    @Autowired
    public void set(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void set(XmlHelper xmlHelper) {
        this.xmlHelper = xmlHelper;
    }

    @Override
    public void init() {
        menuNavigator();
    }

    private void clear() {
        System.out.flush();
    }


    private void menuNavigator() {
        LOGGER.info(menuHelper.mainMenu());

        switch (console.scannerValue()) {
            case "0":
                LOGGER.info("\n\nGoodbye.");
                return;
            case "1":
                clear();
                menuNavigator();
                break;
            case "2":
                clear();
                addPersonMenu();
                break;
            case "3":
                clear();
                updatePerson();
                break;
            case "4":
                clear();
                deletePerson();
                break;
            case "5":
                clear();
                totalCount();
                break;
            case "6":
                clear();
                listOfPersons();
                break;
            case "7":
                clear();
                updateFromXml();
                break;
            default:
                clear();
                LOGGER.error("\n\nWrong Number");
                menuNavigator();
        }
    }

    private void addPersonMenu(){
        LOGGER.info(menuHelper.addPersonMenu());

        String value = console.scannerValue();

        if (value.equalsIgnoreCase("return")){
            clear();
            menuNavigator();
        }

        Person person = personMapper(value);

        if (person != null){
            personService.addPerson(person);
            menuNavigator();
        }else{
            clear();
            if(!StringUtils.isBlank(value)) {
                LOGGER.error("\n\nWRONG CREDENTIALS");
            }
            addPersonMenu();
        }
    }

    private void updatePerson(){
        LOGGER.info(menuHelper.updatePerson());

        Person person = personMapper(console.scannerValue());

        if (personService.updatePerson(person)){
            LOGGER.info("\n\nPerson was update");
        }

        menuNavigator();
    }

    private void deletePerson(){
        LOGGER.info(menuHelper.deletePersonMenu());

        try {
            long id = Long.valueOf(console.scannerValue());

            if (personService.deletePerson(id)){
                LOGGER.info("\n\nPerson deletePerson successfully.");
                menuNavigator();
            }else {
                LOGGER.error("\n\nID not exits");
            }

        }catch (Exception e){
            LOGGER.error("\n\nWrong ID");
        }
    }

    private void totalCount(){
        long total = personService.countPerson();

        LOGGER.info("\n\nTotal persons: " + total);

        menuNavigator();
    }

    private void listOfPersons(){
        LOGGER.info(menuHelper.allPersonMenu());
        for (Person person : personService.findAll()){
            LOGGER.info(person.toString());
        }
        menuNavigator();
    }

    private Person personMapper(String line){
        String[] values = line.split(",");

        if (values.length > 2) {
            Person person = new Person();

            person.setPersonId(Integer.valueOf(values[0]));
            person.setFirstName(values[1]);
            person.setSurname(values[2]);

            return person;
        }
        LOGGER.error("\n\nWrong values");
        return null;
    }

    private void updateFromXml(){
        LOGGER.info(menuHelper.xmlPersonMenu());

        String value = console.scannerValue();

        File file = new File(value.trim());

        if (!file.exists()){
            LOGGER.info("\n\nFile not found.");
            updateFromXml();
        }else {
            try {
                List<Person> personList = xmlHelper.parsXml(new FileInputStream(file));
                personService.addPerson(personList);
                LOGGER.info("\n\nPersons add from file.");
            } catch (FileNotFoundException e) {
                LOGGER.error(e.getMessage());
            }
        }

        menuNavigator();
    }
}
