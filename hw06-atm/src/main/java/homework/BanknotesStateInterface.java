package homework;

import java.util.Map;

import homework.errors.CantWithdrawAmountError;
import homework.errors.UnknownBanknoteError;

/**
 * @author johnkel
 */
public interface BanknotesStateInterface {

    Map<Banknote, Integer> getState();
    void addBanknotes(Banknote banknote, int count) throws UnknownBanknoteError;
    void removeBanknotesByState(BanknotesStateInterface otherState) throws CantWithdrawAmountError;
}
