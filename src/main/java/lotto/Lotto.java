package lotto;

import lotto.util.ErrorMessage;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        // 필드 추가 없이, 전달받은 리스트를 정렬만 수행
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_COUNT);
        }
        if (new HashSet<>(numbers).size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_NUMBER);
        }
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE);
            }
        }
    }

    // 불변 리스트로 반환
    public List<Integer> numbers() {
        return List.copyOf(numbers);
    }
}
