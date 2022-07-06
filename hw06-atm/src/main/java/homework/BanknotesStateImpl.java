package homework;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import homework.errors.CantWithdrawAmountError;
import homework.errors.UnknownBanknoteError;

/**
 * @author johnkel
 */
public class BanknotesStateImpl implements BanknotesStateInterface {

    private final Map<Banknote, Integer> state;

    private BanknotesStateImpl(Map<Banknote, Integer> state) {
        this.state = state;
    }

    public static BanknotesStateImpl createEmpty() {
        Map<Banknote, Integer> map = new EnumMap<>(Banknote.class);
        for (Banknote b : Banknote.BANKNOTES_ORDERED_LIST) {
            map.put(b, 0);
        }
        return new BanknotesStateImpl(map);
    }

    @Override
    public Map<Banknote, Integer> getState() {
        return Collections.unmodifiableMap(state);
    }

    @Override
    public void addBanknotes(Banknote banknote, int count) throws UnknownBanknoteError {
        if (!this.state.containsKey(banknote)) {
            throw UnknownBanknoteError.createFromBanknote(banknote);
        }
        Integer oldCount = this.state.get(banknote);
        this.state.put(banknote, oldCount + count);
    }

    @Override
    public void removeBanknotesByState(BanknotesStateInterface otherState) throws CantWithdrawAmountError {
        for (Map.Entry<Banknote, Integer> entry : otherState.getState().entrySet()) {
            Banknote entryBanknote = entry.getKey();
            if (!this.state.containsKey(entryBanknote) || this.state.get(entryBanknote) < entry.getValue()) {
                throw new CantWithdrawAmountError();
            }
        }

        for (Map.Entry<Banknote, Integer> entry : otherState.getState().entrySet()) {
            Banknote entryBanknote = entry.getKey();
            this.state.put(entryBanknote, this.state.get(entryBanknote) - entry.getValue());
        }
    }
}
