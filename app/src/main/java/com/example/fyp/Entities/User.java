package com.example.fyp.Entities;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
    String id, name, address, phone, cnic, password, createdBy;
    Double salary;
    String image_id;
    Date createdOn;
    List<Order> orderList;
    List<TransactionHistories>transactionHistories;

    public static User user;

    public User(String id, String name, String address, String phone, String cnic, String image_id, Double salary, String password, Date createdOn, String createdBy) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cnic = cnic;
        this.image_id = image_id;
        this.salary = salary;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrderList() {
        return orderList;
    }


    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<TransactionHistories> getTransactionHistories() {
        return transactionHistories;
    }

    public void setTransactionHistories(List<TransactionHistories> transactionHistories) {
        this.transactionHistories = transactionHistories;
    }

    public void setOrders(JSONArray arr)
    {

    }
}
