package nl.hu.coproco;

import nl.hu.coproco.controller.MainApplication;

public class Main {
    public final static int BUILD = 1;
    public final static String VERSION = "1.0.0";

    public static void main(String[] args) {
        System.out.println("Starting CoProCo... " + Main.VERSION + " (Build: " + Main.BUILD + ")");

        // Starting our main controller
        new MainApplication(args);
    }
}
