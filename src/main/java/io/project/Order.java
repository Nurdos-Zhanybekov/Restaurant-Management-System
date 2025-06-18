package io.project;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderID;
    private List<MenuItem> items;
    private String customerName;
    private boolean isTakeaway;
    private LocalDateTime orderTime;
    private double totalPrice;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public boolean isTakeaway() {
        return isTakeaway;
    }

    public void setTakeaway(boolean takeaway) {
        isTakeaway = takeaway;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
