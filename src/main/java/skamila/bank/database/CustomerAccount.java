package skamila.bank.database;

import java.math.BigDecimal;

public class CustomerAccount {

    private int userId;
    private String firstName, surname, personalIdentityNumber, city, postCode, address;
    private BigDecimal funds;

    public CustomerAccount(){}

    public CustomerAccount(int userId, String firstName, String surname, String personalIdentityNumber, String address, String postCode, String city){
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.personalIdentityNumber = personalIdentityNumber;
        this.city = city;
        this.postCode = postCode;
        this.address = address;
        this.funds = new BigDecimal(0);
    }

    public CustomerAccount(int userId, String firstName, String surname, String personalIdentityNumber, String address, String postCode, String city, String funds){
        this(userId, firstName, surname, personalIdentityNumber, address, postCode, city);
        this.funds = new BigDecimal(funds);
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getAddress() {
        return address;
    }

    public String getFunds() {
        return funds.toString();
    }

    public void addFunds (String amount){
        funds = funds.add(new BigDecimal(amount));
    }

    public void subFunds (String amount){
        funds = funds.subtract(new BigDecimal(amount));
    }

    public void displayCustomer () {
        System.out.format("%-3d | %-15s | %-20s | %s | %-15s | %-12s | %-25s | %.2f\n", userId, firstName, surname, personalIdentityNumber, city, postCode, address, funds);
    }




}
