package skamila.bank.validator;

import skamila.cmdMenuFramework.validator.Validator;

public class AmountValidator implements Validator {
    @Override
    public boolean validate(String input) {
        try {

            if (input.length() < 1) throw new IllegalArgumentException();
            if (input.charAt(0) == '.') throw new IllegalArgumentException();

            int i;
            int dotCounter = 0;

            for (i = 0; i < input.length(); i++){
                if(!(input.charAt(i) == '.' || Character.isDigit(input.charAt(i)))) throw new IllegalArgumentException();
                if (input.charAt(i) == '.') dotCounter += 1;
            }
            if (dotCounter > 1)throw new IllegalArgumentException();

            for (i = 0; i < input.length() && Character.isDigit(input.charAt(i)); i++){
            }

            int counter = 0;
            for (i++; i < input.length(); i++){
                counter++;
                if(counter > 2) throw new IllegalArgumentException();
            }



        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}