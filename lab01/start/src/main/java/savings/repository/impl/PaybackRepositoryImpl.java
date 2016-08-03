package savings.repository.impl;

import savings.model.AccountIncome;
import savings.model.PaybackConfirmation;
import savings.model.Purchase;
import savings.repository.PaybackRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joanna on 03.08.16.
 */
public class PaybackRepositoryImpl implements PaybackRepository {
    private Map<String, PaybackConfirmation> paybackConfirmationMap = new HashMap<>();

    @Override
    public PaybackConfirmation save(AccountIncome income, Purchase purchase) {
        PaybackConfirmation paybackConfirmation = new PaybackConfirmation(purchase.getCreditCardNumber(), income);
        paybackConfirmationMap.put(paybackConfirmation.getNumber(), paybackConfirmation);
        return paybackConfirmation;
    }
}
