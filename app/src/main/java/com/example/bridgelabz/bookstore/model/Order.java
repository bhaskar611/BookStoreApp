package com.example.bridgelabz.bookstore.model;

import java.util.Date;
import java.util.List;

public class Order {

    private long orderID;
   // private Double orderTotal;
    private List<CartModel> cart_items;
   // private Date orderDate;

    public Order(long orderID /*Double orderTotal*/,List<CartModel> cart_items/*Date orderDate*/) {
        this.orderID = orderID;
       // this.orderTotal = orderTotal;
        this.cart_items = cart_items;
       // this.orderDate = orderDate;
    }

    public Order(){

    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

//    public Double getOrderTotal() {
//        return orderTotal;
//    }
//
//    public void setOrderTotal(Double orderTotal) {
//        this.orderTotal = orderTotal;
//    }

    public List<CartModel> getCart_items() {
        return cart_items;
    }

    public void setCart_items(List<CartModel> cart_items) {
        this.cart_items = cart_items;
    }

//    public Date getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(Date orderDate) {
//        this.orderDate = orderDate;
//    }
}
