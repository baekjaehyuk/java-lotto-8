package lotto.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;

public class OutputView {

    private static final String PURCHASED_TICKETS_MESSAGE = "\n%d개를 구매했습니다.";
    private static final String WINNING_STATISTICS_TITLE = "\n당첨 통계";
    private static final String SEPARATOR_LINE = "---";
    private static final String RANK_RESULT_FORMAT = "%s (%s원) - %d개";
    private static final String TOTAL_PROFIT_MESSAGE = "총 수익률은 %.1f%%입니다.";

    public void printLottoTickets(LottoTicket lottoTicket){
        System.out.printf((PURCHASED_TICKETS_MESSAGE) + "%n", lottoTicket.size());
        List<Lotto> tickets = lottoTicket.getTickets();
        for (Lotto ticket : tickets) {
            System.out.println(ticket.getNumbers());
        }
    }

    public void printWinningStatistics(LottoResult result, BigDecimal incomeRate) {
        System.out.println(WINNING_STATISTICS_TITLE);
        System.out.println(SEPARATOR_LINE);

        Arrays.stream(LottoRank.values())
                .filter(rank -> rank != LottoRank.NONE)
                .forEach(rank -> {
                    String rankResult = formatRankResult(rank, result.getCountByRank(rank));
                    System.out.println(rankResult);
                });

        String formattedIncomeRate = formatIncomeRate(incomeRate);
        System.out.println(formattedIncomeRate);
    }

    public void printError(String message) {
        System.out.println(message);
    }

    private String formatRankResult(LottoRank rank, int count) {
        String formattedPrize = NumberFormat.getInstance().format(rank.getPrize());
        return String.format(RANK_RESULT_FORMAT, rank.getDescription(), formattedPrize, count);
    }

    private String formatIncomeRate(BigDecimal incomeRate) {
        BigDecimal roundedRate = incomeRate.setScale(1, RoundingMode.HALF_UP);
        return String.format(TOTAL_PROFIT_MESSAGE, roundedRate);
    }
}
