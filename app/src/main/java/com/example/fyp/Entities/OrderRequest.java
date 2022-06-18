package com.example.fyp.Entities;

import android.os.Build;
import android.text.format.DateUtils;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class OrderRequest {
    int orderId;
    String personId;
    int totalBill,billDue;
    String createdOn;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public OrderRequest(String personId, int totalBill, int billDue) {
        this.personId = personId;
        this.totalBill = totalBill;
        this.billDue = billDue;
        orderId=0;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
