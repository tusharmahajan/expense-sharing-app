package command;

import data.ExpenseStore;

import java.util.List;
import java.util.Map;

public class ExactExpenseCalculatorCommand implements Command{

    private final String paid_by_user;
    private final int amount_paid_by_user;
    private final List<String> users_to_pay;
    private final List<Integer> amounts;

    public ExactExpenseCalculatorCommand(String paid_by_user, int amount_paid_by_user, List<String> users_to_pay, List<Integer> amounts) {
        this.paid_by_user = paid_by_user;
        this.amount_paid_by_user = amount_paid_by_user;
        this.users_to_pay = users_to_pay;
        this.amounts = amounts;
    }


    @Override
    public Map<String, Integer> execute() {

        Map<String,Integer> owedUser = ExpenseStore.getUserExpenses(this.paid_by_user);

        for(int i = 0;i<this.users_to_pay.size();i++){
            String user_to_pay = users_to_pay.get(i);
            owedUser.putIfAbsent(user_to_pay, 0);
            owedUser.put(user_to_pay, owedUser.get(user_to_pay) + this.amounts.get(i));
            Map<String,Integer> updateBorrowedUser = ExpenseStore.getUserExpenses(user_to_pay);
            if(updateBorrowedUser == null) {
                System.out.println("User "+ user_to_pay + " does not exist");
                continue;
            }
            updateBorrowedUser.putIfAbsent(paid_by_user, 0);
            updateBorrowedUser.put(paid_by_user, updateBorrowedUser.get(paid_by_user) - this.amounts.get(i));
        }

        return owedUser;
    }
}
