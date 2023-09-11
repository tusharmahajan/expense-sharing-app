package command;

import data.ExpenseStore;

import java.util.Map;

public class ExpenseRetrieverCommand implements Command{

    private final String user_id;

    public ExpenseRetrieverCommand(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public Map<String, Integer> execute() {
        return ExpenseStore.getUserExpenses(user_id);
    }
}
