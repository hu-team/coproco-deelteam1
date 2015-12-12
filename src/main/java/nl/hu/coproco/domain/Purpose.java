package nl.hu.coproco.domain;

import java.io.Serializable;

public class Purpose implements Serializable {
    private String name;

    public Purpose(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
