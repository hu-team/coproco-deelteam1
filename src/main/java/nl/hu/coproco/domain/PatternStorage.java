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
        this.patternStorage = new ArrayList<Pattern>();
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
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();

        for(Pattern p : patternStorage){
            if(p.getPurpose().getName() == purpose.getName() && p.getScope().getName() == scope.getName()){
                patterns.add(p);
            }
        }

        return patterns;
    }
}
