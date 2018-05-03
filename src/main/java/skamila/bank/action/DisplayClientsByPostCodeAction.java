package skamila.bank.action;

import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsByPostCodeAction implements Action {

    CustomerAccountDatabase database;

    public DisplayClientsByPostCodeAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        CustomerAccountInput input = new CustomerAccountInput();
        String postCode =  input.getPostCode();
        DisplayClients printer = new DisplayClients();

        try {
            printer.display(database.getByPostCode(postCode));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

    }
}
