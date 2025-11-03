package lotto.domain;

import java.math.BigDecimal;
import java.util.List;
import lotto.util.ErrorMessages;

public class LottoTicket {

    private final List<Lotto> tickets;

    public LottoTicket(BigDecimal amount, List<Lotto> tickets) {
        this.tickets = tickets;
        validateTickets(tickets);
    }

    public int size() {
        return tickets.size();
    }

    public List<Lotto> getTickets() {
        return tickets;
    }

    private void validateTickets(List<Lotto> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.EMPTY_LOTTO_TICKETS.getMessage());
        }
    }
}
