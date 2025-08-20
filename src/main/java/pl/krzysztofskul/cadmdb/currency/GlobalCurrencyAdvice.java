package pl.krzysztofskul.cadmdb.currency;

import java.math.BigDecimal;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalCurrencyAdvice {

	@Autowired
	private ExchangeRateService exchangeRateService;
	
    @ModelAttribute("currencyEnum")
    public CurrencyEnum addCurrencyToModel(HttpSession session) {
        CurrencyEnum currencyEnum = (CurrencyEnum) session.getAttribute("selectedCurrency");
        if (currencyEnum == null) {
            currencyEnum = CurrencyEnum.PLN; // default fallback
            session.setAttribute("selectedCurrency", currencyEnum);
        }
        return currencyEnum;
    }
    
    @ModelAttribute("exchangeRateEUR")
    public BigDecimal addExchangeRateEUR(HttpSession session) {
    	BigDecimal exchangeRateEUR = exchangeRateService.getRate(CurrencyEnum.EUR);
    	session.setAttribute("exchangeRateEUR", exchangeRateEUR);
    	return exchangeRateEUR;
    }
    
    @ModelAttribute("exchangeRateUSD")
    public BigDecimal addExchangeRateUSD(HttpSession session) {
    	BigDecimal exchangeRateUSD = exchangeRateService.getRate(CurrencyEnum.USD);
    	session.setAttribute("exchangeRateUSD", exchangeRateUSD);
    	return exchangeRateUSD;
    }
    
}
