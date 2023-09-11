import apis.ExpenseEntryAPI;
import apis.ExpenseRetrieverAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseSimulator {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ExpenseEntryAPI expenseEntryAPI = new ExpenseEntryAPI();
    private static final ExpenseRetrieverAPI expenseRetrieverAPI = new ExpenseRetrieverAPI();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            String command = scanner.next().toLowerCase();

            switch (command) {
                case "transaction":
                    handleTransaction();
                    break;
                case "query":
                    handleQuery();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Expense Simulator Menu:");
        System.out.println("1. Enter 'transaction' to record an expense.");
        System.out.println("2. Enter 'query' to retrieve expenses.");
        System.out.println("3. Enter 'exit' to quit the simulator.");
        System.out.print("Enter your choice: ");
    }

    private static void handleTransaction() {
        System.out.print("Enter transaction type (equal/exact): ");
        String transactionType = scanner.next().toLowerCase();

        if (!transactionType.equals("equal") && !transactionType.equals("exact")) {
            System.out.println("Invalid transaction type. Please enter 'equal' or 'exact'.");
            return;
        }

        System.out.print("Enter User who paid: ");
        String paidByUser = scanner.next();
        System.out.print("Enter amount paid by user: ");
        int amount = scanner.nextInt();

        System.out.println("Enter users involved in the transaction by using space. Enter 'exit' to finish.");
        List<String> usersInvolved = new ArrayList<>();
        while (true) {
            System.out.print("Enter userID: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("exit")) break;
            usersInvolved.add(input);
        }

        if (transactionType.equals("equal")) {
            expenseEntryAPI.addEqualExpense(paidByUser, usersInvolved, amount);
        }
        else if (transactionType.equals("exact")) {
            System.out.println("Enter exact amount to be paid for each user one by one in order." +
                    " Enter a non-integer value to finish.");
            List<Integer> amountSplit = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                amountSplit.add(num);
            }
            expenseEntryAPI.addExactExpense(paidByUser, amount, usersInvolved, amountSplit);
        }
    }

    private static void handleQuery() {
        System.out.print("Enter userID whose expenses are to be calculated: ");
        String userID = scanner.next();
        expenseRetrieverAPI.getAmount(userID);
    }
}
