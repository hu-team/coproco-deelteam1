package nl.hu.coproco.controller;

import nl.hu.coproco.view.MainWindow;

/**
 * MainApplication
 */
public class MainApplication {
    private String[] args;

    /**
     * Start MainApplication
     * @param args Arguments
     */
    public MainApplication(String args[]) {
        this.args = args;

        // Init the purpose and scope stores
        initStorage();

        startGui();
    }

    private void initStorage() {

    }

    /**
     * Start the GUI
     */
    private void startGui() {
        (new MainWindow()).openWindow(this.args);
    }
}
