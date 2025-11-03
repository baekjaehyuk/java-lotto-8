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
            BigDecimal amount = getValidAmount();

            LottoTicket lottoTicket = lottoService.purchase(amount);
            outputView.printLottoTickets(lottoTicket);

            List<Integer> winningNumbers = getValidWinningNumbers();
            int bonusNumber = getValidBonusNumber(winningNumbers);

            LottoResult lottoResult = lottoService.matchLottoNumber(lottoTicket, winningNumbers, bonusNumber);
            BigDecimal incomeRate = lottoService.checkIncomeRate(lottoResult, amount);
            outputView.printWinningStatistics(lottoResult, incomeRate);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }

    private BigDecimal getValidAmount() {
        while (true) {
            try {
                String amountInput = inputView.inputPurchaseAmount();
                return amountValidator.validate(amountInput);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private List<Integer> getValidWinningNumbers() {
        while (true) {
            try {
                String winningNumbersInput = inputView.inputWinningNumbers();
                return winningNumberValidator.validate(winningNumbersInput);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private int getValidBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                String bonusNumberInput = inputView.inputBonusNumber();
                return bonusNumberValidator.validate(bonusNumberInput, winningNumbers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
