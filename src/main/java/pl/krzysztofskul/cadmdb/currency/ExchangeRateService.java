package pl.krzysztofskul.cadmdb.currency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {

    private final Map<CurrencyEnum, BigDecimal> rates = new HashMap<CurrencyEnum, BigDecimal>();

    @PostConstruct
    public void init() {
        rates.put(CurrencyEnum.PLN, BigDecimal.ONE); // base
        rates.put(CurrencyEnum.EUR, new BigDecimal("0.22")); // temporary static
        rates.put(CurrencyEnum.USD, new BigDecimal("0.25")); // temporary static
    }

    public BigDecimal getRate(Currency currency) {
        return rates.getOrDefault(currency, BigDecimal.ONE);
    }

    public BigDecimal convert(BigDecimal amountPln, Currency currency) {
        return amountPln.multiply(getRate(currency)).setScale(2, RoundingMode.HALF_UP);
    }

    // Optional: Fetch live rates via NBP API or ECB JSON/XML later
}
