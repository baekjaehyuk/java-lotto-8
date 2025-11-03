package lotto.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningLottoCalculatorTest {

    @DisplayName("로또 티켓과 당첨 번호를 비교하여 당첨 통계를 계산한다.")
    @Test
    void calculate() {
        // given
        List<List<Integer>> lottoNumbers = List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 7),
                List.of(1, 2, 3, 4, 5, 8),
                List.of(1, 2, 3, 4, 8, 9),
                List.of(1, 2, 3, 8, 9, 10),
                List.of(3, 9, 10, 11, 12, 13)
        );

        List<Lotto> lotto = lottoNumbers.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());

        LottoTicket lottoTicket = new LottoTicket(
                BigDecimal.valueOf(6000),
                lotto
        );

        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        WinningLottoCalculator winningLottoCalculator = new WinningLottoCalculator();

        // when
        LottoResult lottoResult = winningLottoCalculator.calculate(lottoTicket, winningLotto);

        // then
        Map<LottoRank, Integer> result = lottoResult.getResult();
        assertThat(result.getOrDefault(LottoRank.FIRST, 0)).isEqualTo(1);
        assertThat(result.getOrDefault(LottoRank.SECOND, 0)).isEqualTo(1);
        assertThat(result.getOrDefault(LottoRank.THIRD, 0)).isEqualTo(1);
        assertThat(result.getOrDefault(LottoRank.FOURTH, 0)).isEqualTo(1);
        assertThat(result.getOrDefault(LottoRank.FIFTH, 0)).isEqualTo(1);
        assertThat(result.getOrDefault(LottoRank.NONE, 0)).isEqualTo(1);
    }
}
