package data;

import java.util.HashMap;
import java.util.Map;

public class ExpenseStore {

    private static final Map<String, Map<String,Integer>> owedAmountToUser = new HashMap<>();

    static {
        owedAmountToUser.put("user1", new HashMap<>());
        owedAmountToUser.put("user2", new HashMap<>());
        owedAmountToUser.put("user3", new HashMap<>());
    }


    public static Map<String, Integer> getUserExpenses(String userId){
        return owedAmountToUser.get(userId);
    }
}
