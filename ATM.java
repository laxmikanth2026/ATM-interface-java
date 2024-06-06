/****************************************************************************/
import java.util.Scanner;

public class ATM {
    private static double balance = 1000.00; // Initial balance
    private static int pin = 1234; // Default PIN

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");
        if (authenticate(scanner)) {
            int choice;
            do {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Change PIN");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        depositMoney(scanner);
                        break;
                    case 3:
                        withdrawMoney(scanner);
                        break;
                    case 4:
                        changePin(scanner);
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 5);
        } else {
            System.out.println("Too many incorrect attempts. Exiting...");
        }

        scanner.close();
    }

    private static boolean authenticate(Scanner scanner) {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter your PIN: ");
            int inputPin = scanner.nextInt();
            if (inputPin == pin) {
                return true;
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Try again.");
            }
        }
        return false;
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
            checkBalance();
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Your balance is $" + balance);
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    private static void changePin(Scanner scanner) {
        System.out.print("Enter your current PIN: ");
        int currentPin = scanner.nextInt();
        if (currentPin == pin) {
            System.out.print("Enter your new PIN: ");
            int newPin = scanner.nextInt();
            System.out.print("Confirm your new PIN: ");
            int confirmPin = scanner.nextInt();
            if (newPin == confirmPin) {
                pin = newPin;
                System.out.println("PIN successfully changed.");
            } else {
                System.out.println("PINs do not match. PIN change failed.");
            }
        } else {
            System.out.println("Incorrect current PIN. PIN change failed.");
        }
    }
}
