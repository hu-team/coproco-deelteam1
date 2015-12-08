package nl.hu.coproco.domain;

import java.util.Observable;

public class Purpose {
    private String name;

    public Purpose(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}
