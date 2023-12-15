package ovh.devnote.rent.car.model;

public class Truck extends Vehicle {
    private int capacity;

    public Truck(String brand, String model, int year, double price, String plate, int capacity) {
        super(brand, model, year, price, plate);
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + " Capacity: " + this.capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String convertToCSVString() {
        return super.convertToCSVString() +
                ";" + this.capacity;
    }
}
