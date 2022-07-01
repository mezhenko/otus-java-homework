package homework.errors;

/**
 * @author johnkel
 */
public class UnknownBanknoteError extends Exception {

    private final Integer unexpectedValue;

    public UnknownBanknoteError(Integer unexpectedValue) {
        this.unexpectedValue = unexpectedValue;
    }
}
