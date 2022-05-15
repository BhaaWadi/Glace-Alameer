package com.example.glacealameer.Model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private String docId;
    private List<OrderItem> orderItems;
    private String userId;
    private  double Total;

    public Order(String docId, List<OrderItem> orderItems, String userId) {
        this.docId = docId;
        this.orderItems = orderItems;
        this.userId = userId;
    }

    public Order() {
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
