package homework;

import homework.errors.CantWithdrawAmountError;
import homework.errors.UnknownBanknoteError;

/**
 * @author johnkel
 */
public interface AtmInterface {

    void getWithdraw(Integer amount) throws CantWithdrawAmountError;

    void addBanknotes(Integer banknoteValue, Integer count) throws UnknownBanknoteError;

    int getBalance();
}
