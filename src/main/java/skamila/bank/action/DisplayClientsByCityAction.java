package skamila.bank.action;

import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.utilities.GetData;
import skamila.bank.validator.NameValidator;
import skamila.bank.validator.Validator;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;

public class DisplayClientsByCityAction implements Action {

    CustomerDatabase database;

    public DisplayClientsByCityAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        GetData getter = new GetData();

        String city =  getter.getCity();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getByCity(city));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

    }
}
