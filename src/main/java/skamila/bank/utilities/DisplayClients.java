package skamila.bank.utilities;

import skamila.bank.CustomerAccount;
import skamila.bank.CustomerDatabase;
import skamila.cmdMenuFramework.Action;

import java.util.ArrayList;

public class DisplayClients{

    public void display(ArrayList<CustomerAccount> customers) {
        String line = "----------------------------------------------------------------------------------------------------------------------------------------";
        System.out.println(line);
        System.out.format("%-3s | %-15s | %-20s | %-11s | %-15s | %-12s | %-25s | %s\n", "nr", "Imię", "Nazwisko", "PESEL", "Miasto", "Kod pocztowy", "Adres", "Stan konta");
        System.out.println(line);

        for (int i = 0; i < customers.size(); i++){
            (customers.get(i)).displayCustomer();
        }
        System.out.println(line);
    }
}
