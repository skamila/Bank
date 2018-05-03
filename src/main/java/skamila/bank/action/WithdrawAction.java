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

        Input confirmInput = new ConsoleInput();
        Validator amountValidator = new AmountValidator();
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

        if (new BigDecimal(account.getFunds()).compareTo(new BigDecimal("0.00")) == 0) {
            System.out.println("Brak środków na końcie.");
            return;
        }

        System.out.print("Wpisz kwotę:\t");
        boolean wrongAmountError;
        String amount;

        do {
            wrongAmountError = false;
            amount = confirmInput.getInput();
            while (!amountValidator.validate(amount)) {
                System.out.println("Kwota nie może być mniejsza od 0 i i może mieć max. dwie cyfry po przecinku.");
                amount = confirmInput.getInput();
            }

            if (new BigDecimal(amount).compareTo(new BigDecimal(account.getFunds())) == 1) {
                System.out.println("Brak środków na końcie. Klient ma " + account.getFunds() + " PLN i maksymalnie taką kwotę można wypłacić.");
                wrongAmountError = true;
            }
        } while (wrongAmountError);


        System.out.println("Czy na pewno chcesz dokonać wypłaty w wysokości " + amount + " PLN ? [T/N]");
        if (confirmation.ifConfirm(confirmInput.getInput())) {
            account.subFunds(amount);
            try {
                database.update();
            } catch (FileNotFoundException e) {
                System.out.println("Błąd! Nie udoło się dodać wypłaty.");
            }
        }
    }
}
