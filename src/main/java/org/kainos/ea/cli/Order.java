package org.kainos.ea.cli;

import java.util.Comparator;
import java.util.Date;

public class Order implements Comparable<Order> {
    private int orderId;
    private int customerId;
    private Date orderDate;
    private  Date dispatchDate;

    public Order(int orderId, int customerId, Date orderDate, Date dispatchDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.dispatchDate = dispatchDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDispatchDate() {return dispatchDate;}

    public void setOrderDate(Date orderDate) {this.orderDate = orderDate;}

    public void setDispatchDate(Date dispatchDate) {this.dispatchDate = dispatchDate;}

    @Override
    public int compareTo(Order otherOrder) {
        return otherOrder.getOrderDate().compareTo(this.getOrderDate());
    }

    @Override
    public String toString() {
        return "Order ID:" + orderId + ", Order Date: " + orderDate;
    }
}
