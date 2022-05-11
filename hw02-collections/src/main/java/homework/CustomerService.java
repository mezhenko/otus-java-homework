package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Maps;

public class CustomerService {

    private final TreeMap<Customer, String> customerStringMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> smallest = customerStringMap.firstEntry();
        if (smallest == null) {
            return null;
        }
        return Maps.immutableEntry((Customer) smallest.getKey().clone(), smallest.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> next =  customerStringMap.higherEntry(customer);
        if (next == null) {
            return null;
        }
        return Maps.immutableEntry((Customer) next.getKey().clone(), next.getValue());
    }

    public void add(Customer customer, String data) {
        customerStringMap.put(customer, data);
    }
}
