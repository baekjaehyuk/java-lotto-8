package lotto.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lotto.util.LottoConstants;

public class LottoTicketCalculator {

    public int calculate(BigDecimal amount) {
        return amount.divide(
                LottoConstants.PRICE_PER_LOTTO,
                0,
                RoundingMode.DOWN
        ).intValue();
    }
}
