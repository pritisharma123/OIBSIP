import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class UserDetails {
    private final Scanner input;
    private final HashMap<String, Integer> details;
    private final HashMap<String, String> nameDetails;
    private final HashMap<String, Integer> balanceDetails;
    private final HashMap<String, String> accountNumberDetails;
    private final HashMap<String, String> transactionHistory;
    private String checkUserId;

    UserDetails() {
        input = new Scanner(System.in);
        details = new HashMap<>();
        nameDetails = new HashMap<>();
        balanceDetails = new HashMap<>();
        accountNumberDetails = new HashMap<>();
        transactionHistory = new HashMap<>();
    }

    public void register() {

        System.out.println("Please Enter your name : ");
        String name = input.nextLine();
        String userId;
        while (true) {
            System.out.println("Please Enter your UserId : ");
            userId = input.nextLine();
            if (!details.containsKey(userId)) {
                break;
            } else {
                System.out.println("Please Enter valid userId");
            }
        }
        String accountNumber;
        while (true) {
            System.out.println("please Enter your Account Number : ");
            accountNumber = input.nextLine();
            if (!details.containsKey(accountNumber) && !Objects.equals(accountNumber, name) && !Objects.equals(accountNumber, userId)) {
                break;
            } else {
                System.out.println("Userid and account number can't be same");
            }
        }

        int pin;
        while (true) {
            System.out.println("Please Enter your pin : ");
            pin = input.nextInt();
            if (pin >= 1000 && pin <=9999) {
                break;
            } else {
                System.out.println("your pin should be of 4 digits");
            }
        }

        details.put(userId, pin);
        nameDetails.put(userId, name);
        accountNumberDetails.put(accountNumber, userId);
        input.nextLine();
    }

    public void logIn() {
        boolean exit = true;
        int attempts = 0;

        while (exit) {

            System.out.println("Please Enter your UserId : ");
            checkUserId = input.nextLine();
            attempts++;
            if (!details.containsKey(checkUserId)) {
                if (attempts > 2) {
                    System.out.println("Too Many Attempts!");
                    System.exit(0);
                }
                System.out.println("Please Enter A valid UserId!");
            } else {
                exit = false;
            }
        }
        exit = true;
        attempts = 0;
        while (exit) {
            System.out.println("Please Enter your pin : ");
            int checkPin = input.nextInt();
            attempts++;
            if (details.get(checkUserId) != checkPin) {
                if (attempts == 3) {
                    System.out.println("Too Many Attempts!");
                }
                System.out.println("Please Enter A valid Pin ");
            } else {
                exit = false;
                System.out.println("Hello " + this.getName(checkUserId));
            }
        }

    }

    String getName(String id) {
        return nameDetails.get(id);
    }

    public void withdraw() {
        System.out.println("Please Enter amount to Withdraw");
        int amount = input.nextInt();

        if (balanceDetails.get(checkUserId) != null && amount <= balanceDetails.get(checkUserId)) {
            balanceDetails.put(checkUserId, balanceDetails.get(checkUserId) - amount);

            String temp = amount + "   withdrawn from your acccount\n";
            if (transactionHistory.get(checkUserId) == null) {
                transactionHistory.put(checkUserId, temp);
            } else {
                temp = transactionHistory.get(checkUserId) + temp;
                transactionHistory.put(checkUserId, temp);
            }
            System.out.println(amount + "  successfully withdrawn from your acccount  ");
        } else {
            System.out.println("Balance is not Sufficient");
        }
    }

    public void deposit() {
        System.out.println("Please Enter amount to Deposit");
        int amount = input.nextInt();
        String his = amount + "   Deposited to your acccount\n";

        if (transactionHistory.get(checkUserId) == null) {
            transactionHistory.put(checkUserId, his);
        } else {
            his = transactionHistory.get(checkUserId) + his;
            transactionHistory.put(checkUserId, his);
        }
        System.out.println(amount + "  successfully Deposited to your acccount  ");
        if (balanceDetails.get(checkUserId) != null) {

            amount += balanceDetails.get(checkUserId);
        }
        balanceDetails.put(checkUserId, amount);

    }

    public void transfer() {
        System.out.println("Please Enter account Number to transfer the amount");
        String accountNumber = input.next();
        if (!accountNumberDetails.containsKey(accountNumber)) {
            System.out.println("Invalid Account Number ");
        } else {
            System.out.println("please enter the amount :");
            int transferAmount = input.nextInt();
            int receivedAmount = transferAmount;

            if (transferAmount <= balanceDetails.get(checkUserId) && balanceDetails.get(checkUserId) != null) {
                balanceDetails.put(checkUserId, balanceDetails.get(checkUserId) - transferAmount);
                String temp = transferAmount + "   Transferred from your account\n";

                if (transactionHistory.get(checkUserId) == null) {
                    transactionHistory.put(checkUserId, temp);
                } else {
                    temp = transactionHistory.get(checkUserId) + temp;
                    transactionHistory.put(checkUserId, temp);
                }
                System.out.println(transferAmount + "  successfully Transferred from your account  ");
            } else {
                System.out.println("Balance is not Sufficient");
            }
            String id = accountNumberDetails.get(accountNumber);
            if (balanceDetails.get(id) != null) {
                transferAmount += balanceDetails.get(checkUserId);
            }

            balanceDetails.put(id, transferAmount);
            String temp = receivedAmount + "   Received in your account\n";

            if (transactionHistory.get(id) == null) {
                transactionHistory.put(id, temp);
            } else {
                temp = transactionHistory.get(id) + temp;
                transactionHistory.put(id, temp);
            }

        }
    }

    public void transactionHistory() {
        System.out.println("Your Transaction History : ");
        String history = transactionHistory.get(checkUserId);
        System.out.println(history);
    }
}