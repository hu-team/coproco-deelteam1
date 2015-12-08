package nl.hu.coproco.service;

import nl.hu.coproco.domain.Purpose;
import nl.hu.coproco.domain.PurposeStorage;

import java.util.ArrayList;

public class PurposeService {
    public static ArrayList<Purpose> getAllPurposes() {
        return PurposeStorage.getInstance().getPurposes();
    }
}
