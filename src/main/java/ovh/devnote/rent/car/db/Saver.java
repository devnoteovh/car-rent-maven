package ovh.devnote.rent.car.db;

public class Saver {
    private static boolean CHANGED = false;

    public static void saveData(UserRepository ubase, VehicleRepository vbase){
        if (CHANGED) {
            ubase.save();
            vbase.save();
        }
    }

    public static void setChanged(boolean changed) {
        Saver.CHANGED = changed;
    }
}
