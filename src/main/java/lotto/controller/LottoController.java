package lotto.controller;

import java.math.BigDecimal;
import java.util.List;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.service.LottoService;
import lotto.controller.validation.AmountValidator;
import lotto.controller.validation.BonusNumberValidator;
import lotto.controller.validation.WinningNumberValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final AmountValidator amountValidator;
    private final WinningNumberValidator winningNumberValidator;
    private final BonusNumberValidator bonusNumberValidator;
    private final LottoService lottoService;

    public LottoController(
            InputView inputView,
            OutputView outputView,
            AmountValidator amountValidator,
            WinningNumberValidator winningNumberValidator,
            BonusNumberValidator bonusNumberValidator,
            LottoService lottoService
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.amountValidator = amountValidator;
        this.winningNumberValidator = winningNumberValidator;
        this.bonusNumberValidator = bonusNumberValidator;
        this.lottoService = lottoService;
    }

    public void run() {
        try {
            BigDecimal amount = amountValidator.validate(inputView.inputPurchaseAmount());

            LottoTicket lottoTicket = lottoService.purchase(amount);
            outputView.printLottoTickets(lottoTicket);

            List<Integer> winningNumbers = winningNumberValidator.validate(inputView.inputWinningNumbers());
            int bonusNumber = bonusNumberValidator.validate(inputView.inputBonusNumber());

            LottoResult lottoResult = lottoService.matchLottoNumber(lottoTicket, winningNumbers, bonusNumber);
            BigDecimal incomeRate = lottoService.checkIncomeRate(lottoResult, amount);
            outputView.printWinningStatistics(lottoResult, incomeRate);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }
}
