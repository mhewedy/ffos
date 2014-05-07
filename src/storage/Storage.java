package storage;

import model.Customer;
import model.DeliveryOrder;
import model.Item;
import model.PackagedOrder;

import java.util.List;

/**
 * Created by mhewedy on 07/05/14.
 */
public abstract class Storage {

    public static Storage get() {
        return new InMemoryStorage();
    }

    public abstract void saveCustomer(Customer customer);

    public abstract Customer searchCustomerByPhoneNumber(String phoneNumber);

    public abstract List<Item> listItems();

    public abstract long nextOrderId();

    public abstract void placeOrder(PackagedOrder order);

    public abstract void confirmOrder(DeliveryOrder order);
}
