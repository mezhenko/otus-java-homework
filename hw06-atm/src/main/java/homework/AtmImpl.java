package homework;

import homework.errors.CantWithdrawAmountError;
import homework.errors.UnknownBanknoteError;

/**
 * @author johnkel
 */
public class AtmImpl implements AtmInterface {

    private final BanknotesStateInterface state;

    public AtmImpl (BanknotesStateInterface state) {
        this.state = state;
    }

    public static AtmImpl createEmpty() {
        return new AtmImpl(BanknotesStateImpl.createEmpty());
    }

    @Override
    public BanknotesStateInterface getWithdraw(int amount) throws CantWithdrawAmountError, UnknownBanknoteError {
        int amountToWithdraw = amount;
        BanknotesStateInterface banknotesStateToRemove = BanknotesStateImpl.createEmpty();
        for (Banknote banknote : Banknote.BANKNOTES_ORDERED_LIST) {
            int banknoteCount = Math.min(this.state.getState().get(banknote),
                    amountToWithdraw / Banknote.getValueByBanknote(banknote));
            amountToWithdraw -= Banknote.getValueByBanknote(banknote) * banknoteCount;
            banknotesStateToRemove.addBanknotes(banknote, banknoteCount);
        }
        if (amountToWithdraw != 0) {
            throw new CantWithdrawAmountError();
        }

        this.state.removeBanknotesByState(banknotesStateToRemove);

        return banknotesStateToRemove;
    }

    @Override
    public void addBanknotes(int banknoteValue, int count) throws UnknownBanknoteError {
        Banknote banknote = Banknote.getBanknoteByValue(banknoteValue);
        this.state.addBanknotes(banknote, count);
    }

    @Override
    public int getBalance() {
        return this.state.getState().entrySet().stream()
                .map(e -> {
                    return Banknote.getValueByBanknote(e.getKey()) * e.getValue();
                })
                .mapToInt(Integer::intValue).sum();
    }
}
