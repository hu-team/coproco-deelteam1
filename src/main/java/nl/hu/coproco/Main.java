package nl.hu.coproco;

import nl.hu.coproco.controller.MainApplication;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting CoProCo...");

        // Starting our main controller
        MainApplication app = new MainApplication(args);
    }
}
