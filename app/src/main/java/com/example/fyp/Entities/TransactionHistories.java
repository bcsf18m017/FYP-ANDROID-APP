package com.example.fyp.Entities;

import java.util.Date;

public class TransactionHistories {
    private String id,orderDetailID;
    private int amountReceied;
    private Date createdOn;
    private OrderDetails orderDetails;

    public TransactionHistories(String id, String orderDetailID, int amountReceied, Date createdOn) {
        this.id = id;
        this.orderDetailID = orderDetailID;
        this.amountReceied = amountReceied;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getAmountReceied() {
        return amountReceied;
    }

    public void setAmountReceied(int amountReceied) {
        this.amountReceied = amountReceied;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
