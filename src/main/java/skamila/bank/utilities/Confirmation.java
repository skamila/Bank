package skamila.bank.utilities;

public class Confirmation {
    public boolean ifConfirm (String input){
        if (input.length() == 0) return true;
        if (input.charAt(0) == 't' || input.charAt(0) == 'T') return true;
        else return false;
    }
}
