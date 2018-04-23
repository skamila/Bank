package skamila.bank.action;

import skamila.bank.CustomerAccount;
import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.cmdMenuFramework.Action;

import java.util.ArrayList;

public class DisplayAllClientsAction implements Action {

    CustomerDatabase database;

    public DisplayAllClientsAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {
        DisplayClients printer = new DisplayClients();
        ArrayList<CustomerAccount> allClients = database.getAll();

        if (allClients.size() != 0) {
            printer.display(allClients);
        } else {
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

    }
}
