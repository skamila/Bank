package skamila.bank.action;

import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.utilities.GetData;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsByPersonalIdentityNumberAction implements Action {

    CustomerDatabase database;

    public DisplayClientsByPersonalIdentityNumberAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {
        GetData getter = new GetData();

        String city =  getter.getPersonalIdentityNumber();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByPersonalIdentityNumber(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

    }
}
