package lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.TreeMap;

public class OutputView {
    private static final String TICKET_NUMBER_MESSAGE = "%d개를 구매했습니다.\n";
    private static final String LOTTO_NUMBERS_MESSAGE = "[%d, %d, %d, %d, %d, %d]\n";
    private static final String RESULT_HEADLINE_MESSAGE = "당첨 통계\n---";
    private static final String PRIZE_FREQ_MESSAGE = "%d개 일치 (%s원)%s - %d개\n";
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %.1f%%입니다.\n";

    public static void printTicketNumber(long ticketNumber) {
        System.out.printf(TICKET_NUMBER_MESSAGE, ticketNumber);
    }

    public static void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.printf(LOTTO_NUMBERS_MESSAGE, lotto.getNumbers());
        }
        System.out.println();
    }

    public static void printPrizeStats(TreeMap<PrizeGrade, Integer> gradeDist) {
        System.out.println(RESULT_HEADLINE_MESSAGE);
        gradeDist.remove(PrizeGrade.NO_PRIZE);
        for (PrizeGrade prizeGrade : gradeDist.descendingKeySet()) {
            System.out.printf(PRIZE_FREQ_MESSAGE, prizeGrade.getMatchCount(), withCommaFormat(prizeGrade.getPrizeMoney()), prizeGrade.getAdditionalMessage(), gradeDist.get(prizeGrade));
        }
    }

    private static String withCommaFormat(int number) {
        DecimalFormat commaFormat = new DecimalFormat(StringConstants.COMMA_FORMAT);
        return commaFormat.format(number);
    }

    public static void printProfitRate(double profitRate) {
        System.out.printf(PROFIT_RATE_MESSAGE, profitRate * NumberConstants.HUNDRED);
    }
}
