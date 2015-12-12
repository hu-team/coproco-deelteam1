package nl.hu.coproco;

import nl.hu.coproco.controller.MainApplication;
import nl.hu.coproco.domain.Pattern;
import nl.hu.coproco.domain.PatternStorage;
import nl.hu.coproco.domain.Purpose;
import nl.hu.coproco.domain.Scope;
import nl.hu.coproco.service.export.JsonExport;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting CoProCo...");

        // Starting our main controller
        MainApplication app = new MainApplication(args);



        //Test Data voor JSonExport
        PatternStorage ps = new PatternStorage();
        Pattern p = new Pattern("Test data Naam Pattern");
        p.setConsequences("Test Cons");
        p.setProblem("Test Probleem");
        Purpose pup = new Purpose("Test purpose");
        p.setPurpose(pup);
        Scope sc = new Scope("Test Scope");
        p.setScope(sc);
        p.setSolution("test Solution");
        ps.addPattern(p);

        Pattern p1 = new Pattern("Test data Naam Pattern1");
        p1.setConsequences("Test Cons1");
        p1.setProblem("Test Probleem1");
        Purpose pup1 = new Purpose("Test purpose1");
        p1.setPurpose(pup1);
        Scope sc1 = new Scope("Test Scope1");
        p1.setScope(sc1);
        p1.setSolution("test Solution1");
        ps.addPattern(p1);

        JsonExport jse = new JsonExport(ps);
        jse.saveExport("DikkeBMW");

    }
}
