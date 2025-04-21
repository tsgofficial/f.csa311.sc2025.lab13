package AndrewWebServices;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDatabase extends Database {
    private Map<String, Integer> accounts = new HashMap<>();

    public void addAccount(String username, int password) {
        accounts.put(username, password);
    }

    @Override
    public int getPassword(String accountName) {
        return accounts.getOrDefault(accountName, 0);
    }
}
