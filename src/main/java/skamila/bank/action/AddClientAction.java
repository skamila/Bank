package skamila.bank.action;

import skamila.bank.database.CustomerAccount;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.Confirmation;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;
import java.io.FileNotFoundException;

public class AddClientAction implements Action {

    CustomerAccountDatabase database;

    public AddClientAction(CustomerAccountDatabase database){
        this.database = database;
    }

    @Override
    public void action() {

        Input input = new ConsoleInput ();
        Confirmation confirmation = new Confirmation();
        CustomerAccountInput getter = new CustomerAccountInput();

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
