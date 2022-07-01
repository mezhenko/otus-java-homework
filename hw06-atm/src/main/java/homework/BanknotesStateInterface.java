package homework;

import java.util.Map;

import homework.errors.CantWithdrawAmountError;

/**
 * @author johnkel
 */
public interface BanknotesStateInterface {

    Map<Banknote, Integer> getState();
    void addBanknotes(Banknote banknote, Integer count);
    void removeBanknotes(Banknote banknote, Integer count) throws CantWithdrawAmountError;
}
