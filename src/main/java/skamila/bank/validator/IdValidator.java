package skamila.bank.validator;

public class IdValidator implements Validator {

    @Override
    public boolean validate(String input) {
        try{
            if (Integer.parseInt(input) <= 0) throw new IllegalArgumentException();
        } catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }
}
