package lotto.domain;

import java.math.BigDecimal;

public enum LottoRank {
    FIRST(6, false, BigDecimal.valueOf(2_000_000_000), "6개 일치"),
    SECOND(5, true, BigDecimal.valueOf(30_000_000), "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, BigDecimal.valueOf(1_500_000), "5개 일치"),
    FOURTH(4, false, BigDecimal.valueOf(50_000), "4개 일치"),
    FIFTH(3, false, BigDecimal.valueOf(5_000), "3개 일치"),
    NONE(0, false, BigDecimal.ZERO, "");

    private final int matchCount;
    private final boolean matchBonus;
    private final BigDecimal prize;
    private final String description;

    LottoRank(int matchCount, boolean matchBonus, BigDecimal prize, String description) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.prize = prize;
        this.description = description;
    }

    public static LottoRank valueOf(int matchCount, boolean matchBonus) {
        return java.util.Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount && rank.matchBonus == matchBonus)
                .findFirst()
                .orElse(getRankByMatchCount(matchCount));
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public String getDescription() {
        return description;
    }

    private static LottoRank getRankByMatchCount(int matchCount) {
        if (matchCount == 5) {
            return THIRD;
        }
        if (matchCount == 4) {
            return FOURTH;
        }
        if (matchCount == 3) {
            return FIFTH;
        }
        return NONE;
    }
}
