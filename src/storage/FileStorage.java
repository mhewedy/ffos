package storage;

import model.Customer;
import model.DeliveryOrder;
import model.Item;
import model.Order;
import util.IOUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhewedy on 08/05/14.
 */
public class FileStorage extends Storage{

    private static final String BASE_DIR = System.getProperty("user.home") + "/ffos/";
    private static final String CUSTOMERS_FILE = BASE_DIR + "customers.dat";
    private static final String ITEMS_FILE = BASE_DIR + "items.dat";
    private static final String ORDERS_FILE = BASE_DIR + "orders.dat";
    private static final String ORDER_ID_FILE = BASE_DIR + "orderId.dat";

    @Override
    public boolean saveCustomer(Customer customer) {
        if (searchCustomerByPhoneNumber(customer.getPhoneNumber()) == null){
            IOUtil.writeLine(CUSTOMERS_FILE, customer.toString(), true);
            return true;
        }
        return false;
    }

    @Override
    public Customer searchCustomerByPhoneNumber(String phoneNumber) {
        List<Customer> customerList = new ArrayList<>();

        List<String> lines = IOUtil.readFile(CUSTOMERS_FILE);
        for (String line : lines){
            customerList.add(customerFromFileFormat(line));
        }

        Customer customer = new Customer(null, null, phoneNumber, null);
        int index = customerList.indexOf(customer);
        if (index != -1) {
            return customerList.get(index);
        }
        return null;
    }

    @Override
    public List<Item> listItems() {
        List<Item> itemList = new ArrayList<>();

        List<String> lines = IOUtil.readFile(ITEMS_FILE);
        for (String line : lines){
            itemList.add(itemFromFileFormat(line));
        }
        return itemList;
    }

    @Override
    public synchronized long nextOrderId() {
        long orderId = 1;

        List<String> list = IOUtil.readFile(ORDER_ID_FILE);
        if (list != null && !list.isEmpty()){
            orderId = Long.parseLong(list.get(0));
        }
        IOUtil.writeLine(ORDER_ID_FILE, String.valueOf(orderId+1), false);

        return orderId;
    }

    @Override
    public void placeOrder(Order order) {
        IOUtil.writeLine(ORDERS_FILE, order.toString(), true);
    }

    @Override
    public void confirmOrder(DeliveryOrder order) {
        List<String> lines = IOUtil.readFile(ORDERS_FILE);

        for (int i=0; i< lines.size(); i++){
            String line = lines.get(i);
            DeliveryOrder currentOrder = new DeliveryOrder(Long.parseLong(line.split("\t")[0]));

            if (currentOrder.equals(order)){
                line = order.toString();
            }
            IOUtil.writeLine(ORDERS_FILE, line, i != 0);
        }
    }

    private Customer customerFromFileFormat(String line){
        String[] arr = line.split("\\t");
        return new Customer(arr[0], arr[1], arr[2]);
    }

    private Item itemFromFileFormat(String line){
        String[] arr = line.split("\\t");
        return new Item(arr[0], Double.parseDouble(arr[1]), arr[2]);
    }

}
