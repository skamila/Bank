package skamila.bank.validator;

import skamila.cmdMenuFramework.validator.Validator;

public class PostCodeValidator implements Validator {

    @Override
    public boolean validate(String input) {

        try {
            if (input.length() != 6) throw new IllegalArgumentException();
            if (input.charAt(2) != '-') throw new IllegalArgumentException();

            for (int i = 0; i < input.length(); i++) {
                if (i == 2) continue;
                if (!(Character.isDigit(input.charAt(i)))) throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
