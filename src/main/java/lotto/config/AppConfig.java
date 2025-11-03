package lotto.config;

import lotto.controller.LottoController;
import lotto.domain.service.IncomeRateCalculator;
import lotto.domain.service.LottoTicketCalculator;
import lotto.domain.service.WinningLottoCalculator;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.domain.generator.LottoNumberGeneratorImpl;
import lotto.service.LottoService;
import lotto.controller.validation.BonusNumberValidator;
import lotto.controller.validation.WinningNumberValidator;
import lotto.controller.validation.AmountValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    private AppConfig() {

    }

    public LottoController lottoController() {
        return new LottoController(
                inputView(),
                outputView(),
                amountValidator(),
                winningNumberValidator(),
                bonusNumberValidator(),
                lottoService()
        );
    }

    private LottoService lottoService() {
        return new LottoService(
                lottoNumberGenerator(),
                lottoTicketCalculator(),
                winningLottoCalculator(),
                incomeRateCalculator()
        );
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private LottoNumberGenerator lottoNumberGenerator() {
        return new LottoNumberGeneratorImpl();
    }

    private WinningNumberValidator winningNumberValidator() {
        return new WinningNumberValidator();
    }

    private BonusNumberValidator bonusNumberValidator() {
        return new BonusNumberValidator();
    }

    private AmountValidator amountValidator() {
        return new AmountValidator();
    }

    private LottoTicketCalculator lottoTicketCalculator() {
        return new LottoTicketCalculator();
    }

    private WinningLottoCalculator winningLottoCalculator() {
        return new WinningLottoCalculator();
    }

    private IncomeRateCalculator incomeRateCalculator() {
        return new IncomeRateCalculator();
    }
}
