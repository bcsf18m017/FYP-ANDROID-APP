package com.example.fyp.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private int id,totalBill,billDue;
    private String dueDate;
    private String personID;
    private User person;
    List<OrderDetails>orderDetails;

    public Order(int id, int totalBill, int billDue, String dueDate, String personID) {
        this.id = id;
        this.totalBill = totalBill;
        this.billDue = billDue;
        this.dueDate = dueDate;
        this.personID = personID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public int getBillDue() {
        return billDue;
    }

    public void setBillDue(int billDue) {
        this.billDue = billDue;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
