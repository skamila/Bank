package skamila.bank.validator;

public class PersonalIdentityNumberValidator implements Validator {

    public boolean validate(String input) {
        try {
            if (input.length() != 11 || !input.matches("^[0-9]+$"))
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
