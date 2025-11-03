package lotto.view;

import lotto.domain.Rank;
import java.util.*;

public class OutputView {
    public void printPurchased(List<List<Integer>> numbers) {
        System.out.println(numbers.size() + "개를 구매했습니다.");
        for (List<Integer> n : numbers) System.out.println(n);
        System.out.println();
    }
    public void printStats(Map<Rank, Long> counts, double yieldPercent) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (5,000원) - %d개%n", counts.getOrDefault(Rank.FIFTH, 0L));
        System.out.printf("4개 일치 (50,000원) - %d개%n", counts.getOrDefault(Rank.FOURTH, 0L));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", counts.getOrDefault(Rank.THIRD, 0L));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", counts.getOrDefault(Rank.SECOND, 0L));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", counts.getOrDefault(Rank.FIRST, 0L));
        System.out.printf("총 수익률은 %.1f%%입니다.%n", Math.round(yieldPercent * 10) / 10.0);
    }
}
