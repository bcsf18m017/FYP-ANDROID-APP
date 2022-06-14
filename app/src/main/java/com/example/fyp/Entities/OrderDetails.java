package com.example.fyp.Entities;

import java.io.Serializable;
import java.util.Date;

public class OrderDetails implements Serializable {
    private int orderID,quantity,totalAmount,amountDue,minimumInstallment;
    private String productID;
    private Date dueDate;
    private Product product;

    public OrderDetails(int orderID, int quantity, int totalAmount, int amountDue, int minimumInstallment, String productID, Date dueDate) {
        this.orderID = orderID;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.amountDue = amountDue;
        this.minimumInstallment = minimumInstallment;
        this.productID = productID;
        this.dueDate = dueDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(int amountDue) {
        this.amountDue = amountDue;
    }

    public int getMinimumInstallment() {
        return minimumInstallment;
    }

    public void setMinimumInstallment(int minimumInstallment) {
        this.minimumInstallment = minimumInstallment;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
