package com.helper.imp;

import com.helper.MenuHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Component
public class MenuHelperImp implements MenuHelper {

    @Override
    public String mainMenu(){

        StringJoiner menu = new StringJoiner(System.lineSeparator());

        menu.add("\n\nType Option ID for action");
        menu.add("0: for exit.");
        menu.add("1: for help");
        menu.add("2: for Add Person ");
        menu.add("3: for Edit Person ");
        menu.add("4: for Delete Person ");
        menu.add("5: for Persons Count ");
        menu.add("6: for List Persons ");
        menu.add("7: for update from XLM");

        return menu.toString();
    }

    @Override
    public String addPersonMenu(){
        StringJoiner menu = new StringJoiner(System.lineSeparator());

        menu.add("\n\nAdd new Person");
        menu.add("Type - return for exit.");
        menu.add("Add new person and use comma as value separator!");
        menu.add("%USER_ID%, %USER_FIRST_NAME%, %USER_LAST_NAME%");

        return menu.toString();
    }

    @Override
    public String updatePerson() {
        StringJoiner menu = new StringJoiner(System.lineSeparator());

        menu.add(StringUtils.EMPTY);
        menu.add("Person to update");
        menu.add("Add new person and use comma as value separator!");
        menu.add("%USER_ID%, %USER_FIRST_NAME%, %USER_LAST_NAME%");

        return menu.toString();
    }

    @Override
    public String allPersonMenu(){
        StringJoiner menu = new StringJoiner(System.lineSeparator());

        menu.add(StringUtils.EMPTY);
        menu.add("List of persons");

        return menu.toString();
    }

    @Override
    public String deletePersonMenu(){
        StringJoiner menu = new StringJoiner(System.lineSeparator());

        menu.add("\n\nInsert person ID to delete:");

        return menu.toString();
    }

    @Override
    public String xmlPersonMenu() {
        StringJoiner menu = new StringJoiner(System.lineSeparator());

        menu.add("\n\nPlease add absolute path to xml file.");

        return menu.toString();
    }
}
