package ovh.devnote.rent.car;

import ovh.devnote.rent.car.model.Car;
import ovh.devnote.rent.car.model.Vehicle;

public class Test {
    public static void main(String[] args) {
        /*String seed = "v_#(jxXlmQ+Eh&[k[^Xtu{26=;GT_cW${;KhjVQ.";
        String haslo = "mateusz123";

        String hash = DigestUtils.md5Hex(haslo+seed);

        System.out.println(hash);*/

        Vehicle vehicle = new Car("Audi", "A5", 2000, 1.00, "asdf");
        System.out.println(vehicle.getClass().getSimpleName());

        System.out.println(Car.class.getSimpleName());

    }
}
