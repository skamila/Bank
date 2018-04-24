package skamila.bank.validator;

import skamila.cmdMenuFramework.validator.Validator;

public class CustomerIdValidator implements Validator {
    @Override
    public boolean validate(String input) {
        try {
            int tmp = Integer.parseInt(input);
            if (tmp < 1) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
