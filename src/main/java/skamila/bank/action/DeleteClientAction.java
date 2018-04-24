package skamila.bank.action;

import skamila.bank.database.CustomerAccount;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.Confirmation;
import skamila.bank.database.CustomerAccountInput;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;

import java.io.FileNotFoundException;

public class DeleteClientAction implements Action {

    CustomerAccountDatabase database;

    public DeleteClientAction(CustomerAccountDatabase database) {
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
        CustomerAccountInput getter = new CustomerAccountInput();

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
