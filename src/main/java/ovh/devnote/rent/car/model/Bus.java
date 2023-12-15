package ovh.devnote.rent.car.model;

public class Bus extends Vehicle {
    private int seats;

    public Bus(String brand, String model, int year, double price, String plate, int seats) {
        super(brand, model, year, price, plate);
        this.seats = seats;
    }

    @Override
    public String toString() {
        return super.toString() + " Seats: " + this.seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String convertToCSVString() {
        return super.convertToCSVString()
                + ";" + this.seats;
    }
}
