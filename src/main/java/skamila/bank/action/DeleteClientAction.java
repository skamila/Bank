package skamila.bank.action;

import skamila.bank.CustomerAccount;
import skamila.bank.CustomerDatabase;
import skamila.bank.utilities.Confirmation;
import skamila.bank.utilities.GetData;
import skamila.bank.validator.CustomerIdValidator;
import skamila.bank.validator.Validator;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;

import java.io.FileNotFoundException;

public class DeleteClientAction implements Action {

    CustomerDatabase database;

    public DeleteClientAction(CustomerDatabase database) {
        this.database = database;
    }

    @Override
    public void action() {

        if (database.isEmpty()) {
            System.out.println("Nie można wykonać. Brak użytkowników w bazie danych.");
            return;
        }

        Input input = new ConsoleInput();
        Confirmation confirmation = new Confirmation();
        CustomerAccount account = new CustomerAccount();
        int customerId;

        boolean wrongCustomerNumberError;
        GetData getter = new GetData();

        do {
            customerId = getter.getId();

            wrongCustomerNumberError = false;

            try {
                account = database.getById(customerId);
            } catch (IllegalArgumentException e) {
                System.out.println("Nie znaleziono klienta. Upewnij się, że podałeś poprawny numer klienta.");
                wrongCustomerNumberError = true;
            }

        } while (wrongCustomerNumberError);

        System.out.println("Czy na pewno chcesz usunąć użytkownika? [T/N]");
        if (confirmation.ifConfirm(input.getInput())) {
            database.deleteCustomer(customerId);
            try {
                database.update();
            } catch (FileNotFoundException e) {
                System.out.println("Błąd! Nie udało się usunąć użytkownika.");
            }
        }
    }
}
