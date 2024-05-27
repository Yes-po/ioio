package org.example;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class PersonFactory {
    public static Person personFromCSV(String CSVLine, char separator){
        String[] csv_args_arr = CSVLine.split(String.valueOf(separator));
        Deque<String> csv_args = new LinkedList<>(Arrays.stream(csv_args_arr).toList());

        Person result = null;
        switch (csv_args.getFirst()){
           case "USER" -> result = new Person(csv_args);
           case "CUSTOMER" -> result = new Customer(csv_args);
           case "ADMIN" -> result = new Admin(csv_args);
            default -> result = new Person(csv_args);
        }

        return result;
    }
}
