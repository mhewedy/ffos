import model.*;

import java.util.List;

/**
 * Created by mhewedy on 07/05/14.
 */
public class Test {

    public static void main(String[] args) throws InvalidObjectException {

        List<Item> itemList = Item.list();

        OrderedItem item = new OrderedItem(itemList.get(0), 4);
        DeliveryOrder order = new DeliveryOrder(new Date(1, 1, 2014), new Time(12, 30));
        order.addCustomer(new Customer(null, "wael", "123", "place"));
        order.addOrderItem(item);
        order.save();
        System.out.println(order);
        order.confirm();
        System.out.println(order);

        DeliveryOrder order1 = new DeliveryOrder(new Date(1, 1, 2014), new Time(12, 30));
        order1.addCustomer(new Customer(null, null, "123", null));
        order1.addOrderItem(item);
        order1.addOrderItem(new OrderedItem(itemList.get(1), 10));
        order1.save();

        System.out.println(order1);


    }
}
