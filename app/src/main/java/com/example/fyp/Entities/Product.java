package com.example.fyp.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Product implements Serializable {
    String product_ID, title, description, createdBy, category;
    double price, percentage;
    boolean daily, monthly;
    int image_id;
    Date createdOn;

    public static List<Product> productList;

    public Product(String product_ID, String title, String description, String category, int image_id, String createdBy, double price, double percentage, boolean daily, boolean monthly, Date createdOn) {
        this.product_ID = product_ID;
        this.title = title;
        this.description = description;
        this.image_id = image_id;
        this.createdBy = createdBy;
        this.price = price;
        this.percentage = percentage;
        this.daily = daily;
        this.monthly = monthly;
        this.createdOn = createdOn;
        this.category = category;
        productList = new ArrayList<>();
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage_id() {
        return image_id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getPercentage() {
        return percentage;
    }

    public boolean isDaily() {
        return daily;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setDaily(boolean daily) {
        this.daily = daily;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public static Product getProductByID(String id) {
        for (Product p : productList) {
            if (p.product_ID.equals(id)) {
                return p;
            }
        }
        return null;
    }


}
