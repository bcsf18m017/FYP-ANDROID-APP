package com.example.fyp.Dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.fyp.Api.ApiInterface;
import com.example.fyp.Api.RetrofitClient;
import com.example.fyp.DB.CartDB;
import com.example.fyp.Entities.Cart;
import com.example.fyp.Entities.Order;
import com.example.fyp.Entities.OrderDetails;
import com.example.fyp.Entities.OrderDetailsRequest;
import com.example.fyp.Entities.OrderRequest;
import com.example.fyp.Entities.Product;
import com.example.fyp.Entities.User;
import com.example.fyp.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmOrderDialog {
    public OrderRequest order;
    ProgressDialog dialogBox;
    int dailyPrice,monthlyPrice,totalPrice;
    String[] dueDates;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    public void showDialog(Context activity,  int dailyAmount, int monthlyAmount,int totalAmount) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.confirm_order_layout);
        TextView daily = dialog.findViewById(R.id.dailyHeading);
        TextView monthly = dialog.findViewById(R.id.monthlyHeading);
        dialogBox=new ProgressDialog(activity);

        dailyPrice=dailyAmount;
        monthlyPrice=monthlyAmount;
        totalPrice=totalAmount;

        CartDB db=new CartDB(activity);
        List<Cart>list=db.getAllItems();
        dueDates=new String[list.size()];
        int max=0;
        for(int i=0;i<list.size();i++)
        {
            for(int j=0;j< Product.productList.size();j++)
            {
                if(list.get(i).getProduct_id().equals(Product.productList.get(j).getProduct_ID()))
                {
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(Calendar.MONTH, Product.productList.get(j).getMinimumInstallments());
                    Date date=calendar.getTime();
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    dueDates[i]=simpleDateFormat.format(date);
                    if(Product.productList.get(j).getMinimumInstallments()>max)
                    {
                        max=Product.productList.get(j).getMinimumInstallments();
                    }

                }
            }
        }
        order=new OrderRequest(User.user.getId(),totalPrice,totalPrice);
        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        order.setCreatedOn(simpleDateFormat.format(date));

        daily.setText(Double.toString(dailyAmount));
        monthly.setText(Double.toString(monthlyAmount));



        Button cancel = dialog.findViewById(R.id.order_cancel_button);
        Button confirm = dialog.findViewById(R.id.place_order_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBox.setTitle("Confirming Order");
                dialogBox.setMessage("Please wait while we are confirming your order");
                dialogBox.setCancelable(false);
                dialogBox.show();
                postOrder(activity);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void postOrderDetails(Context activity)
    {
        ApiInterface apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);

        CartDB db=new CartDB(activity);
        List<Cart>list=db.getAllItems();
        int[] installments=new int[list.size()];
        int[]prices=new int[list.size()];
        for(int i=0;i<list.size();i++)
        {
            for(int j=0;j< Product.productList.size();j++)
            {
                if(list.get(i).getProduct_id().equals(Product.productList.get(j).getProduct_ID()))
                {
                    installments[i]=Product.productList.get(j).getMinimumInstallments();
                    prices[i]=(int)Product.productList.get(j).getPrice();
                }
            }
        }

        int i=0;
        for(Cart c:list) {
            OrderDetailsRequest request=new OrderDetailsRequest(order.getOrderId(),c.getProduct_id(),dueDates[i], c.getQuantity(),prices[i]*c.getQuantity(),prices[i]*c.getQuantity(),installments[i]);
            i++;
            Call<OrderDetails> call=apiInterface.postOrderDetail(request);
            int finalI = i;
            call.enqueue(new Callback<OrderDetails>() {
                @Override
                public void onResponse(Call<OrderDetails> call, Response<OrderDetails> response) {
                    if(response.errorBody()!=null)
                    {
                        Toast.makeText(activity, "Temporary Error While Placing Order.Please Try Again Later", Toast.LENGTH_SHORT).show();
                        dialogBox.dismiss();
                    }
                    else
                    {
                        if(finalI ==list.size())
                        {
                            for(Cart c:list)
                            {
                                db.deleteRecordById(c.getProduct_id());
                            }
                            Toast.makeText(activity, "Your Order Has Been Placed Successfully", Toast.LENGTH_SHORT).show();
                            dialogBox.dismiss();
                        }
                    }
                }
                @Override
                public void onFailure(Call<OrderDetails> call, Throwable tt) {
                    Toast.makeText(activity, "Temporary Error While Placing Order.Please Try Again Later", Toast.LENGTH_SHORT).show();
                    dialogBox.dismiss();
                }
            });
        }



    }


    private void postOrder(Context activity)
    {

        ApiInterface apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);

        Call<Order> call=apiInterface.postOrder(order);

        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.errorBody()==null)
                {
                    order.setOrderId(response.body().getId());
                    postOrderDetails(activity);
                }
                else
                {
                    Toast.makeText(activity, "Temporary Error While Placing Order.Please Try Again Later", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable tt) {
                dialogBox.dismiss();
                Toast.makeText(activity, "Temporary Error While Placing Order.Please Try Again Later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
