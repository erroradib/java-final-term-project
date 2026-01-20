package entities;

import java.io.*;
import java.util.*;

public class Account {
    private String username;
    private String password;
    private File file = new File("./data/users.txt");

    public Account() {}

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addAccount() {
        try {
            file.getParentFile().mkdirs(); 
            FileWriter fw = new FileWriter(file, true);
            fw.write(username + "\t" + password + "\n");
            fw.close();
        } catch (IOException e) { 
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    public boolean validateLogin(String user, String pass) {
        try {
            if (!file.exists()) return false;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split("\t");
                if (data.length >= 2 && data[0].equals(user) && data[1].equals(pass)) {
                    sc.close();
                    return true;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) { 
            System.out.println("Database file not found");
        }
        return false;
    }

    public boolean usernameExists(String user) {
        try {
            if (!file.exists()) return false;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split("\t");
                if (data.length >= 1 && data[0].equals(user)) {
                    sc.close();
                    return true;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found");
        }
        return false;
    }
}