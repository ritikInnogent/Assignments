// BankAccount is an class which we can deposit withdrawn get balance and account number
class BankAccount {
    private double balance;           // here we declare private so that it follows the encapsulation concept
    private String accountNumber;     // same with account number

    // Constructor to initialize account number and balance
    public BankAccount(String accNum) {
        accountNumber = accNum;       // Set account number from argument
        balance = 0;                 // Initial balance is 0
    }

    // we deposit money in bank account if condition satisfied
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    // here also we withdrawn amount when condition is fulfilled
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) balance -= amount;
    }

    // through this we get the account balance
    public double getBalance() {
        return balance;
    }

    // through this we get the account number
    public String getAccountNumber() {
        return accountNumber;
    }
}

// this class only prints the account balance so that single responsiblity principle follows 
class AccountPrinter {
    public void printBalance(BankAccount acct) {
        System.out.println("Balance: " + acct.getBalance());
    }
}

// here we simply declare the method withou defination 
interface InterestAccount {
    void addInterest();
}

// this class simply add interest and use other class properties by implementing and extends other class
class SavingsAccount extends BankAccount implements InterestAccount {
    private double rate; // Interest rate as decimal (e.g. 0.05 for 5%)

    // contructors call parents class constructor
    public SavingsAccount(String accNum, double rate) {
        super(accNum);
        this.rate = rate;
    }

    // Calculate and add interest to the balance
    public void addInterest() {
        double interest = getBalance() * rate;
        deposit(interest); // add this interest to the balance by deposit function
    }
}

// Service class that uses AccountPrinter to show the balance of any BankAccount
class BankService {
    private AccountPrinter printer;  // Dependency on AccountPrinter for printing

    // Constructor to inject AccountPrinter dependency
    public BankService(AccountPrinter printer) {
        this.printer = printer;
    }

    // Use the printer to show balance of given account
    public void showBalance(BankAccount acct) {
        printer.printBalance(acct);
    }
}

// Main application class to run the bank program
public class Bank {
    public static void main(String[] args) {
        // Create a basic bank account with account number "12345"
        BankAccount acc1 = new BankAccount("12345");

        // Create savings account with account number "54321" and 5% interest rate
        SavingsAccount savAcc = new SavingsAccount("54321", 0.05);

        // Deposit 1000 and withdraw 400 from the basic account
        acc1.deposit(1000);
        acc1.withdraw(400);

        // Deposit 2000 to savings and add interest to it
        savAcc.deposit(2000);
        savAcc.addInterest();

        // Create AccountPrinter and BankService objects
        AccountPrinter printer = new AccountPrinter();
        BankService service = new BankService(printer);

        // Use BankService to show balances of both accounts
        service.showBalance(acc1);
        service.showBalance(savAcc);
    }
}
