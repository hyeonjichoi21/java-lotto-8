package lotto.domain;

import java.util.*;

public class WinningNumbers {
    private static final int SIZE = 6;
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final Set<Integer> winning;
    private final int bonus;

    public WinningNumbers(List<Integer> numbers, int bonus) {
        validate(numbers);
        this.winning = new HashSet<>(numbers);
        validateBonus(bonus);
        this.bonus = bonus;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(numbers).size() != SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
        for (int n : numbers) {
            if (n < MIN || n > MAX) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
            }
        }
    }

    private void validateBonus(int b) {
        if (b < MIN || b > MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
        if (winning.contains(b)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public int matchCount(lotto.Lotto lotto) {
        return (int) lotto.numbers().stream().filter(winning::contains).count();
    }

    public boolean bonusMatched(lotto.Lotto lotto) {
        return lotto.numbers().contains(bonus);
    }
}
