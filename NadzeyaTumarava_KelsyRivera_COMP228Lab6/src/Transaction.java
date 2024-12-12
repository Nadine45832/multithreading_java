public class Transaction implements Runnable {
    private final Account account;
    private final double amount;
    private final boolean withdraw;

    public Transaction(Account account, double amount, boolean withdraw) {
        this.account = account;
        this.amount = amount;
        this.withdraw = withdraw;
    }

    @Override
    public void run() {
        try {
            if (withdraw) {
                account.withdraw(amount);
            } else {
                account.deposit(amount);
            }
        }
        catch (Exception exception)
        {
            Thread.currentThread().interrupt();
            throw new RuntimeException(exception);
        }
    }
}
