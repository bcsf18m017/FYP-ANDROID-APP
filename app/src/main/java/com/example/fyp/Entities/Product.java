package com.example.fyp.Entities;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Product implements Serializable {
    String product_ID, title, description, createdBy, category;
    double price, percentage;
    String image;
    Date createdOn;
    boolean daily,monthly;
    int minimumInstallments;
    

    public static List<Product> productList;

    public Product(String product_ID, String title, String description, String category, String image, String createdBy, double price, double percentage,int minimumInstallments,boolean daily,boolean monthly, Date createdOn) {
        this.product_ID = product_ID;
        this.title = title;
        this.description = description;
        this.image = image;
        this.createdBy = createdBy;
        this.price = price;
        this.percentage = percentage;
        this.minimumInstallments=minimumInstallments;
        this.createdOn = createdOn;
        this.category = category;
        this.daily=daily;
        this.monthly=monthly;
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

    public String getImage_id() {
        return image;
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

    public void setImage_id(String image_id) {
        this.image = image_id;
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

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMinimumInstallments() {
        return minimumInstallments;
    }

    public void setMinimumInstallments(int minimumInstallments) {
        this.minimumInstallments = minimumInstallments;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static void setProductList(List<Product> productList) {
        Product.productList = productList;
    }

    public boolean isDaily() {
        return daily;
    }

    public void setDaily(boolean daily) {
        this.daily = daily;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
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
