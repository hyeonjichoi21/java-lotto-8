package lotto.app;

import lotto.Lotto;
import lotto.domain.WinningNumbers;
import lotto.infra.NumberPicker;
import lotto.infra.RandomNumberPicker;
import lotto.service.LottoJudge;
import lotto.util.ErrorMessage;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;
import java.util.stream.Collectors;

public class LottoController {
    private static final int PRICE = 1000;

    private final InputView in = new InputView();
    private final OutputView out = new OutputView();
    private final LottoJudge judge = new LottoJudge();
    private final NumberPicker picker = new RandomNumberPicker();

    public void run() {
        long amount = readAmountRetry();
        int count = (int) (amount / PRICE);

        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(new Lotto(picker.pickSix()));
        }
        out.printPurchased(tickets.stream().map(Lotto::numbers).collect(Collectors.toList()));

        WinningNumbers win = readWinningRetry();

        Map<lotto.domain.Rank, Long> result = judge.summarize(tickets, win);
        long totalPrize = judge.totalPrize(result);
        double yield = judge.yieldPercent(totalPrize, amount);
        out.printStats(result, yield);
    }

    private long readAmountRetry() {
        while (true) {
            try {
                String raw = in.readAmount().trim();
                long v = Long.parseLong(raw);
                if (v <= 0 || v % PRICE != 0) throw new IllegalArgumentException();
                return v;
            } catch (Exception e) {
                System.out.println(ErrorMessage.INVALID_AMOUNT);
            }
        }
    }

    private WinningNumbers readWinningRetry() {
        while (true) {
            try {
                List<Integer> nums = Arrays.stream(in.readWinning().split(","))
                        .map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
                int bonus = Integer.parseInt(in.readBonus().trim());
                return new WinningNumbers(nums, bonus);
            } catch (Exception e) {
                System.out.println(ErrorMessage.INVALID_INPUT);
            }
        }
    }
}
