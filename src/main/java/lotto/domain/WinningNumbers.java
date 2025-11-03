package lotto.domain;

import lotto.util.ErrorMessage;

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
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_NUMBERS);
        }
        if (new HashSet<>(numbers).size() != SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER);
        }
        for (int n : numbers) {
            if (n < MIN || n > MAX) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE);
            }
        }
    }

    private void validateBonus(int b) {
        if (b < MIN || b > MAX) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_RANGE);
        }
        if (winning.contains(b)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS);
        }
    }

    public int matchCount(lotto.Lotto lotto) {
        return (int) lotto.numbers().stream().filter(winning::contains).count();
    }

    public boolean bonusMatched(lotto.Lotto lotto) {
        return lotto.numbers().contains(bonus);
    }
}
