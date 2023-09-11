package command;

import data.ExpenseStore;

import java.util.List;
import java.util.Map;

public class EqualExpenseCalculatorCommand implements Command{

    private final String paid_by_user;
    private final List<String>  users_to_pay;
    private final int totalAmount;

    public EqualExpenseCalculatorCommand(String paid_by_user, List<String> users_to_pay, int totalAmount) {
        this.paid_by_user = paid_by_user;
        this.users_to_pay = users_to_pay;
        this.totalAmount = totalAmount;
    }

    @Override
    public Map<String, Integer> execute() {

        int userInvolvedInTransaction = this.users_to_pay.size() + 1;
        int amountToPay = this.totalAmount/userInvolvedInTransaction;

        Map<String,Integer> owedUser = ExpenseStore.getUserExpenses(this.paid_by_user);

        for(String user_to_pay : users_to_pay){
            owedUser.putIfAbsent(user_to_pay, 0);
            owedUser.put(user_to_pay, owedUser.get(user_to_pay) + amountToPay);
            Map<String,Integer> updateBorrowedUser = ExpenseStore.getUserExpenses(user_to_pay);
            if(updateBorrowedUser == null) {
                System.out.println("User "+ user_to_pay + " does not exist");
                continue;
            }
            updateBorrowedUser.putIfAbsent(paid_by_user, 0);
            updateBorrowedUser.put(paid_by_user, updateBorrowedUser.get(paid_by_user) - amountToPay);
        }

        return owedUser;
    }


}
