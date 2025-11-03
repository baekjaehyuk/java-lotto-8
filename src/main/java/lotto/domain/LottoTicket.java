package lotto.domain;

import java.math.BigDecimal;
import java.util.List;
import lotto.util.ErrorMessages;
import lotto.util.LottoConstants;

public class LottoTicket {

    private final List<Lotto> tickets;

    public LottoTicket(BigDecimal amount, List<Lotto> tickets) {
        validateAmount(amount);
        this.tickets = tickets;
        validateTickets(tickets);
    }

    public int size() {
        return tickets.size();
    }

    public List<Lotto> getTickets() {
        return tickets;
    }

    private void validateAmount(BigDecimal amount) {
        BigDecimal remainderAmount = amount.remainder(LottoConstants.PRICE_PER_LOTTO);

        if (remainderAmount.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PURCHASE_LOTTO.getMessage());
        }
    }

    private void validateTickets(List<Lotto> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.EMPTY_LOTTO_TICKETS.getMessage());
        }
    }
}
