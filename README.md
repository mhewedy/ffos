ffos (Fast Food Ordering System)
====

A sample library about Fast Food Ordering System

Features:
========
1. View Menu Items.
2. Add Customer.
2. Search for customer by phone number.
3. Place a new Order. (either packed Order or Delivery Order)
4. Confirm a Delivery Order.

Installation:
========

Download and unzip [ffos.zip](https://github.com/MuhammadHewedy/ffos/raw/master/ffos.zip) into your home directory.
*    On windows7, put it in `C:\Users\<username>\`
*    On linux, put it in `/home/<username>/`  
Where `<username>` is your `username`

Example:
========

        // View Menu Items
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


The output should be:

    ****Menu****
    Cheese Burger	9.99	Cheese Burger Sandwitch
    Chicken	8.99	Chicken Sandwitch
    Falafel	8.99	Falafel

    Customer saved.

    findByPhoneNumber: abbas fernas	0123456789	Cairo, Egypt
    Order add successfully: 1	{[{Cheese Burger	9.99	Cheese Burger Sandwitch}	1, {Chicken	8.99	Chicken Sandwitch}	1]}	{2014-5-8}	{2:51}	18.98
    customer found
    Delivery Order add successfully: 2	{[{Cheese Burger	9.99	Cheese Burger Sandwitch}	2, {Cheese Burger	9.99	Cheese Burger Sandwitch}	4]}	{2014-5-8}	{2:58}	59.94	{abbas fernas	0123456789	Cairo, Egypt}	NotDelivered
    customer found
    Delivery Order add successfully: 3	{[{Cheese Burger	9.99	Cheese Burger Sandwitch}	2, {Cheese Burger	9.99	Cheese Burger Sandwitch}	4]}	{2014-5-8}	{2:58}	59.94	{abbas fernas	0123456789	Cairo, Egypt}	NotDelivered
    Delivery Order add successfully: 4	{[{Cheese Burger	9.99	Cheese Burger Sandwitch}	2, {Cheese Burger	9.99	Cheese Burger Sandwitch}	4]}	{2014-5-8}	{2:58}	59.94	{Wael Fayez	934212	Jeddah}	NotDelivered
    Confirming orderId #4
    Delivery Order confirmed: 4	{[{Cheese Burger	9.99	Cheese Burger Sandwitch}	2, {Cheese Burger	9.99	Cheese Burger Sandwitch}	4]}	{2014-5-8}	{2:58}	59.94	{Wael Fayez	934212	Jeddah}	Delivered

And now the order data is saved at your home directory at folder you extracted above at `user.name/ffos/`

Good luck!