package homework;

import homework.errors.CantWithdrawAmountError;
import homework.errors.UnknownBanknoteError;

/**
 * @author johnkel
 */
public interface AtmInterface {

    BanknotesStateInterface getWithdraw(int amount) throws CantWithdrawAmountError, UnknownBanknoteError;

    void addBanknotes(int banknoteValue, int count) throws UnknownBanknoteError;

    int getBalance();
}
