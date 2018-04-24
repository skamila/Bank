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

public class TransferAction implements Action {

    CustomerAccountDatabase database;

    public TransferAction(CustomerAccountDatabase database) {
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
        CustomerAccount sourceAccount = new CustomerAccount();
        String sourceCustomerId;
        CustomerAccount targetAccount = new CustomerAccount();
        String targetCustomerId;

        System.out.print("Wpisz numer klienta, który dokonuje przelewu:\t");
        boolean wrongCustomerNumberError;

        do {
            sourceCustomerId = input.getInput();

            while (!customerIdValidator.validate(sourceCustomerId)) {
                System.out.println("Numer klienta to liczba calkowita wieksza niz 1.");
                sourceCustomerId = input.getInput();
            }

            wrongCustomerNumberError = false;

            try {
                sourceAccount = database.getById(Integer.parseInt(sourceCustomerId));
            } catch (IllegalArgumentException e) {
                System.out.println("Nie znaleziono klienta. Upewnij się, że podałeś poprawny numer klienta.");
                wrongCustomerNumberError = true;
            }

        } while (wrongCustomerNumberError);

        System.out.print("Wpisz numer klienta do którego chcesz przelać pieniądze:\t");

        do {
            targetCustomerId = input.getInput();

            while (!customerIdValidator.validate(targetCustomerId)) {
                System.out.println("Numer klienta to liczba calkowita wieksza niz 1.");
                targetCustomerId = input.getInput();
            }

            wrongCustomerNumberError = false;

            try {
                targetAccount = database.getById(Integer.parseInt(targetCustomerId));
            } catch (IllegalArgumentException e) {
                System.out.println("Nie znaleziono klienta. Upewnij się, że podałeś poprawny numer klienta.");
                wrongCustomerNumberError = true;
            }

        } while (wrongCustomerNumberError);

        if (sourceAccount == targetAccount) {
            System.out.println("Nie można wykonać. Nadwaca i odbiorca przelewu muszą być różni.");
            return;
        }

            System.out.print("Wpisz kwotę:\t");
            boolean wrongAmountError;
            String amount;

            do {
                wrongAmountError = false;
                amount = input.getInput();
                while (!amountValidator.validate(amount)) {
                    System.out.println("Kwota nie może być mniejsza od 0 i może mieć max. dwie cyfry po przecinku.");
                    amount = input.getInput();
                }
                if (new BigDecimal(amount).compareTo(new BigDecimal(sourceAccount.getFunds())) == 1) {
                    System.out.println("Brak środków na końcie. Klient ma " + sourceAccount.getFunds() + " PLN i maksymalnie taką kwotę można przelać.");
                    wrongAmountError = true;
                }
            } while (wrongAmountError);


            System.out.println("Czy na pewno chcesz wykonać przelew w wysokości " + amount + " PLN ? [T/N]");
            if (confirmation.ifConfirm(input.getInput())) {
                sourceAccount.subFunds(amount);
                targetAccount.addFunds(amount);
                try {
                    database.update();
                } catch (FileNotFoundException e) {
                    System.out.println("Błąd! Nie udoło się dodać wypłaty.");
                }
            }
    }
}
