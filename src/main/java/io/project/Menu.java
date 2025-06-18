package io.project;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Menu {
    public void start(){
        MenuItem burger = new MenuItem("Burger", 10, "fast food");
        MenuItem pizza = new MenuItem("Pizza", 12, "fast food");
        MenuItem ramen = new MenuItem("Ramen", 8, "meal");

        Map<String, MenuItem> menuItemMap = new HashMap<>();
        List<MenuItem> menuItems = new ArrayList<>();
        Queue<Order> orders = new ConcurrentLinkedQueue<>();
        Queue<Order> completedOrders = new ConcurrentLinkedQueue<>();
        menuItemMap.put("Burger", burger);
        menuItemMap.put("Pizza", pizza);
        menuItemMap.put("Ramen", ramen);

        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Welcome to Restaurant-----");
        System.out.println();
        System.out.println();

        boolean isFinished = false;
        int id = 1;

        while(!isFinished){
            System.out.println("1. Show menu");
            System.out.println("2. Add menu item");
            System.out.println("3. Take order");
            System.out.println("4. Process next order");
            System.out.println("5. Show completed orders");
            System.out.println("6. Generate daily report");
            System.out.println("7. Exit");

            int chooseOption = scanner.nextInt();
            scanner.nextLine();

            switch (chooseOption){
                case 1:
                    int number = 1;
                    for(Map.Entry<String, MenuItem> entry : menuItemMap.entrySet()){
                        System.out.println(number + ". " + entry.getKey());
                        number++;
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String enterItemName = scanner.nextLine();
                    System.out.print("Enter item price: ");
                    double enterItemPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Category: ");
                    String enterItemCategory = scanner.nextLine();
                    System.out.println();
                    MenuItem menuItem = new MenuItem(enterItemName, enterItemPrice, enterItemCategory);
                    menuItemMap.put(enterItemName, menuItem);
                    System.out.println("Menu Item Added!");
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter customer name: ");
                    String enterCustomerName = scanner.nextLine();
                    System.out.print("Takeaway? (true/false): ");
                    boolean enterTakeaway = scanner.nextBoolean();
                    scanner.nextLine();
                    System.out.println("Select Menu Items (type 'done' to finish): ");
                    String enterMenuItem = scanner.nextLine();

                    Order order = new Order();
                    order.setOrderID(id);
                    order.setCustomerName(enterCustomerName);
                    order.setTakeaway(enterTakeaway);
                    while(!enterMenuItem.equals("done")){
                        System.out.print("- ");
                        enterMenuItem = scanner.nextLine();
                        for(Map.Entry<String, MenuItem> menuItemEntry : menuItemMap.entrySet()){
                            if(enterMenuItem.equals(menuItemEntry.getKey())){
                                menuItems.add(menuItemEntry.getValue());
                                order.setItems(menuItems);
                                break;
                            }
                        }
                    }

                    LocalDateTime localDateTime = LocalDateTime.now();
                    order.setOrderTime(localDateTime);
                    orders.add(order);

                    System.out.println("Order placed. Order ID: " + id);
                    id++;
                    break;
                case 4:
                    double totalPrice = 0;
                    Order processingOrder = orders.poll();
                    assert processingOrder != null;
                    System.out.println("Processing Order ID: " + processingOrder.getOrderID());
                    System.out.println("Customer: " + processingOrder.getCustomerName());
                    System.out.print("Items: ");
                    for(MenuItem menuItem1 : processingOrder.getItems()){
                        System.out.print(menuItem1.getName() + ", ");
                        totalPrice += menuItem1.getPrice();
                    }
                    processingOrder.setTotalPrice(totalPrice);
                    System.out.println("Total: $" + totalPrice);
                    System.out.println("Order Completed");
                    completedOrders.add(processingOrder);
                    break;
                case 5:
                    System.out.println("Completed Orders:");
                    for(Order completedOrder : completedOrders) {
                        System.out.println("Order ID: " + completedOrder.getOrderID() + " | "
                                + "Customer: " + completedOrder.getCustomerName() + " | " + "Total: "
                                + completedOrder.getTotalPrice());
                    }
                    break;
                case 6:
                    int totalOrders = 0;
                    double totalSales = 0;
                    System.out.println("---Daily Report---");
                    for(Order order1 : completedOrders){
                        totalOrders++;
                        totalSales += order1.getTotalPrice();
                    }
                    System.out.println("Total Orders: " + totalOrders);
                    System.out.println("Total Sales: $" + totalSales);
                    break;
                case 7:
                    isFinished = true;
                    break;
                default:
                    System.out.println("Incorrect Input");
            }
        }

    }
}
