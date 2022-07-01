package homework;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import homework.errors.CantWithdrawAmountError;

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
    public void addBanknotes(Banknote banknote, Integer count) {
        Integer oldCount = this.state.get(banknote);
        this.state.put(banknote, oldCount + count);
    }

    @Override
    public void removeBanknotes(Banknote banknote, Integer count) throws CantWithdrawAmountError {
        Integer oldCount = this.state.get(banknote);
        if (oldCount < count) {
            throw new CantWithdrawAmountError();
        }
        this.state.put(banknote, oldCount - count);
    }
}
