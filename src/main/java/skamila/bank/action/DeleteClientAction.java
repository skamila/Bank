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

        Input confirmInput = new ConsoleInput();
        Confirmation confirmation = new Confirmation();
        CustomerAccount account = new CustomerAccount();
        int customerId;
        CustomerAccountInput input = new CustomerAccountInput();
        boolean ifContinue = false;

        do {
            ifContinue = false;
            customerId = input.getId();
            try {
                account = database.getById(customerId);
            } catch (IllegalArgumentException e) {
                System.out.println("Wpisałeś niepoprawny numer użytkownika. Czy chcesz spróbować ponownie? (T/N)");
                if (confirmation.ifConfirm(confirmInput.getInput())) ifContinue = true;
                else return;
            }
        } while (ifContinue);

        System.out.println("Czy na pewno chcesz usunąć użytkownika? [T/N]");
        if (confirmation.ifConfirm(confirmInput.getInput())) {
            database.deleteCustomer(customerId);
            try {
                database.update();
            } catch (FileNotFoundException e) {
                System.out.println("Błąd! Nie udało się usunąć użytkownika.");
            }
        }
    }
}
