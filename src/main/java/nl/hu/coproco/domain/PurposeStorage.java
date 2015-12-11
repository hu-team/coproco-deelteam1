package nl.hu.coproco.domain;

import java.util.ArrayList;

public class PurposeStorage {
    private static PurposeStorage instance;

    private ArrayList<Purpose> purposeStorage;

    public static PurposeStorage getInstance() {
        if (instance == null) {
            instance = new PurposeStorage();
        }
        return instance;
    }


    public PurposeStorage() {
        this.purposeStorage = new ArrayList<Purpose>();

        this.initDefaultPurposes();
    }

    private void initDefaultPurposes() {
        this.purposeStorage.add(new Purpose("Creational"));
        this.purposeStorage.add(new Purpose("Structural"));
        this.purposeStorage.add(new Purpose("Behavioral"));
    }

    public ArrayList<Purpose> getPurposes() {
        return this.purposeStorage;
    }
}
