package apis;

import command.ExpenseRetrieverCommand;

import java.util.Map;

public class ExpenseRetrieverAPI {

    public void getAmount(String user_paid_by){
        ExpenseRetrieverCommand expenseRetrieverCommand = new ExpenseRetrieverCommand(user_paid_by);
        Map<String, Integer> expenseHistory = expenseRetrieverCommand.execute();
        if(expenseHistory == null){
            System.out.println("User : " + user_paid_by + " not involved in any expense!!");
            return;
        }
        for(Map.Entry<String, Integer> expense : expenseHistory.entrySet()){
            System.out.println(expense.getKey() + " : " + expense.getValue());
        }
        return;
    }
}
