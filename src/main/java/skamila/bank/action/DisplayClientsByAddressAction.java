package skamila.bank.action;

import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsByAddressAction implements Action {

    CustomerAccountDatabase database;

    public DisplayClientsByAddressAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {
        CustomerAccountInput getter = new CustomerAccountInput();

        String city =  getter.getAddress();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByAddress(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }
    }
}
