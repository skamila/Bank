package skamila.bank.database;

import skamila.bank.database.CustomerAccount;

import java.util.ArrayList;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintWriter;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class CustomerAccountDatabase {
    private String filePath;
    private ArrayList<CustomerAccount> customerDatabase;

    public CustomerAccountDatabase(String filePath){
        customerDatabase = new ArrayList<>();
        this.filePath = filePath;
    }

    public boolean isEmpty(){
        return customerDatabase.isEmpty();
    }

    public int getNewId(){
        if (isEmpty()) return 1;
        CustomerAccount account = customerDatabase.get(customerDatabase.size() - 1);
        return account.getUserId() + 1;
    }

    public void addCustomer(CustomerAccount account){
        customerDatabase.add(account);
    }

    public void deleteCustomer(int id){
        boolean ifDelete = false;
        for (int i = 0; i < customerDatabase.size() && !ifDelete; i++) {
            if ((getCustomer(i)).getUserId() == id){
                customerDatabase.remove(i);
                ifDelete = true;
            }
        }
    }

    public CustomerAccount getCustomer(int index){
        for (CustomerAccount account : customerDatabase) {
            return customerDatabase.get(index);
        }
        throw new IllegalArgumentException();
    }

    public void update() throws FileNotFoundException {

        File file = new File(filePath);
        PrintWriter write = new PrintWriter(filePath);

        for (CustomerAccount account : customerDatabase) {
            write.print(doCustomerEntry(account));
        }

        write.close();

    }

    public void load() throws IOException {

        Path path = Paths.get(filePath);

        if (Files.notExists(path)){
            Files.newOutputStream(path, CREATE, APPEND);
        }

        BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));

        String currentLine = null;
        while((currentLine = reader.readLine()) != null){
            customerDatabase.add(getCustomerEntry(currentLine));
        }

        reader.close();

    }

    private String doCustomerEntry (CustomerAccount account){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(account.getUserId());
        stringBuilder.append("|");
        stringBuilder.append(account.getFirstName());
        stringBuilder.append("|");
        stringBuilder.append(account.getSurname());
        stringBuilder.append("|");
        stringBuilder.append(account.getPersonalIdentityNumber());
        stringBuilder.append("|");
        stringBuilder.append(account.getAddress());
        stringBuilder.append("|");
        stringBuilder.append(account.getPostCode());
        stringBuilder.append("|");
        stringBuilder.append(account.getCity());
        stringBuilder.append("|");
        stringBuilder.append(account.getFunds());
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private CustomerAccount getCustomerEntry (String input){

        String [] customerAttributes = input.split("\\|");

        return new CustomerAccount (
                Integer.parseInt(customerAttributes[0]),
                customerAttributes[1],
                customerAttributes[2],
                customerAttributes[3],
                customerAttributes[4],
                customerAttributes[5],
                customerAttributes[6],
                customerAttributes[7]
        );
    }

    public CustomerAccount getById(int id){

        for (CustomerAccount account : customerDatabase) {
            if (account.getUserId() == id) return account;
        }
        throw new IllegalArgumentException();
    }

    public ArrayList<CustomerAccount> getByFirstName(String firstName){
        ArrayList<CustomerAccount> customerList = new ArrayList<>();
        for (CustomerAccount account : customerDatabase) {
            if (account.getFirstName().equals(firstName)) customerList.add(account);
        }
        if (customerList.isEmpty()) throw new IllegalArgumentException();
        return customerList;
    }

    public ArrayList<CustomerAccount> getBySurname(String surname){
        ArrayList<CustomerAccount> customerList = new ArrayList<>();
        for (CustomerAccount account : customerDatabase) {
            if (account.getSurname().equals(surname)) customerList.add(account);
        }
        if (customerList.isEmpty()) throw new IllegalArgumentException();
        return customerList;
    }

    public ArrayList<CustomerAccount> getByPersonalIdentityNumber(String personalIdentityNumber){
        ArrayList<CustomerAccount> customerList = new ArrayList<>();
        for (CustomerAccount account : customerDatabase) {
            if (account.getPersonalIdentityNumber().equals(personalIdentityNumber)) customerList.add(account);
        }
        if (customerList.isEmpty()) throw new IllegalArgumentException();
        return customerList;
    }

    public ArrayList<CustomerAccount> getByCity(String city){
        ArrayList<CustomerAccount> customerList = new ArrayList<>();
        for (CustomerAccount account : customerDatabase) {
            if (account.getCity().equals(city)) {
                customerList.add(account);
            }
        }
        if (customerList.isEmpty()) throw new IllegalArgumentException();
        return customerList;
    }

    public ArrayList<CustomerAccount> getByPostCode(String postCode){
        ArrayList<CustomerAccount> customerList = new ArrayList<>();
        for (CustomerAccount account : customerDatabase) {
            if (account.getPostCode().equals(postCode)) customerList.add(account);
        }
        if (customerList.isEmpty()) throw new IllegalArgumentException();
        return customerList;
    }

    public ArrayList<CustomerAccount> getByAddress(String address){
        ArrayList<CustomerAccount> customerList = new ArrayList<>();
        for (CustomerAccount account : customerDatabase) {
            if (account.getAddress().equals(address)) customerList.add(account);
        }
        if (customerList.isEmpty()) throw new IllegalArgumentException();
        return customerList;
    }

    public ArrayList<CustomerAccount> getAll(){
        if (customerDatabase.isEmpty()) throw new IllegalArgumentException();
        return customerDatabase;
    }



}
