package savings.repository.impl;

import common.math.Percentage;
import savings.model.Account;
import savings.repository.AccountRepository;
import savings.repository.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joanna on 03.08.16.
 */
public class AccountRepositoryImpl implements AccountRepository {
    private Map<String, Account> accounts = new HashMap<>();

    public AccountRepositoryImpl() {
        accounts.put("1234", new Account("1234", "Jane & John Smith")
                .withObjective("Glock", Percentage.of("50%"))
                .withObjective("M60", Percentage.of("50%")));
    }

    @Override
    public Account findByCreditCard(String creditCardNumber) {
        if(accounts.containsKey(creditCardNumber)) {
            return accounts.get(creditCardNumber);
        }
        throw new NotFoundException();
    }

    @Override
    public void update(Account account) {
        if(accounts.containsValue(account)) {
            accounts.remove(account.getNumber());
            accounts.put(account.getNumber(), account);
        }
    }
}
