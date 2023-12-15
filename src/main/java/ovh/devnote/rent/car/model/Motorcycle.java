package ovh.devnote.rent.car.model;

public class Motorcycle extends Vehicle {

    boolean additionalSeat;

    public Motorcycle(String brand, String model, int year, double price, String plate, boolean additionalSeat) {
        super(brand, model, year, price, plate);
        this.additionalSeat = additionalSeat;
    }

    @Override
    public String toString() {
        return super.toString() + " Additional seat: " + this.additionalSeat;
    }

    public boolean isAdditionalSeat() {
        return additionalSeat;
    }

    public void setAdditionalSeat(boolean additionalSeat) {
        this.additionalSeat = additionalSeat;
    }

    @Override
    public String convertToCSVString() {
        return super.convertToCSVString() +
                ";" + this.additionalSeat;
    }
}
