package ovh.devnote.rent.car.gui;

import ovh.devnote.rent.car.authenticate.Authenticator;
import ovh.devnote.rent.car.model.LuxuryCar;
import ovh.devnote.rent.car.model.User;
import ovh.devnote.rent.car.model.Vehicle;

import java.util.Collection;
import java.util.Scanner;

public class GUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static String showMenuAndReadChoose() {
        System.out.println("1. List cars");
        System.out.println("2. Rent car");
        System.out.println("3. Return car");
        System.out.println("4. Exit");
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
            System.out.println("Success !!");
        } else {
            System.out.println("Error !!");
        }
    }

    public static void showWrongChoose() {
        System.out.println("Wrong choose !!");
    }

    public static User readLoginData() {
        System.out.println("Login:");
        String login = scanner.nextLine();
        System.out.println("Password:");
        return new User(login, scanner.nextLine());
    }
}
