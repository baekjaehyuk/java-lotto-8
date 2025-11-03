package lotto.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;
import lotto.domain.service.IncomeRateCalculator;
import lotto.domain.service.LottoTicketCalculator;
import lotto.domain.service.WinningLottoCalculator;
import lotto.domain.generator.LottoNumberGenerator;

public class LottoService {

    private final LottoNumberGenerator lottoNumberGenerator;
    private final LottoTicketCalculator lottoTicketCalculator;
    private final WinningLottoCalculator winningLottoCalculator;
    private final IncomeRateCalculator incomeRateCalculator;

    public LottoService(
            LottoNumberGenerator lottoNumberGenerator,
            LottoTicketCalculator lottoTicketCalculator,
            WinningLottoCalculator winningLottoCalculator,
            IncomeRateCalculator incomeRateCalculator
    ) {
        this.lottoNumberGenerator = lottoNumberGenerator;
        this.lottoTicketCalculator = lottoTicketCalculator;
        this.winningLottoCalculator = winningLottoCalculator;
        this.incomeRateCalculator = incomeRateCalculator;
    }

    public LottoTicket purchase(BigDecimal amount) {
        int ticketCount = lottoTicketCalculator.calculate(amount);

        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new Lotto(lottoNumberGenerator.generate()));
        }

        return new LottoTicket(amount, tickets);
    }

    public LottoResult matchLottoNumber(LottoTicket lottoTicket, List<Integer> winningNumbers, int bonusNumber) {
        return winningLottoCalculator.calculate(lottoTicket, new WinningLotto(winningNumbers, bonusNumber));
    }

    public BigDecimal checkIncomeRate(LottoResult lottoResult, BigDecimal amount) {
        return incomeRateCalculator.calculate(lottoResult, amount);
    }
}
