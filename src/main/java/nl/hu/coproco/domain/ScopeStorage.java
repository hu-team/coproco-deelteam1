package nl.hu.coproco.domain;

import java.util.ArrayList;

public class ScopeStorage {
    private static ScopeStorage instance;

    private ArrayList<Scope> scopeStorage;

    public static ScopeStorage getInstance() {
        if (instance == null) {
            instance = new ScopeStorage();
        }
        return instance;
    }


    public ScopeStorage() {
        this.scopeStorage = new ArrayList<Scope>();

        this.initDefaultScopes();
    }

    private void initDefaultScopes() {
        this.scopeStorage.add(new Scope("Class"));
        this.scopeStorage.add(new Scope("Object"));
    }

    public ArrayList<Scope> getScopes() {
        return this.scopeStorage;
    }
}
