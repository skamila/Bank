package skamila.bank.action;

import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsByPersonalIdentityNumberAction implements Action {

    CustomerAccountDatabase database;

    public DisplayClientsByPersonalIdentityNumberAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {
        CustomerAccountInput getter = new CustomerAccountInput();

        String city =  getter.getPersonalIdentityNumber();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByPersonalIdentityNumber(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

    }
}
