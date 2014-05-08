import model.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mhewedy on 07/05/14.
 */
public class Test {

    public static void main(String[] args) throws InvalidObjectException {

        List<Item> itemList = Item.list();

        System.out.println("****Menu****");
        for (Item item : itemList){
            System.out.println(item);
        }

        // Save Customer:
        Customer c = new Customer("abbas", "fernas", "0123456789", "Cairo, Egypt");
        boolean saved = c.save();
        if (saved) {
            System.out.println("\nCustomer saved.");
        }else{
            System.out.println("\ncustomer with same phone number already exists");
        }

        // Search for customer by phone number:
        Customer c2 = Customer.findByPhoneNumber("0123456789");
        System.out.println("\nfindByPhoneNumber: " + c2 );

        // Adding Packaged Order:
        Order order = new Order(new Date(2014, 5, 8), new Time(2, 51));
        order.addOrderItem(new OrderedItem(itemList.get(0), 1));
        order.addOrderItem(new OrderedItem(itemList.get(1), 1));
        order.save();
        System.out.println("Order add successfully: " + order);


        // Adding Delivery Order with customer already exits
        DeliveryOrder deliveryOrder = new DeliveryOrder(new Date(2014, 5, 8), new Time(2, 58));
        deliveryOrder.addOrderItem(new OrderedItem(itemList.get(0), 2));
        deliveryOrder.addOrderItem(new OrderedItem(itemList.get(0), 4));
        deliveryOrder.addCustomer(c2);
        deliveryOrder.save();
        System.out.println("Delivery Order add successfully: " + deliveryOrder);


        // Adding Delivery Order with customer already exits
        deliveryOrder = new DeliveryOrder(new Date(2014, 5, 8), new Time(2, 58));
        deliveryOrder.addOrderItem(new OrderedItem(itemList.get(0), 2));
        deliveryOrder.addOrderItem(new OrderedItem(itemList.get(0), 4));
        deliveryOrder.addCustomer(new Customer(null, null, "0123456789", null));
        deliveryOrder.save();
        System.out.println("Delivery Order add successfully: " + deliveryOrder);

        // Adding Delivery Order with a new customer
        deliveryOrder = new DeliveryOrder(new Date(2014, 5, 8), new Time(2, 58));
        deliveryOrder.addOrderItem(new OrderedItem(itemList.get(0), 2));
        deliveryOrder.addOrderItem(new OrderedItem(itemList.get(0), 4));
        deliveryOrder.addCustomer(new Customer("Wael", "Fayez", "934212", "Jeddah"));
        deliveryOrder.save();
        System.out.println("Delivery Order add successfully: " + deliveryOrder);

        // Confirming an order:
        System.out.println("Confirming orderId #" + deliveryOrder.getOrderId());
        deliveryOrder.confirm();
        System.out.println("Delivery Order confirmed: " + deliveryOrder);
    }
}
