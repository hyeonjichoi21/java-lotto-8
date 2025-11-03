package lotto.service;

import lotto.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoJudgeTest {

    LottoJudge judge = new LottoJudge();

    @Test
    void summarize는_등수별_개수를_정확히_집계한다() {
        // given
        WinningNumbers win = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // 1등
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // 2등
                new Lotto(List.of(1, 2, 3, 4, 5, 8)), // 3등
                new Lotto(List.of(1, 2, 3, 4, 8, 9)), // 4등
                new Lotto(List.of(1, 2, 3, 8, 9, 10)) // 5등
        );

        // when
        Map<Rank, Long> result = judge.summarize(tickets, win);

        // then
        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(1);
    }

    @Test
    void yieldPercent는_한_자리수_반올림으로_계산된다() {
        double result = judge.yieldPercent(5000, 8000);
        assertThat(result).isEqualTo(62.5);
    }
}
