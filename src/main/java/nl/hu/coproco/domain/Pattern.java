package nl.hu.coproco.domain;


import java.util.Observable;

public class Pattern extends Observable {
    private String name;

    private String problem;
    private String solution;
    private String consequences;

    private CachableImage diagram;

    private Purpose purpose;
    private Scope scope;

    public Pattern(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifyObservers();
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
        this.notifyObservers();
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
        this.notifyObservers();
    }

    public String getConsequences() {
        return consequences;
    }

    public void setConsequences(String consequences) {
        this.consequences = consequences;
        this.notifyObservers();
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
        this.notifyObservers();
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
        this.notifyObservers();
    }

    public void setDiagram(CachableImage image) {
        this.diagram = image;
        this.notifyObservers();
    }

    public CachableImage getDiagram() {
        return this.diagram;
    }
}
