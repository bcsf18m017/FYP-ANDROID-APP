package com.example.fyp.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.DB.CartDB;
import com.example.fyp.DialogView;
import com.example.fyp.Entities.Cart;
import com.example.fyp.Entities.Product;
import com.example.fyp.ProductDetails;
import com.example.fyp.R;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyCartHolder>  {

    List<Cart>cartList;
    Context myContext;


    public CartAdapter(Context context, List<Cart> cartList) {
        this.cartList = cartList;
        this.myContext=context;
    }

    @NonNull
    @Override
    public CartAdapter.MyCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(myContext).inflate(R.layout.cart_item_layout, parent, false);
        return new CartAdapter.MyCartHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyCartHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.data=cartList.get(position);
        Product p=Product.getProductByID(holder.data.getProduct_id());
        if(p!=null)
        {
            holder.name.setText(p.getTitle());
            holder.quantity.setText(holder.data.getQuantity()+"x");
            holder.price.setText(Double.toString(p.getPrice()));
            holder.total.setText("Total:\t"+Double.toString(holder.data.getQuantity()*p.getPrice()));
            holder.image.setImageResource(p.getImage_id());
            ArrayList<String> options=new ArrayList<>();
            options.add("") ;
            options.add("Full Payment") ;
            options.add("Installment") ;

            SpinnerItemAdapter adapter=new SpinnerItemAdapter(myContext.getApplicationContext(),options);
            holder.spinner.setAdapter(adapter);

            holder.spinner.setSelection(0,false);
            holder.spinner.setSelection(getSavedMethodIndex(p.getProduct_ID()));

            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    CartDB cartDb =new CartDB(view.getContext());
                    cartDb.setPaymentMethod(p.getProduct_ID(),options.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(myContext);
                    builder1.setMessage("Do you really want to remove this item from cart?.");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    CartDB cartDb =new CartDB(view.getContext());
                                    cartDb.deleteRecordById(p.getProduct_ID());
                                    cartList.remove(position);
                                    Cart.deleteFromList(p.getProduct_ID());
                                    notifyDataSetChanged();
                                    DialogView dialogView=new DialogView();
                                    dialogView.showDialog(view.getContext(),"Item removed from cart",R.drawable.remove);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }

            });
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(view.getContext(), ProductDetails.class);
                    intent.putExtra("Details", p);
                    intent.putExtra("Caller","Cart");
                    myContext.startActivity(intent);
                    ((Activity)myContext).finish();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    private int getSavedMethodIndex(String pid)
    {
        CartDB cartDb =new CartDB(myContext.getApplicationContext());
        Cart c= cartDb.getItemByID(pid);
        return c.getPayment_method().equals("Installment")?2:1;
    }

    public class MyCartHolder extends RecyclerView.ViewHolder {
        TextView name,price,quantity,total;
        ImageView image,delete;
        Button edit;
        Cart data;
        Spinner spinner;
        public MyCartHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.productNameCart);
            quantity=itemView.findViewById(R.id.productQuantityCart);
            price=itemView.findViewById(R.id.productPriceCart);
            total=itemView.findViewById(R.id.productTotalCart);

            image=itemView.findViewById(R.id.productImageCart);

            delete=itemView.findViewById(R.id.delButtonCart);
            edit=itemView.findViewById(R.id.editButtonCart);

            spinner=itemView.findViewById(R.id.paymentMethodSpinner);

        }
    }
}

