package com.example.fyp.Entities;

public class OrderDetailsRequest {
    int orderId;
    String productId,dueDate,id;
    int productQuantity,totalAmount,amountDue,minimumInstallment;


    public OrderDetailsRequest(int orderId,String productId, String dueDate, int productQuantity, int totalAmount, int amountDue, int minimumInstallment) {
        this.productId = productId;
        this.dueDate = dueDate;
        this.productQuantity = productQuantity;
        this.totalAmount = totalAmount;
        this.amountDue = amountDue;
        this.minimumInstallment = minimumInstallment;
        this.orderId=orderId;
        id="3fa85f64-5717-4562-b3fc-2c963f66afa6";
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
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
}
