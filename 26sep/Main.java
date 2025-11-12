import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Account> accounts = new ArrayList<>();
    private static LoanService loanService = new LoanService();

    public static void main(String[] args) {
        System.out.println("=== Welcome to Banking System ===");
        
        while (true) {
            showMainMenu();
            int choice = getValidChoice(1, 6);
            
            switch (choice) {
                case 1: createAccount(); break;
                case 2: performTransaction(); break;
                case 3: checkBalance(); break;
                case 4: calculateInterest(); break;
                case 5: applyLoan(); break;
                case 6: 
                    System.out.println("Thank you for using Banking System!");
                    return;
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit/Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Calculate Interest (Savings Only)");
        System.out.println("5. Apply for Loan");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Invalid name!");
            return;
        }

        System.out.print("Enter initial balance: ");
        double balance = getValidAmount();
        if (balance < 0) return;

        System.out.println("Select account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int type = getValidChoice(1, 2);

        Account account = (type == 1) ? new SavingAccount(name, balance) : new CurrentAccount(name, balance);
        accounts.add(account);
        System.out.println("Account created successfully for " + name);
    }

    private static void performTransaction() {
        Account account = selectAccount();
        if (account == null) return;

        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        int choice = getValidChoice(1, 2);
        
        System.out.print("Enter amount: ");
        double amount = getValidAmount();
        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }

        if (choice == 1) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }

    private static void checkBalance() {
        Account account = selectAccount();
        if (account != null) account.getBalance();
    }

    private static void calculateInterest() {
        Account account = selectAccount();
        if (account == null) return;
        
        if (!(account instanceof SavingAccount)) {
            System.out.println("Interest calculation only available for Savings Account!");
            return;
        }

        System.out.print("Enter number of years: ");
        int years = getValidChoice(1, 50);
        ((SavingAccount) account).interestCalculation(years);
    }

    private static void applyLoan() {
        Account account = selectAccount();
        if (account == null) return;

        System.out.println("1. Home Loan");
        System.out.println("2. Auto Loan");
        int choice = getValidChoice(1, 2);
        
        System.out.print("Enter loan amount: ");
        double amount = getValidAmount();
        if (amount <= 0) {
            System.out.println("Loan amount must be positive!");
            return;
        }

        if (choice == 1) {
            loanService.applyHomeLoan(account, amount);
        } else {
            loanService.applyAutoLoan(account, amount);
        }
    }

    private static Account selectAccount() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found! Create an account first.");
            return null;
        }

        System.out.println("\nSelect Account:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).name);
        }
        
        int choice = getValidChoice(1, accounts.size());
        return accounts.get(choice - 1);
    }

    private static int getValidChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.print("Invalid choice! Enter " + min + "-" + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a number: ");
            }
        }
    }

    private static double getValidAmount() {
        while (true) {
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount >= 0) {
                    return amount;
                }
                System.out.print("Amount cannot be negative! Enter valid amount: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a valid number: ");
            }
        }
    }
}
