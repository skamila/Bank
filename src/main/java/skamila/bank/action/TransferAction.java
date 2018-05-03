package skamila.bank.action;

import skamila.bank.database.CustomerAccount;
import skamila.bank.database.CustomerAccountDatabase;
import skamila.bank.database.CustomerAccountInput;
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


        Input confirmInput = new ConsoleInput();
        Validator amountValidator = new AmountValidator();
        Confirmation confirmation = new Confirmation();
        CustomerAccount sourceAccount = new CustomerAccount();
        int sourceCustomerId;
        CustomerAccount targetAccount = new CustomerAccount();
        CustomerAccountInput input = new CustomerAccountInput();
        int targetCustomerId;
        boolean ifContinue = false;

        System.out.print("Zleceniodawca przelewu:\n");
        do {
            ifContinue = false;
            sourceCustomerId = input.getId();
            try {
                sourceAccount = database.getById(sourceCustomerId);
            } catch (IllegalArgumentException e) {
                System.out.println("Wpisałeś niepoprawny numer użytkownika. Czy chcesz spróbować ponownie? (T/N)");
                if (confirmation.ifConfirm(confirmInput.getInput())) ifContinue = true;
                else return;
            }
        } while (ifContinue);

        if (new BigDecimal(sourceAccount.getFunds()).compareTo(new BigDecimal("0.00")) == 0) {
            System.out.println("Brak środków na końcie.");
            return;
        }

        System.out.print("Odbiorca przelewu:\n");

        do {
            ifContinue = false;
            targetCustomerId = input.getId();
            try {
                targetAccount = database.getById(targetCustomerId);
            } catch (IllegalArgumentException e) {
                System.out.println("Wpisałeś niepoprawny numer użytkownika. Czy chcesz spróbować ponownie? (T/N)");
                if (confirmation.ifConfirm(confirmInput.getInput())) ifContinue = true;
                else return;
            }
        } while (ifContinue);

        if (sourceAccount == targetAccount) {
            System.out.println("Nie można wykonać. Nadwaca i odbiorca przelewu muszą być różni.");
            return;
        }

            System.out.print("Wpisz kwotę:\t");
            boolean wrongAmountError;
            String amount;

            do {
                wrongAmountError = false;
                amount = confirmInput.getInput();
                while (!amountValidator.validate(amount)) {
                    System.out.println("Kwota nie może być mniejsza od 0 i może mieć max. dwie cyfry po przecinku.");
                    amount = confirmInput.getInput();
                }
                if (new BigDecimal(amount).compareTo(new BigDecimal(sourceAccount.getFunds())) == 1) {
                    System.out.println("Brak środków na końcie. Klient ma " + sourceAccount.getFunds() + " PLN i maksymalnie taką kwotę można przelać.");
                    wrongAmountError = true;
                }
            } while (wrongAmountError);


            System.out.println("Czy na pewno chcesz wykonać przelew w wysokości " + amount + " PLN ? [T/N]");
            if (confirmation.ifConfirm(confirmInput.getInput())) {
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
