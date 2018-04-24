package skamila.bank.action;

import skamila.bank.database.CustomerAccount;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.utilities.Confirmation;
import skamila.bank.validator.AmountValidator;
import skamila.bank.validator.CustomerIdValidator;
import skamila.cmdMenuFramework.validator.Validator;
import skamila.cmdMenuFramework.Action;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class WithdrawAction implements Action {

    CustomerAccountDatabase database;

    public WithdrawAction(CustomerAccountDatabase database) {
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
        Validator customerIdValidator = new CustomerIdValidator();
        Confirmation confirmation = new Confirmation();
        CustomerAccount account = new CustomerAccount();
        String customerId;

        System.out.print("Wpisz numer klienta:\t");
        boolean wrongCustomerNumberError;

        do {
            customerId = input.getInput();

            while (!customerIdValidator.validate(customerId)) {
                System.out.println("Numer klienta to liczba calkowita wieksza niz 1.");
                customerId = input.getInput();
            }

            wrongCustomerNumberError = false;

            try {
                account = database.getById(Integer.parseInt(customerId));
            } catch (IllegalArgumentException e) {
                System.out.println("Nie znaleziono klienta. Upewnij się, że podałeś poprawny numer klienta.");
                wrongCustomerNumberError = true;
            }

        } while (wrongCustomerNumberError);


        System.out.print("Wpisz kwotę:\t");
        boolean wrongAmountError;
        String amount;

        do {
            wrongAmountError = false;
            amount = input.getInput();
            while (!amountValidator.validate(amount)) {
                System.out.println("Kwota nie może być mniejsza od 0 i i może mieć max. dwie cyfry po przecinku.");
                amount = input.getInput();
            }

            if (new BigDecimal(amount).compareTo(new BigDecimal(account.getFunds())) == 1) {
                System.out.println("Brak środków na końcie. Klient ma " + account.getFunds() + " PLN i maksymalnie taką kwotę można wypłacić.");
                wrongAmountError = true;
            }
        } while (wrongAmountError);


        System.out.println("Czy na pewno chcesz dokonać wypłaty w wysokości " + amount + " PLN ? [T/N]");
        if (confirmation.ifConfirm(input.getInput())) {
            account.subFunds(amount);
            try {
                database.update();
            } catch (FileNotFoundException e) {
                System.out.println("Błąd! Nie udoło się dodać wypłaty.");
            }
        }
    }
}
