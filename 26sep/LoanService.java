public class LoanService implements Loan {

    boolean hasLoan;
    public void applyHomeLoan(Account account, Double amount) {
        if(!hasLoan) {
            System.out.println("Home loan of " + amount + " successfully applied by : " + account.name + ". \n");
            hasLoan = true;
        }
        else {
            System.out.println(account.name + "!!Has Already owned a Loan!!  \n");
        }
    }

    public void applyAutoLoan(Account account, Double amount) {
        if(!hasLoan) {
            System.out.println("Auto loan of " + amount + " successfully applied by : " + account.name + ". \n");
            hasLoan = true;
        }
        else {
            System.out.println(account.name + "!!Has Already owned a Loan!!  \n");
        }
    }
}
