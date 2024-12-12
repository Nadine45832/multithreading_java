import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();

        Account accountTest = new Account(12, 1000);

        int amountOfTransactions = rand.nextInt(3, 20);
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < amountOfTransactions; i++){
            double amount = rand.nextDouble(10, 200);
            boolean withdraw = rand.nextDouble() > 0.5;

            transactions.add(new Transaction(accountTest, amount, withdraw));
        }

        System.out.printf("Apply %d transactions to account %d with balance %f%n", transactions.size(), accountTest.getId(), accountTest.getBalance());

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (Transaction t : transactions) {
            executor.execute(t);
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.MINUTES);

        System.out.printf("Left balance %f%n", accountTest.getBalance());

    }
}