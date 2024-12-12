public class Account {
    private double balance;

    private final int id;

    public Account(int accountId, double initialBalance) {
        this.balance = initialBalance;
        this.id = accountId;
    }

    public void withdraw(double amount) throws Exception {
        synchronized (this) {
            if (balance >= amount) {
                balance -= amount;
                System.out.printf("Withdrawn %f from account %d. Current balance %f%n", amount, id, balance);
            } else {
                throw new Exception(String.format("Account %d doesn't have sufficient funds to withdraw. Left balance %f", id, balance));
            }
        }
    }

    public void deposit(double amount) {
        synchronized (this) {
            balance += amount;
            System.out.printf("Deposited %f to account %d. Current balance %f%n", amount, id, balance);
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }
}