package ovh.devnote.rent.car.db;

import ovh.devnote.rent.car.Constants;
import ovh.devnote.rent.car.model.User;

import java.io.*;
import java.util.HashMap;

public class UserRepository {
    private final HashMap<String, User> users = new HashMap<>();

    public UserRepository() {
        try(BufferedReader reader =
                    new BufferedReader(new FileReader(Constants.USERS_FILE))) {
            String lineFromFile;
            while((lineFromFile = reader.readLine()) != null) {
                String[] userParts = lineFromFile.split(";");
                User user = new User(userParts[0], userParts[1], userParts[2]);
                this.users.put(user.getLogin(), user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie ma, zepsulo sie !!");
        } catch (IOException e) {
            System.out.println("Nie da sie pliku odczytać !!");
        }
    }

    public User findByLogin(String login) {
        return this.users.get(login);
    }

    public void save() {
        try(BufferedWriter writer =
                    new BufferedWriter(new FileWriter(Constants.USERS_FILE))) {
            boolean first = true;
            for(User user : this.users.values()) {
                if(!first) {
                    writer.newLine();
                }
                first = false;
                writer.write(user.convertToCSVString());
            }
        } catch (IOException e) {
            System.out.println("Users file writing error !");
        }
    }
}
