package skamila.bank.action;

import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.utilities.GetData;
import skamila.cmdMenuFramework.Action;

public class DisplayClientsBySurnameAction implements Action {

    CustomerDatabase database;

    public DisplayClientsBySurnameAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        GetData getter = new GetData();

        String surname =  getter.getSurname();
        DisplayClients printer = new DisplayClients();
        try {
            printer.display(database.getBySurname(surname));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }
    }
}
