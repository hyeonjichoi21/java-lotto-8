package lotto.infra;

import java.util.List;

public interface NumberPicker {
    List<Integer> pickSix(); // 1~45 유니크 6개, 오름차순
}
