package nl.hu.coproco.service;

import nl.hu.coproco.domain.Scope;
import nl.hu.coproco.domain.ScopeStorage;

import java.util.ArrayList;

public class ScopeService {
    public static ArrayList<Scope> getAllScopes() {
        return ScopeStorage.getInstance().getScopes();
    }

    public static Scope getScopeByName(String scopeName) {
        for (Scope scope: getAllScopes()) {
            if (scope.getName().equals(scopeName)) {
                return scope;
            }
        }
        return null;
    }
}
