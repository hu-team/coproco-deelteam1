package nl.hu.coproco.service;

import nl.hu.coproco.domain.Pattern;
import nl.hu.coproco.domain.PatternStorage;
import nl.hu.coproco.domain.Purpose;
import nl.hu.coproco.domain.Scope;

import java.util.ArrayList;

/**
 * Storage facade
 */
public class PatternService {

    public static ArrayList<Pattern> getAllPatterns() {
        return PatternStorage.getInstance().getPatterns();
    }

    public static ArrayList<Pattern> getFilteredPatterns(Purpose purpose, Scope scope) {
        return PatternStorage.getInstance().getFilteredPatterns(purpose, scope);
    }

    public static void addPattern(Pattern pattern) {
        PatternStorage.getInstance().addPattern(pattern);
    }
}
