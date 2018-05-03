package skamila.bank.action;

import skamila.bank.database.CustomerAccount;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.cmdMenuFramework.Action;

import java.util.ArrayList;

public class DisplayAllClientsAction implements Action {

    CustomerAccountDatabase database;

    public DisplayAllClientsAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        DisplayClients printer = new DisplayClients();

        try {
            printer.display(database.getAll());
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów.");
        }

    }
}
