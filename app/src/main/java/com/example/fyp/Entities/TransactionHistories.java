package com.example.fyp.Entities;

import java.util.Date;

public class TransactionHistories {
    private String id,orderDetailID,personId;
    private int amountReceied;
    private String  createdOn;
    private OrderDetails orderDetails;

    public TransactionHistories(String id, String orderDetailID, int amountReceied,String personId, String createdOn) {
        this.id = id;
        this.orderDetailID = orderDetailID;
        this.amountReceied = amountReceied;
        this.createdOn = createdOn;
        this.personId=personId;
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

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
