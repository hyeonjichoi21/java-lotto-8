package lotto.domain;

import lotto.Lotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningNumbersTest {

    @Test
    void 유효하지_않은_보너스_번호는_예외를_던진다() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> new WinningNumbers(numbers, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void matchCount는_일치한_번호의_수를_반환한다() {
        WinningNumbers win = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 7, 8, 9));
        assertThat(win.matchCount(lotto)).isEqualTo(3);
    }

    @Test
    void bonusMatched는_보너스_번호가_있을_때_true를_반환한다() {
        WinningNumbers win = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 2, 3, 7, 8, 9));
        assertThat(win.bonusMatched(lotto)).isTrue();
    }
}
