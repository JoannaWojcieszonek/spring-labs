package savings.repository.impl;

import common.math.Percentage;
import savings.model.Merchant;
import savings.repository.MerchantRepository;
import savings.repository.NotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joanna on 03.08.16.
 */
public class MerchantRepositoryImpl implements MerchantRepository {
    private Map<String, Merchant> merchants = new HashMap<>();

    public MerchantRepositoryImpl() {
        merchants.put("4321", new Merchant("4321", "Guns'n'Bombs", Percentage.of("6%")));
    }

    @Override
    public Merchant findByNumber(String number) {
        if(merchants.containsKey(number)) {
            return merchants.get(number);
        }
        else
            throw new NotFoundException();
    }
}
