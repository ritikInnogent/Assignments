class SavingAccount extends Account {
    SavingAccount(String name,double balance) {
        super(name,balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal of "+ amount +" successful from "+name+ "'s Account \nRemaining balance: " + this.balance+"\n");
        } else {
            System.out.println("Insufficient balance");
        }
    }

    public void interestCalculation(int years) {
        double INTEREST_RATE = 0.04;
        double interest = this.balance * INTEREST_RATE * years;
        System.out.println("Interest for " + years + " year is: " + interest+"\n");
    }

}
