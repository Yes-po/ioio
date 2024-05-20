package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static javax.xml.crypto.dsig.DigestMethod.SHA256;

public class Auth {
    private List<Person> people = new ArrayList<Person>();
    private static Auth instance;

    private void load_users(){
        Properties appProps = new Properties();
        try{
            Path path = Path.of("src/main/resources/app.properties");
            appProps.load(new FileInputStream(path.toAbsolutePath().toString()));
            String savedUsers = appProps.getProperty("authentication_list_saving_file");

            File savedUsersFile = new File(savedUsers);
            Scanner scanner = new Scanner(savedUsersFile);
            while (scanner.hasNext())
                people.add(PersonFactory.personFromCSV(scanner.nextLine(), ';'));

        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Program nie mógł wczytać listy użytkowników");
        }
    }

    private void save_users(){
        Properties appProps = new Properties();
        Writer writer = null;
        try{
            Path path = Path.of("src/main/resources/app.properties");
            appProps.load(new FileInputStream(path.toAbsolutePath().toString()));
            String savedUsers = appProps.getProperty("authentication_list_saving_file");

            File savedUsersFile = new File(savedUsers);
            writer = new FileWriter(savedUsersFile);

            for (Person person: people)
                writer.write( person.toCSV(';')+ "\n");

        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Program nie mógł zapisać listy użytkowników");
        } finally {
            try{
            if (writer != null)
                writer.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public Auth(){
        load_users();
    }

    private int id_generator(){
        Optional<Integer> id = people.stream().map(person -> person.id + 1).max(Comparator.naturalOrder());
        return id.orElse(0);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String encrypt_password(String plain_text){
        byte[] bytes = plain_text.getBytes(StandardCharsets.UTF_8);
        try{
            bytes = MessageDigest.getInstance("SHA256")
                    .digest(bytes);

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            // throw new RuntimeException(e);
        }

        return bytesToHex(bytes);
    }
    public Person authenticate(String login, String password){
        boolean isIn = false;
        for(var p: people) {

            if(p.login.equals(login) && p.password.equals(encrypt_password(password))){
                return p;
            }
            if(p.login.equals(login)){
                isIn=true;
                System.out.println("Zle haslo");
                return null;
            }
        }
        System.out.println("Nie ma cie w bazie.");
        return null;

    }
    public boolean loginTaken(String login){
        for (Person p : people){
            if (p.login.equals(login))
                return true;
        }
        return false;
    }
    public boolean addPerson(Person p){
        if (loginTaken(p.login))
            return false; // użytkownik już istnieje
        this.people.add(p);
        p.id = id_generator();
        p.password = encrypt_password(p.password);
        save_users();
        return true;
    }

    public static Auth getInstance(){
        if(instance == null){
            instance = new Auth();
        }
        return instance;

    }
}
