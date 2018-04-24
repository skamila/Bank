package skamila.bank.database;

import skamila.cmdMenuFramework.validator.Validator;
import skamila.bank.validator.*;
import skamila.cmdMenuFramework.input.ConsoleInput;
import skamila.cmdMenuFramework.input.Input;

public class CustomerAccountInput {

    private Input input;
    private Validator validator;

    public CustomerAccountInput(){
        input = new ConsoleInput();
    }

    public int getId(){
        validator = new IdValidator();
        System.out.print("Numer klienta:\t");
        String id = input.getInput();
        while (!validator.validate(id)) {
            System.out.println("Numer klienta musi składać się z liczby całkowitej większej od 0.");
            id = input.getInput();
        }
        return Integer.parseInt(id);
    }

    public String getFirstName(){
        validator = new NameValidator();
        System.out.print("Imię:\t");
        String firstName = input.getInput();
        while (!validator.validate(firstName)) {
            System.out.println("Imie musi mieć długość min. 3 znaki i może składać się jedynie z liter oraz wybranych znaków (' ''-').");
            firstName = input.getInput();
        }
        return firstName;
    }

    public String getSurname(){
        validator = new NameValidator();
        System.out.print("Nazwisko:\t");
        String surname = input.getInput();
        while (!validator.validate(surname)) {
            System.out.println("Nazwisko musi mieć długość min. 3 znaki i może składać się jedynie z liter oraz wybranych znaków (' ''-').");
            surname = input.getInput();
        }
        return surname;
    }

    public String getPersonalIdentityNumber(){
        validator = new PersonalIdentityNumberValidator();
        System.out.print("PESEL:\t");
        String personalIdentityNumber = input.getInput();
        while (!validator.validate(personalIdentityNumber)) {
            System.out.println("PESEL może składać się jedynie z 11 cyfr.");
            personalIdentityNumber = input.getInput();
        }
        return personalIdentityNumber;
    }

    public String getAddress(){
        validator = new AddreessValidator();
        System.out.print("Ulica i numer:\t");
        String address = input.getInput();
        while (!validator.validate(address)) {
            System.out.println("Adres musi mieć długość min. 3 znaki i może składać się jedynie z liter, cyfr oraz wybranych znaków (' ''-''.''/').");
            address = input.getInput();
        }
        return address;
    }

    public String getPostCode(){
        validator = new PostCodeValidator();
        System.out.print("Kod pocztowy:\t");
        String postCode = input.getInput();
        while (!validator.validate(postCode)) {
            System.out.println("Kod pocztowy może składać się jedynie z cyfr i znaku '-' (format 00-000).");
            postCode = input.getInput();
        }
        return postCode;
    }

    public String getCity(){
        validator = new NameValidator();
        System.out.print("Miasto:\t");
        String city = input.getInput();
        while (!validator.validate(city)) {
            System.out.println("Miasto musi mieć długość min. 3 znaki i może składać się jedynie z liter oraz wybranych znaków (' ''-').");
            city = input.getInput();
        }
        return city;
    }


}
