package ovh.devnote.rent.car.db;

import ovh.devnote.rent.car.Constants;
import ovh.devnote.rent.car.authenticate.Authenticator;
import ovh.devnote.rent.car.model.*;


import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class VehicleRepository {
    private final HashMap<String, Vehicle> vehicles = new HashMap<>();

    public VehicleRepository() {
        try(BufferedReader reader =
                    new BufferedReader(new FileReader(Constants.VEHICLES_FILE))) {
            String lineFromFile;
            while((lineFromFile = reader.readLine()) != null) {
                String[] vehicleParts = lineFromFile.split(";");
                Vehicle vehicle = null;
                switch(vehicleParts[0]) {
                    case "Car":
                        vehicle = new Car(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5]
                        );
                        break;
                    case "Bus":
                        vehicle = new Bus(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5],
                                Integer.parseInt(vehicleParts[7])
                        );
                        break;
                    case "LuxuryCar":
                        vehicle = new LuxuryCar(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5]
                        );
                        break;
                    case "Truck":
                        vehicle = new Truck(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5],
                                Integer.parseInt(vehicleParts[7])
                        );
                        break;
                    case "Motorcycle":
                        vehicle = new Motorcycle(
                                vehicleParts[1],
                                vehicleParts[2],
                                Integer.parseInt(vehicleParts[3]),
                                Double.parseDouble(vehicleParts[4]),
                                vehicleParts[5],
                                Boolean.parseBoolean(vehicleParts[7])
                        );
                        break;
                }
                vehicle.setRent(Boolean.parseBoolean(vehicleParts[6]));
                this.vehicles.put(vehicle.getPlate(), vehicle);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Pliku nie ma, zepsulo sie !!");
        } catch (IOException e) {
            System.out.println("Nie da sie pliku odczytać !!");
        }
    }

    public boolean rentVehicle(String plate) {
        Vehicle vehicle = this.vehicles.get(plate);
        if(vehicle instanceof LuxuryCar &&
                !"ADMIN".equals(Authenticator.loggedUserRole)) {
            return false;
        }
        if(vehicle != null && !vehicle.isRent()) {
            vehicle.setRent(true);
            return true;
        }
        return false;
    }
    public boolean returnVehicle(String plate) {
        Vehicle vehicle = this.vehicles.get(plate);
        if(vehicle instanceof LuxuryCar &&
                !"ADMIN".equals(Authenticator.loggedUserRole)) {
            return false;
        }
        if(vehicle != null && vehicle.isRent()) {
            vehicle.setRent(false);
            return true;
        }
        return false;
    }

    public Collection<Vehicle> getVehicles() {
        return this.vehicles.values();
    }

    public void save() {
        try(BufferedWriter writer =
                    new BufferedWriter(new FileWriter(Constants.VEHICLES_FILE))) {
            boolean first = true;
            for(Vehicle vehicle : this.vehicles.values()) {
                if(!first) {
                    writer.newLine();
                }
                first = false;
                writer.write(vehicle.convertToCSVString());
            }
        } catch (IOException e) {
            System.out.println("Vehicles file writing error !");
        }
    }
}
