package main.java.connections;

import main.java.entities.Clinic;

public class SelectedClinic {
    private static Clinic clinic;

    public static Clinic getClinic() {
        return clinic;
    }

    public static void setClinic(Clinic clinic) {
        SelectedClinic.clinic = clinic;
    }
}
