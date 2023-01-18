import java.util.Scanner;
import java.util.HashMap;
import java.util.Objects;

public class ATM {
    public static void main(String[] args) {
        UserDetails myAccount = new UserDetails();
        Scanner input = new Scanner(System.in);
        int registerOrNot;
        System.out.println("Welcome to Oasis ATM");
        while (true) {
            System.out.println("Enter 1 for registration");
            System.out.println("Enter 2 for logIn");
            registerOrNot = input.nextInt();
            if (registerOrNot == 1) {
                myAccount.register();
            } else if (registerOrNot == 2) {
                myAccount.logIn();
            } else {
                break;
            }
            boolean exit = true;
            while (exit) {
                System.out.println("1.Transaction History");
                System.out.println("2.Withdraw");
                System.out.println("3.Deposit");
                System.out.println("4.Transfer");
                System.out.println("5.Quit");
                System.out.println("6.Login");
                int choice = input.nextInt();
                switch (choice) {
                    case 1 -> myAccount.transactionHistory();
                    case 2 -> myAccount.withdraw();
                    case 3 -> myAccount.deposit();
                    case 4 -> myAccount.transfer();
                    case 5 -> System.exit(0);
                    case 6 -> exit = false;
                    default -> System.out.println("Invalid choice :");
                }
            }
        }
    }
}