package skamila.bank.action;

import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsBySurnameAction implements Action {

    CustomerAccountDatabase database;

    public DisplayClientsBySurnameAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        CustomerAccountInput getter = new CustomerAccountInput();

        String surname =  getter.getSurname();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getBySurname(surname));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }
    }
}
