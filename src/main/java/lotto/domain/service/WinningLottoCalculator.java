package lotto.domain.service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;

public class WinningLottoCalculator {

    public LottoResult calculate(LottoTicket lottoTicket, WinningLotto winningLotto) {
        Map<LottoRank, Integer> resultMap = new EnumMap<>(LottoRank.class);

        for (Lotto ticket : lottoTicket.getTickets()) {
            int matchCount = countMatchingNumbers(ticket.getNumbers(), winningLotto.getWinningNumbers());
            boolean bonusMatch = ticket.getNumbers().contains(winningLotto.getBonusNumber());

            LottoRank rank = LottoRank.valueOf(matchCount, bonusMatch);
            resultMap.put(rank, resultMap.getOrDefault(rank, 0) + 1);
        }

        return new LottoResult(resultMap);
    }

    private int countMatchingNumbers(List<Integer> ticketNumbers, List<Integer> winningNumbers) {
        return (int) ticketNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }
}
