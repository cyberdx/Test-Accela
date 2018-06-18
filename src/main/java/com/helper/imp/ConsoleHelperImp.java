package com.helper.imp;

import com.helper.ConsoleHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ConsoleHelperImp implements ConsoleHelper {

    private static final Logger LOGGER = Logger.getLogger(ConsoleHelperImp.class);
    private Scanner scanner;

    public ConsoleHelperImp(Scanner scanner){
        this.scanner = scanner;
    }

    public String scannerValue(){
        try {
            return scanner.nextLine().trim();
        }catch (Exception e){
            LOGGER.info("\n\nWrong menu Value");
        }
        return StringUtils.EMPTY;
    }
}
