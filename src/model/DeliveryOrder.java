package model;

import storage.Storage;


/**
 * Created by mhewedy on 08/05/14.
 */
public class DeliveryOrder extends PackagedOrder {

    private static final double MIN_ORDER_PRICE = 50;

    private Customer customer;
    private DeliveryStatus deliveryStatus = DeliveryStatus.NotDelivered;

    public DeliveryOrder(Date date, Time time) {
        super(date, time);
    }

    @Override
    public double getMinOrderPrice() {
        return MIN_ORDER_PRICE;
    }

    public void addCustomer(Customer customer) throws InvalidObjectException {
        Customer c = Storage.get().searchCustomerByPhoneNumber(customer.getPhoneNumber());
        if (c != null){
            System.out.println("customer found");
            this.customer = c;
        }else{
            customer.save();
            this.customer = customer;
        }
    }

    @Override
    public void save() throws InvalidObjectException {
        if (customer == null){
            throw new InvalidObjectException("customer");
        }
        super.save();
    }

    public void confirm(){
        this.deliveryStatus = DeliveryStatus.Delivered;
        Storage.get().confirmOrder(this);
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public enum DeliveryStatus{
        Delivered, NotDelivered;
    }

    @Override
    public String toString() {
        return super.toString() + "DeliveryOrder{" +
                "customer=" + customer +
                ", deliveryStatus=" + deliveryStatus +
                '}';
    }
}
