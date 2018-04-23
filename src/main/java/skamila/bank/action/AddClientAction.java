package skamila.bank.action;

import skamila.bank.CustomerAccount;
import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.Confirmation;
import skamila.bank.utilities.GetData;
import skamila.bank.validator.*;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;
import java.io.FileNotFoundException;

public class AddClientAction implements Action {

    CustomerDatabase database;

    public AddClientAction(CustomerDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        Input input = new ConsoleInput ();
        Confirmation confirmation = new Confirmation();
        GetData getter = new GetData();

        String firstName = getter.getFirstName();
        String surname = getter.getSurname();
        String personalIdentityNumber = getter.getPersonalIdentityNumber();
        String address = getter.getAddress();
        String postCode = getter.getPostCode();
        String city = getter.getCity();

        System.out.println("Czy na pewno chcesz dodać użytkownika? [T/N]");
        if (confirmation.ifConfirm(input.getInput())){
            database.addCustomer(new CustomerAccount(database.getNewId(), firstName, surname,
                    personalIdentityNumber, address, postCode, city));
            try {
                database.update();
            } catch (FileNotFoundException e) {
                System.out.println("Błąd! Nie udoło się dodać użytkownika.");
            }
        }

    }
}
