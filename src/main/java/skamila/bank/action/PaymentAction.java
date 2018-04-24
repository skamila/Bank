package skamila.bank.action;

import skamila.bank.database.CustomerAccount;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.Confirmation;
import skamila.bank.database.CustomerAccountInput;
import skamila.bank.validator.AmountValidator;
import skamila.cmdMenuFramework.validator.Validator;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;

import java.io.FileNotFoundException;

public class PaymentAction implements Action {

    CustomerAccountDatabase database;

    public PaymentAction(CustomerAccountDatabase database) {
        this.database = database;
    }

    @Override
    public void action() {

        if (database.isEmpty()) {
            System.out.println("Nie można wykonać. Brak użytkowników w bazie danych.");
            return;
        }

        Input input = new ConsoleInput();
        Validator amountValidator = new AmountValidator();
        Confirmation confirmation = new Confirmation();
        CustomerAccount account = new CustomerAccount();
        int customerId;
        CustomerAccountInput getter = new CustomerAccountInput();
        boolean wrongCustomerNumberError;

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


        System.out.print("Wpisz kwotę:\t");
        String amount = input.getInput();
        while (!amountValidator.validate(amount)) {
            System.out.println("Kwota nie może być mniejsza od 0 i może mieć max. dwie cyfry po przecinku.");
            amount = input.getInput();
        }

        System.out.println("Czy na pewno chcesz dokonać wpłaty w wysokości " + amount + " PLN ? [T/N]");
        if (confirmation.ifConfirm(input.getInput())) {
            account.addFunds(amount);
            try {
                database.update();
            } catch (FileNotFoundException e) {
                System.out.println("Błąd! Nie udoło się dodać wpłaty.");
            }
        }
    }
}
