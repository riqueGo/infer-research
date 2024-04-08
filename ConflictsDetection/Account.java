package ConflictsDetection;

public class Account {
    private String accountNumber;
    private double balance;
    private Person owner;
    private AccountType accountType;

    public Account(String accountNumber, double balance, Person owner, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        this.accountType = accountType;
    }

    public Account(String accountNumber, double balance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    //Infer doesn't catch balance useRight
    public void deposit(double amount) {
        // Infer.useRight(this.balance); // <- this works for Infer
        balance = balance + amount;
        System.out.println(amount + " deposited. New balance is: " + balance);
    }

    //Infer catch balance useRight
    public void depositWorks(double amount) {
        Infer.useRight(balance); // <- this works for Infer
        balance = balance + amount;
        System.out.println(amount + " deposited. New balance is: " + balance);
    }
}
