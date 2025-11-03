package lotto.util;

public final class ErrorMessage {

    private ErrorMessage() {
        // 인스턴스 생성 방지
    }

    // 공통 접두사
    public static final String PREFIX = "[ERROR] ";

    // ===== 입력 관련 =====
    public static final String INVALID_INPUT = PREFIX + "입력값을 다시 확인해 주세요.";
    public static final String INVALID_AMOUNT = PREFIX + "구입 금액은 1,000원 단위의 양수여야 합니다.";

    // ===== 로또 번호 관련 =====
    public static final String INVALID_LOTTO_COUNT = PREFIX + "로또 번호는 6개여야 합니다.";
    public static final String DUPLICATE_NUMBER = PREFIX + "로또 번호는 중복될 수 없습니다.";
    public static final String INVALID_RANGE = PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다.";

    // ===== 당첨 번호 관련 =====
    public static final String INVALID_WINNING_NUMBERS = PREFIX + "당첨 번호는 1부터 45 사이의 중복되지 않은 6개여야 합니다.";
    public static final String DUPLICATE_BONUS = PREFIX + "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    public static final String INVALID_BONUS_RANGE = PREFIX + "보너스 번호는 1부터 45 사이여야 합니다.";
}
