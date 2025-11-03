package lotto;

import lotto.util.ErrorMessage;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_COUNT);
        }
    }

    public List<Integer> numbers() {
        // 불변 리스트 보장
        return List.copyOf(numbers);
    }

}
