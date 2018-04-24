package skamila.bank.action;

import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsByCityAction implements Action {

    CustomerAccountDatabase database;

    public DisplayClientsByCityAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        CustomerAccountInput getter = new CustomerAccountInput();

        String city =  getter.getCity();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByCity(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

    }
}
