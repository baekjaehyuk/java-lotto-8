package lotto.domain;

import java.util.Map;

public class LottoResult {

    private final Map<LottoRank, Integer> result;

    public LottoResult(Map<LottoRank, Integer> result) {
        this.result = result;
    }

    public Map<LottoRank, Integer> getResult() {
        return result;
    }

    public int getCountByRank(LottoRank rank) {
        return result.getOrDefault(rank, 0);
    }
}
