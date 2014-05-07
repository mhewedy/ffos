package model;

/**
 * Created by mhewedy on 07/05/14.
 */
public class OrderedItem{

    private Item item;
    private int quantity;

    public OrderedItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void validate() throws InvalidObjectException {
        if (item == null){
            throw new InvalidObjectException("item");
        }
        if (quantity <= 0){
            throw new InvalidObjectException("quantity");
        }
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice(){
        return quantity * item.getPrice();
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
