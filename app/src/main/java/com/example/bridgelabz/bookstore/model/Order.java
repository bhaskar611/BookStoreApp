package com.example.bridgelabz.bookstore.model;

import java.util.Date;
import java.util.List;

public class Order {

    private long orderID;
    private float orderTotal;
    private List<CartModel> cart_items;
    private String orderDate;
    private long deliveryAddressId;

    public long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public Order(long orderID, float orderTotal, List<CartModel> cart_items, String orderDate, long deliveryAddressId) {
        this.orderID = orderID;
        this.orderTotal = orderTotal;
        this.cart_items = cart_items;
       this.orderDate = orderDate;
        this.deliveryAddressId = deliveryAddressId;
    }

    public Order(){

    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<CartModel> getCart_items() {
        return cart_items;
    }

    public void setCart_items(List<CartModel> cart_items) {
        this.cart_items = cart_items;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
