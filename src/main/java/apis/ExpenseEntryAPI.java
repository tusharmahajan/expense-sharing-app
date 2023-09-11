package apis;

import command.EqualExpenseCalculatorCommand;
import command.ExactExpenseCalculatorCommand;

import java.util.List;

public class ExpenseEntryAPI {

    public void addEqualExpense(String user_paid_by, List<String> users_to_pay, int amount){
        new EqualExpenseCalculatorCommand(user_paid_by, users_to_pay, amount).execute();
        System.out.println("Expense successfully noted");
    }

    public void addExactExpense(String user_paid_by, Integer user_amount_paid, List<String> users_to_pay, List<Integer> amounts){
        new ExactExpenseCalculatorCommand(user_paid_by, user_amount_paid, users_to_pay, amounts).execute();
        System.out.println("Expense successfully noted");
    }

}
