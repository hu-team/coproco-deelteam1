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
        // TODO: Default inserts
    }

    public ArrayList<Purpose> getPurposes() {
        return this.purposeStorage;
    }
}
