package org.example;


import java.util.Deque;
import java.util.List;

public class Person {

    protected String personRole; // Pole rozróżniające podklasę obiektu person
    public String login;
    public String password;
    public int id;
    public boolean isAdmin;

    protected void init(String PersonRole, String login, String password, int id, boolean isAdmin){
        // Metoda inicjalizująca klasę
        this.login = login;
        this.password = password;
        this.id = id;
        this.isAdmin = isAdmin;
        this.personRole = PersonRole;
    }

    public Person(String login, String password, int id){
        init("PERSON", login, password, id, false);
    }

    public Person(Deque<String> csv_args){
        // Przyjmuje listę argumentów csv, inicjalizuje obiekt Person i "zjada" zużyte argumenty.
        init(
                csv_args.removeFirst(),
                csv_args.removeFirst(),
                csv_args.removeFirst(),
                Integer.parseInt(csv_args.removeFirst()),
                Boolean.parseBoolean(csv_args.removeFirst())
        );
    }
    public Person(){
        this.personRole = "USER";
    }


    public String toCSV(char separator){
        /*
            Jeśli ktoś musiałby napisać override tej metody, tutaj jest szybki przykład jak to zrobić:

            class Student extends Person {
               public String kierunek;
               public String uczelnia;

              @override
              public String toCSV(char separator){
              String base_class_csv = super.toCSV(separator);
              return String.format("%s%c%s%c%s",
                    base_class_csv, separator,
                    kierunek, separator, uczelnia); // Można też użyć dodawania stringów, zamiast String.format.
              }

              public Student (Deque<String> csv_args){
                super(csv_args);
                this.kierunek = csv_args.removeFirst();
                this.uczelnia = csv_args.removeFirst();
              }

            }
         */
         List<String> fields  = List.of(
                 this.personRole,
                 this.login,
                 this.password,
                 String.valueOf(this.id),
                 String.valueOf(this.isAdmin)
         );
         return fields.stream().reduce(null,
                 (result, element) -> (result == null)?element: String.format("%s%c%s", result, separator, element));
    }


}
