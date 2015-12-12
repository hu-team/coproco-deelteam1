package nl.hu.coproco.domain;

import java.util.ArrayList;
import java.util.Observable;

public class PatternStorage extends Observable {
    private ArrayList<Pattern> patternStorage;

    private static PatternStorage instance;

    public static PatternStorage getInstance() {
        if (instance == null) {
            instance = new PatternStorage();
        }
        return instance;
    }

    public PatternStorage() {
        this.patternStorage = new ArrayList<>();
        this.initDefaultPatterns();
    }

    private void initDefaultPatterns() {
        // TODO: Paar voorbeelden toevoegen.
    }

    public void addPattern(Pattern pattern) {
        this.patternStorage.add(pattern);

        this.notifyObservers();
    }

    public void removePattern(Pattern pattern) {
        this.patternStorage.remove(pattern);

        this.notifyObservers();
    }

    public Pattern getPattern(int index) throws IndexOutOfBoundsException {
        return this.patternStorage.get(index);
    }

    public ArrayList<Pattern> getPatterns() {
        return this.patternStorage;
    }

    public ArrayList<Pattern> getFilteredPatterns(Purpose purpose, Scope scope) {
        String purposeName = null;
        String scopeName = null;

        if (purpose != null) {
            purposeName = purpose.getName();
        }

        if (scope != null) {
            scopeName = scope.getName();
        }

        return this.getFilteredPatterns(purposeName, scopeName);
    }

    public ArrayList<Pattern> getFilteredPatterns(String purposeName, String scopeName) {
        ArrayList<Pattern> returningPatterns = new ArrayList<>();

        for(Pattern pattern: this.patternStorage) {
            if ((purposeName == null || pattern.getPurpose().getName().equals(purposeName))
                    && (scopeName == null || pattern.getScope().getName().equals(scopeName))) {
                returningPatterns.add(pattern);
            }
        }

        return returningPatterns;
    }

    public void setPatterns(ArrayList<Pattern> patterns) {
        this.patternStorage = patterns;
        this.notifyObservers();
    }
}
