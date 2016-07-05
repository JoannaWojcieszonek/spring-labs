package savings.service;

import org.joda.money.Money;
import savings.model.*;
import savings.repository.AccountRepository;
import savings.repository.MerchantRepository;
import savings.repository.PaybackRepository;

/**
 * Created by joanna on 04.07.16.
 */
public class PaybackBookKeeperImpl implements PaybackBookKeeper{

    private AccountRepository accountRepository;

    private MerchantRepository merchantRepository;

    private PaybackRepository paybackRepository;

    public PaybackBookKeeperImpl(AccountRepository accountRepository, MerchantRepository merchantRepository, PaybackRepository paybackRepository) {
        this.accountRepository = accountRepository;
        this.merchantRepository = merchantRepository;
        this.paybackRepository = paybackRepository;
    }

    @Override
    public PaybackConfirmation registerPaybackFor(Purchase purchase) {
        Account account = accountRepository.findByCreditCard(purchase.getCreditCardNumber());
        Merchant merchant = merchantRepository.findByNumber(purchase.getMerchantNumber());
        Money amount = merchant.calculatePaybackFor(account, purchase);
        AccountIncome income = account.addPayback(amount);

        return paybackRepository.save(income, purchase);
    }
}
