package lotto.service;

import lotto.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.util.ErrorMessage;

import java.util.*;

public class LottoJudge {
    public Map<Rank, Long> summarize(List<Lotto> tickets, WinningNumbers win) {
        Map<Rank, Long> result = new EnumMap<>(Rank.class);
        for (Lotto t : tickets) {
            int match = win.matchCount(t);
            boolean bonus = win.bonusMatched(t);
            Rank rank = Rank.from(match, bonus);
            result.merge(rank, 1L, Long::sum);
        }
        return result;
    }

    public long totalPrize(Map<Rank, Long> counts) {
        long sum = 0L;
        for (Map.Entry<Rank, Long> e : counts.entrySet()) {
            sum += e.getKey().prize() * e.getValue();
        }
        return sum;
    }

    public double yieldPercent(long totalPrize, long paid) {
        if (paid <= 0 || paid % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT);
        }
        double percent = (totalPrize * 100.0) / paid;
        return Math.round(percent * 10) / 10.0; // 한 자리 반올림
    }

}
