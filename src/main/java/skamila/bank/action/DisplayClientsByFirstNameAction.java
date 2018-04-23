package skamila.bank.action;

import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.utilities.GetData;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsByFirstNameAction implements Action {

    CustomerDatabase database;

    public DisplayClientsByFirstNameAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {
        GetData getter = new GetData();

        String city =  getter.getFirstName();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByFirstName(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }
    }
}
