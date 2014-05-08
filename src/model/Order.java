package model;

import storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhewedy on 07/05/14.
 */
public class Order {

    private static final double DISCOUNT_TOTAL = 200.0;
    private static final double DISCOUNT_RATION = 0.10;

    protected long orderId;
    private List<OrderedItem> orderedItemList = new ArrayList<>();
    private Date date;
    private Time time;

    public Order(Date date, Time time) {
        this.orderId = Storage.get().nextOrderId();
        this.date = date;
        this.time = time;
    }

    public long getOrderId() {
        return orderId;
    }

    public void addOrderItem(OrderedItem orderedItem) throws InvalidObjectException {
        if (orderedItem == null){
            throw new InvalidObjectException("orderItem");
        }
        orderedItem.validate();
        orderedItemList.add(orderedItem);
    }

    public final double getTotalPrice(){
        double total = 0.0;
        for (OrderedItem orderedItem : orderedItemList){
            total += orderedItem.getPrice();
        }
        return applyDiscount(total);
    }

    public void save() throws InvalidObjectException {
        double totalPrice = getTotalPrice();
        double minPrice = getMinOrderPrice();

        if (totalPrice < minPrice){
            throw new InvalidObjectException("total price " + totalPrice+ " less than min price " + minPrice);
        }
        Storage.get().placeOrder(this);
    }

    protected double getMinOrderPrice(){
        return 1;
    }

    private double applyDiscount(double total){
        if (total > DISCOUNT_TOTAL){
            System.out.println("applying discount of " + DISCOUNT_RATION );
            return total - (total * DISCOUNT_RATION);
        }else{
            return total;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        if (orderId != that.orderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (orderId ^ (orderId >>> 32));
    }

    @Override
    public String toString() {
        return orderId + "\t{" + orderedItemList + "}\t{" + date + "}\t{" + time + "}\t" + getTotalPrice();
    }
}
