package skamila.bank.action;

import skamila.bank.CustomerAccount;
import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.utilities.GetData;
import skamila.cmdMenuFramework.Action;

import java.util.ArrayList;

public class DisplayClientsByIdAction implements Action {

    CustomerDatabase database;

    public DisplayClientsByIdAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        GetData getter = new GetData();

        int id =  getter.getId();
        DisplayClients printer = new DisplayClients();

        ArrayList<CustomerAccount> customer = new ArrayList<>();

        try {
            customer.add(database.getById(id));
        } catch (IllegalArgumentException e){
            System.out.println("Nie znaleziono żadnych klientów spełniających podane kryterium.");
        }

        if (!customer.isEmpty()) printer.display(customer);

    }
}
