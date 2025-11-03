package lotto.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketCalculatorTest {

    private final LottoTicketCalculator lottoTicketCalculator = new LottoTicketCalculator();

    @DisplayName("구입 금액으로 구매할 수 있는 로또의 개수를 계산한다.")
    @Test
    void calculate() {
        // given
        BigDecimal amount = BigDecimal.valueOf(5000);

        // when
        int ticketCount = lottoTicketCalculator.calculate(amount);

        // then
        assertThat(ticketCount).isEqualTo(5);
    }
}
