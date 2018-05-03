package skamila.bank.action;

import skamila.bank.database.CustomerAccount;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.DisplayClients;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;

import java.util.ArrayList;

public class DisplayClientsByIdAction implements Action {

    CustomerAccountDatabase database;

    public DisplayClientsByIdAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        CustomerAccountInput input = new CustomerAccountInput();
        int id =  input.getId();
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
