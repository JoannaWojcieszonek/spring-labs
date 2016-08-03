package savings.service.impl;

import static org.fest.assertions.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;
import static org.joda.time.DateTime.now;
import static org.mockito.Matchers.any;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import savings.model.*;
import savings.repository.*;
import savings.repository.impl.AccountRepositoryImpl;
import savings.repository.impl.MerchantRepositoryImpl;
import savings.repository.impl.PaybackRepositoryImpl;
import savings.service.PaybackBookKeeperImpl;

public class PaybackBookKeeperImplTest {

    private PaybackBookKeeperImpl bookKeeper;

    @Before
    public void setUp() throws Exception {
        bookKeeper = new PaybackBookKeeperImpl(
                new AccountRepositoryImpl(), new MerchantRepositoryImpl(), new PaybackRepositoryImpl());
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowWhenAccountNotFound() {
        //given
        Purchase purchase = new Purchase(Money.of(EUR, 100L), "2222", "4321", now());
        //when
        PaybackConfirmation confirmation = bookKeeper.registerPaybackFor(purchase);
        //then
        //throw new NotFoundException();
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowWhenMerchantNotFound() {
       ///given
        Purchase purchase = new Purchase(Money.of(EUR, 100L), "1234", "3333", now());
        //when
        PaybackConfirmation confirmation = bookKeeper.registerPaybackFor(purchase);
        //then
        //throw new NotFoundException();
    }

    @Test
    public void shouldRegisterPayback() {
        //given
        Purchase purchase = new Purchase(Money.of(EUR, 100L), "1234", "4321", now());
        //when
        PaybackConfirmation confirmation = bookKeeper.registerPaybackFor(purchase);
        //then
        assertThat(confirmation.getNumber()).isEqualTo("1234");
        assertThat(confirmation.getIncome().getAmount()).isEqualTo(Money.of(EUR, 6L));
        assertThat(confirmation.getIncome().getDistributions()).hasSize(2);
        assertThat(confirmation.getIncome().getDistribution("Glock").getAmount()).isEqualTo(Money.of(EUR, 3L));
        assertThat(confirmation.getIncome().getDistribution("M60").getAmount()).isEqualTo(Money.of(EUR, 3L));
    }

}
