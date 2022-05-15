package com.example.glacealameer.Model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private Item item;
    private int count;
    private String docId;
    private  String orderType;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public OrderItem(Item item, int count, String docId, String orderType) {
        this.item = item;
        this.count = count;
        this.docId = docId;
        this.orderType = orderType;
    }

    public OrderItem() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }
}
