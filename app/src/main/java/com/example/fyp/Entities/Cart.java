package com.example.fyp.Entities;

import java.util.ArrayList;

public class Cart {
    String product_id,payment_method;
    int quantity;

    public static ArrayList<Cart>cartList=new ArrayList<>();

    public Cart(String product_id, int quantity,String payment_method) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.payment_method=payment_method;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static void populateList(ArrayList<Cart>list)
    {
        cartList=new ArrayList<>(list);
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public static void deleteFromList(String id)
    {
        int i=0;
        for(Cart c:cartList) {
           if(c.product_id.equals(id))
           {
               cartList.remove(i);
                return;
           }
            i++;
        }
    }
}
