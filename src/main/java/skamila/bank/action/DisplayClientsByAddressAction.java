package skamila.bank.action;

import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.utilities.GetData;
import skamila.bank.validator.NameValidator;
import skamila.bank.validator.Validator;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;

public class DisplayClientsByAddressAction implements Action {

    CustomerDatabase database;

    public DisplayClientsByAddressAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {
        GetData getter = new GetData();

        String city =  getter.getAddress();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByAddress(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }
    }
}
