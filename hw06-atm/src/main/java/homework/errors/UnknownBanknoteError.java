package homework.errors;

import javax.annotation.Nullable;

import homework.Banknote;

/**
 * @author johnkel
 */
public class UnknownBanknoteError extends Exception {

    @Nullable
    private final Integer unexpectedValue;
    @Nullable
    private final Banknote unexpectedBanknote;

    public UnknownBanknoteError(Integer value, Banknote banknote) {
        this.unexpectedValue = value;
        this.unexpectedBanknote = banknote;
    }

    public static UnknownBanknoteError createFromValue(int value) {
        return new UnknownBanknoteError(value, null);
    }

    public static UnknownBanknoteError createFromBanknote(Banknote banknote) {
        return new UnknownBanknoteError(null, banknote);
    }
}
