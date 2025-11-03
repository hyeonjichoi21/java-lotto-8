package lotto.domain;

public enum Rank {
    FIRST(6, false, 2_000_000_000L, "6개 일치 (2,000,000,000원)"),
    SECOND(5, true, 30_000_000L, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    THIRD(5, false, 1_500_000L, "5개 일치 (1,500,000원)"),
    FOURTH(4, false, 50_000L, "4개 일치 (50,000원)"),
    FIFTH(3, false, 5_000L, "3개 일치 (5,000원)"),
    NONE(0, false, 0L, "");

    private final int match;
    private final boolean needsBonus;
    private final long prize;
    private final String label;

    Rank(int match, boolean needsBonus, long prize, String label) {
        this.match = match;
        this.needsBonus = needsBonus;
        this.prize = prize;
        this.label = label;
    }

    public static Rank from(int match, boolean bonus) {
        if (match == 6) return FIRST;
        if (match == 5 && bonus) return SECOND;
        if (match == 5) return THIRD;
        if (match == 4) return FOURTH;
        if (match == 3) return FIFTH;
        return NONE;
    }
    public long prize() { return prize; }
    public String label() { return label; }
}
