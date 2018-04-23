package skamila.bank.action;

import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.utilities.GetData;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsByPostCodeAction implements Action {

    CustomerDatabase database;

    public DisplayClientsByPostCodeAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {
        GetData getter = new GetData();

        String city =  getter.getPostCode();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByPostCode(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

    }
}
