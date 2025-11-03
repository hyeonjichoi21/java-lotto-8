package lotto.app;

import lotto.Lotto;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.infra.NumberPicker;
import lotto.infra.RandomNumberPicker;
import lotto.service.LottoJudge;
import lotto.util.ErrorMessage;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private static final int PRICE = 1000;

    private final InputView in = new InputView();
    private final OutputView out = new OutputView();
    private final LottoJudge judge = new LottoJudge();
    private final NumberPicker picker = new RandomNumberPicker();

    public void run() {
        try {
            long amount = readAmount();
            int count = (int) (amount / PRICE);

            List<Lotto> tickets = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                tickets.add(new Lotto(picker.pickSix()));
            }
            out.printPurchased(tickets.stream().map(Lotto::numbers).collect(Collectors.toList()));

            WinningNumbers win = readWinningOnce();

            var result = judge.summarize(tickets, win);
            long totalPrize = judge.totalPrize(result);
            double yield = judge.yieldPercent(totalPrize, amount);
            out.printStats(result, yield);

        } catch (IllegalArgumentException e) {
            // 이미 내부에서 [ERROR] 출력한 경우라도, 안전하게 다시 한 번 처리
            if (!e.getMessage().startsWith("[ERROR]")) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    // 구입 금액을 파싱하고 유효성 검증에 실패하면 [ERROR] 출력 후 IllegalArgumentException을 던진다.
    private long readAmount() {
        try {
            String raw = in.readAmount().trim();
            long v = Long.parseLong(raw);
            if (v <= 0 || v % PRICE != 0) {
                System.out.println(ErrorMessage.INVALID_AMOUNT);
                throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT);
            }
            return v;
        } catch (NumberFormatException e) {
            System.out.println(ErrorMessage.INVALID_AMOUNT);
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT, e);
        }
    }

    // 당첨 번호와 보너스 번호를 파싱하고, 검증 실패 시 [ERROR] 출력 후 IllegalArgumentException을 던진다.
    private WinningNumbers readWinningOnce() {
        String rawWinning = in.readWinning();
        String rawBonus = in.readBonus();

        try {
            List<Integer> numbers = Arrays.stream(rawWinning.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();

            int bonus = Integer.parseInt(rawBonus.trim());
            return new WinningNumbers(numbers, bonus);
        } catch (Exception e) {
            // 어떤 이유로든 실패 시 공통 에러 문구 노출 후 예외 전파
            System.out.println(ErrorMessage.INVALID_INPUT);
            if (e instanceof IllegalArgumentException iae) {
                throw iae;
            }
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT, e);
        }
    }
}
