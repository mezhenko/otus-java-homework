package homework;

import java.util.EnumMap;
import java.util.Map;

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
    public void getWithdraw(Integer amount) throws CantWithdrawAmountError {
        int amountToWithdraw = amount;
        Map<Banknote, Integer> banknotesCountToRemoveMap = new EnumMap<>(Banknote.class);
        for (Banknote banknote : Banknote.BANKNOTES_ORDERED_LIST) {
            int banknoteCount = Math.min(this.state.getState().get(banknote),
                    amountToWithdraw / Banknote.getValueByBanknote(banknote));
            amountToWithdraw -= Banknote.getValueByBanknote(banknote) * banknoteCount;
            banknotesCountToRemoveMap.put(banknote, banknoteCount);
        }
        if (amountToWithdraw != 0) {
            throw new CantWithdrawAmountError();
        }

        for (Map.Entry<Banknote, Integer> entry : banknotesCountToRemoveMap.entrySet()) {
            this.state.removeBanknotes(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void addBanknotes(Integer banknoteValue, Integer count) throws UnknownBanknoteError {
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
