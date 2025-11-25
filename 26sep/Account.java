//To achieve abstraction and encapsulation
abstract class Account {
    double balance;
    String name;

    Account(String name,double balance) {
        this.name = name;
        this.balance = balance;
    }
    //defination will be diff in both the child class
    public abstract void withdraw(double amount);

    public void deposit(double amount) {
        System.out.println(name+"'s is Credited by"+ amount);
        this.balance += amount;
    }

    public void getBalance() {
        System.out.println("Available Balance in "+name+" 's Account :" + balance +"\n");
    }
}
