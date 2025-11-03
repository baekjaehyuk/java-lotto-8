package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketTest {

    @DisplayName("로또 리스트가 비어있으면 예외가 발생한다.")
    @Test
    void createLottoTicketWithEmptyLottos() {
        // given
        BigDecimal amount = BigDecimal.valueOf(1000);
        List<Lotto> emptyLottos = Collections.emptyList();

        // when & then
        assertThatThrownBy(() -> new LottoTicket(amount, emptyLottos))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 구입 금액과 로또 리스트로 로또 티켓을 생성한다.")
    @Test
    void createLottoTicket() {
        // given
        BigDecimal amount = BigDecimal.valueOf(2000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );

        // when & then
        assertThatCode(() -> new LottoTicket(amount, lottos))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 티켓의 사이즈를 올바르게 반환한다.")
    @Test
    void getLottoTicketSize() {
        // given
        BigDecimal amount = BigDecimal.valueOf(2000);
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );
        LottoTicket lottoTicket = new LottoTicket(amount, lottos);

        // when
        int size = lottoTicket.size();

        // then
        assertThat(size).isEqualTo(2);
    }
}
