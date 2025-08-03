package pl.krzysztofskul.cadmdb.currency;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
public class CurrencyRestController {

    private static final String SESSION_CURRENCY_KEY = "selectedCurrency";

    @PostMapping("/set")
    public ResponseEntity<Void> setCurrency(@RequestBody Map<String, String> request, HttpSession session) {
        String code = request.get("currency");
        try {
            CurrencyEnum currency = CurrencyEnum.valueOf(code.toUpperCase());
            session.setAttribute(SESSION_CURRENCY_KEY, currency);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Invalid currency code
        }
    }

    @GetMapping("/get")
    public ResponseEntity<CurrencyEnum> getCurrency(HttpSession session) {
        CurrencyEnum currency = (CurrencyEnum) session.getAttribute(SESSION_CURRENCY_KEY);
        if (currency == null) {
            currency = CurrencyEnum.PLN; // default
            session.setAttribute(SESSION_CURRENCY_KEY, currency);
        }
        return ResponseEntity.ok(currency);
    }
	
}
