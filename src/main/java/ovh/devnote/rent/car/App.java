package ovh.devnote.rent.car;

import ovh.devnote.rent.car.authenticate.Authenticator;
import ovh.devnote.rent.car.db.Saver;
import ovh.devnote.rent.car.db.UserRepository;
import ovh.devnote.rent.car.db.VehicleRepository;
import ovh.devnote.rent.car.gui.GUI;
import ovh.devnote.rent.car.model.User;

public class App {
    public static void main(String[] args) {
        final VehicleRepository vbase = new VehicleRepository();
        final UserRepository ubase = new UserRepository();
        final Authenticator authenticator = new Authenticator(ubase);
        boolean run = true;
        while(run){
            switch (GUI.showLoginOrRegistrationReadChoose()){
                case "1":
                    run = false;
                    break;
                case "2":
                    User user = GUI.readUserData();
                    boolean registered=false;
                    if (ubase.findByLogin(user.getLogin()) == null){
                        user.setPassword(authenticator.hashing(user.getPassword()));
                        user.setRole("USER");
                        ubase.addUser(user);
                        registered = true;
                    }
                    GUI.showResult(registered);
                    break;
                case "0":
                    run = false;
                    Saver.saveData(ubase,vbase);
                    System.exit(0);
                default:
                    GUI.showWrongChoose();
                    break;
            }
        }
        run = false;
        for(int i = 0; i < 3; i++) {
            User user = GUI.readUserData();
            boolean authResult = authenticator.authenticate(user.getLogin(), user.getPassword());
            if(authResult) {
                System.out.println("Logged !!");
                run = true;
                break;
            }
            System.out.println("Incorrect login data !!");
        }
        while(run) {
            switch(GUI.showMenuAndReadChoose()) {
                case "1":
                    GUI.printVehicles(vbase.getVehicles());
                    break;
                case "2":
                    GUI.showResult(vbase.rentVehicle(GUI.readPlate()));
                    break;
                case "3":
                    GUI.showResult(vbase.returnVehicle(GUI.readPlate()));
                    break;

                case "9":
                    GUI.printUsers(ubase.getUsers());
                    User userToChange = ubase.findByLogin(GUI.readLogin());
                    if(userToChange != null) {
                        userToChange.setRole("ADMIN");
                        GUI.showResult(true);
                    }

                        run = false;
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    GUI.showWrongChoose();
                    break;
            }
        }
        Saver.saveData(ubase,vbase);
    }
}
