package se.kth.iv1350.retailStore.startup;
import se.kth.iv1350.retailStore.controller.Controller;
import se.kth.iv1350.retailStore.integration.IntegratorCreator;
import se.kth.iv1350.retailStore.view.View;

/**
 * the class that controls the startup of the program
 */
public class Main {

    /**
     * the startup, runs for now a temporary scenario in view.
     */
    public static void main(String[] args) {

        IntegratorCreator integratorCreator = new IntegratorCreator();

        Controller controller = new Controller(integratorCreator);

        View view = new View(controller);

        view.runScenario();
    }

}
