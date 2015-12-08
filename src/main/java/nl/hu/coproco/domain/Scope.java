package nl.hu.coproco.domain;

import java.util.Observable;

public class Scope {
    private String name;

    public Scope(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}
