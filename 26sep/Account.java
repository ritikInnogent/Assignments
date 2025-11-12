
abstract class Account {
    double balance;
    String name;

    Account(String name,double balance) {
        this.name = name;
        this.balance = balance;
    }

    public abstract void withdraw(double amount);

    public void deposit(double amount) {
        System.out.println(name+"'s is Credited by"+ amount);
        this.balance += amount;
    }

    public void getBalance() {
        System.out.println("Available Balance in "+name+" 's Account :" + balance +"\n");
    }
}
