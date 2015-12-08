package nl.hu.coproco.domain;

import java.util.ArrayList;
import java.util.Observable;

public class PatternStorage extends Observable {
    private ArrayList<Pattern> patternStorage;

    public PatternStorage() {
        this.patternStorage = new ArrayList<Pattern>();
    }

    public void addPattern(Pattern pattern) {
        this.patternStorage.add(pattern);

        this.notifyObservers(this.patternStorage);
    }

    public void removePattern(Pattern pattern) {
        this.patternStorage.remove(pattern);

        this.notifyObservers(this.patternStorage);
    }

    public Pattern getPattern(int index) throws IndexOutOfBoundsException {
        return this.patternStorage.get(index);
    }

    public ArrayList<Pattern> getPatterns() {
        return this.patternStorage;
    }

    public ArrayList<Pattern> getFilteredPatterns(Purpose purpose, Scope scope) {
        // TODO

        return null;
    }
}
