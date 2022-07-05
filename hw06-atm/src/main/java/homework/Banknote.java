package homework;

import java.util.Arrays;
import java.util.List;

import homework.errors.UnknownBanknoteError;

/**
 * @author johnkel
 */
public enum Banknote {
    BANKNOTE_100,
    BANKNOTE_500,
    BANKNOTE_1000,
    BANKNOTE_5000;

    public static final List<Banknote> BANKNOTES_ORDERED_LIST =
            Arrays.asList(BANKNOTE_5000, BANKNOTE_1000, BANKNOTE_500, BANKNOTE_100);

    public static Banknote getBanknoteByValue(int banknoteValue) throws UnknownBanknoteError {
        return switch (banknoteValue) {
            case 100 -> BANKNOTE_100;
            case 500 -> BANKNOTE_500;
            case 1000 -> BANKNOTE_1000;
            case 5000 -> BANKNOTE_5000;
            default -> throw UnknownBanknoteError.createFromValue(banknoteValue);
        };
    }
    public static int getValueByBanknote(Banknote banknote){
        return switch (banknote) {
            case BANKNOTE_100 -> 100;
            case BANKNOTE_500 -> 500;
            case BANKNOTE_1000 -> 1000;
            case BANKNOTE_5000 -> 5000;
        };
    }


}
