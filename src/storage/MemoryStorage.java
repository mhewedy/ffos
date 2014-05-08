package storage;

import model.Customer;
import model.DeliveryOrder;
import model.Item;
import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mhewedy on 07/05/14.
 */
class MemoryStorage extends Storage {

    private static List<Customer> customerList = new ArrayList<>();
    private static List<Item> itemList = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();
    private static AtomicLong orderIdGenerator = new AtomicLong();

    @Override
    public boolean saveCustomer(Customer customer) {
        if (searchCustomerByPhoneNumber(customer.getPhoneNumber()) == null){
            customerList.add(customer);
            return true;
        }
        return false;
    }

    @Override
    public Customer searchCustomerByPhoneNumber(String phoneNumber) {
        Customer customer = new Customer(null, null, phoneNumber, null);

        int index = customerList.indexOf(customer);
        if (index != -1) {
            return customerList.get(index);
        }
        return null;
    }

    @Override
    public List<Item> listItems() {
        return itemList;
    }

    @Override
    public long nextOrderId() {
        return orderIdGenerator.incrementAndGet();
    }

    @Override
    public void placeOrder(Order order) {
        orderList.add(order);
    }

    @Override
    public void confirmOrder(DeliveryOrder order) {
        int index = orderList.indexOf(order);
        if (index != -1){
            DeliveryOrder o = (DeliveryOrder) orderList.get(index);
            o.setDeliveryStatus(order.getDeliveryStatus());
        }
    }

    static {
        itemList.add(new Item("Beefburger", 19.99, "Beefburger Sandwich"));
        itemList.add(new Item("Cheeseburger", 9.99, "Cheeseburger Sandwich"));
        itemList.add(new Item("Chickenburger", 7.99, "Chickenburger Sandwich"));
        itemList.add(new Item("Egg McMuffin", 25.99, "Egg McMuffin Sandwich"));
        itemList.add(new Item("Hotcakes Meal", 11.99, "Hotcakes Meal"));
        itemList.add(new Item("Big Breakfast Meal", 24.99, "Big Breakfast Meal"));
    }
}
