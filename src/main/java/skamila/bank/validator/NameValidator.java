package skamila.bank.validator;

import skamila.cmdMenuFramework.validator.Validator;

public class NameValidator implements Validator {

    @Override
    public boolean validate(String input) {
        try {
            if (input.length() < 3)throw new IllegalArgumentException();
            for (int i = 0; i < input.length(); i++) {
                if (!(Character.isLetter(input.charAt(i)) || input.charAt(i) == ' ' || input.charAt(i) == '-')) throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
