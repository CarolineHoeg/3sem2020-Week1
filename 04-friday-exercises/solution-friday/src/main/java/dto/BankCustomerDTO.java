package dto;

import entities.BankCustomer;

/**
 *
 * @author carol
 */
public class BankCustomerDTO {

    private int customerID;
    private String fullName;
    private String accountNumber;
    private double balance;

    public BankCustomerDTO(BankCustomer customer) {
        this.customerID = customer.getId();
        this.fullName = customer.getFirstName() + " " + customer.getLastName();
        this.accountNumber = customer.getAccountNumber();
        this.balance = customer.getBalance();
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
    

}
