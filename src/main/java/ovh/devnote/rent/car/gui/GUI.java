package ovh.devnote.rent.car.gui;

import ovh.devnote.rent.car.authenticate.Authenticator;
import ovh.devnote.rent.car.db.Saver;
import ovh.devnote.rent.car.model.LuxuryCar;
import ovh.devnote.rent.car.model.User;
import ovh.devnote.rent.car.model.Vehicle;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GUI {
    private static final Scanner scanner = new Scanner(System.in);
    public static String showLoginOrRegistrationReadChoose() {
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("0. Exit");
        return scanner.nextLine();
    }


    public static String showMenuAndReadChoose() {
        System.out.println("1. List cars");
        System.out.println("2. Rent car");
        System.out.println("3. Return car");
        if("ADMIN".equals(Authenticator.loggedUserRole)) {
            System.out.println("9. Change role to admin");
        }

        System.out.println("0. Exit");
        return scanner.nextLine();
    }

    public static void printVehicles(Collection<Vehicle> vehicles) {
        for(Vehicle vehicle : vehicles) {
            if(vehicle instanceof LuxuryCar && !"ADMIN".equals(Authenticator.loggedUserRole)) {
                continue;
            }
            System.out.println(vehicle);
        }
    }

    public static String readPlate() {
        System.out.println("Enter plate:");
        return scanner.nextLine();
    }

    public static void showResult(boolean rentResult) {
        if(rentResult) {
            Saver.setChanged(true);
            System.out.println("Success !!");
        } else {
            System.out.println("Error !!");
        }
    }

    public static void showWrongChoose() {
        System.out.println("Wrong choose !!");
    }

    public static User readUserData() {
        System.out.println("Login:");
        String login = scanner.nextLine();
        System.out.println("Password:");
        return new User(login, scanner.nextLine());
    }


    public static void printUsers(HashMap<String, User> users) {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            System.out.println("User: " + entry.getValue());
        }
    }

    public static String readLogin() {
        System.out.println("Login:");
        String login = scanner.nextLine();
        return login;
    }
    public static String readRole() {
        System.out.println("Role:");
        String role = scanner.nextLine();
        return role;
    }

    public static void printUserRoleWarning() {
        System.out.println("You change your role!! Logged Out!!");
    }
}
