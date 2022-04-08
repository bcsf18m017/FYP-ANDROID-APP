package com.example.fyp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fyp.DialogView;
import com.example.fyp.Entities.Cart;
import com.example.fyp.DB.CartDB;
import com.example.fyp.Entities.Product;
import com.example.fyp.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyProductHolder> {


    List<Product> productList;
    Context myContext;
    private ItemClickListener myClickListener;

    public ProductAdapter(Context context, List<Product> productList, ItemClickListener itemClickListener) {
        this.productList = productList;
        this.myContext=context;
        this.myClickListener=itemClickListener;
    }


    @NonNull
    @Override
    public MyProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(myContext).inflate(R.layout.item_layout, parent, false);
        return new MyProductHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyProductHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.data=productList.get(position);
        holder.name.setText(holder.data.getTitle());
        holder.price.setText(Double.toString(holder.data.getPrice()));
        holder.image.setImageResource(holder.data.getImage_id());


        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDB db=new CartDB(view.getContext());
                Cart cartItem=new Cart(productList.get(position).getProduct_ID(),Integer.parseInt(holder.counter.getNumber()),"Direct Payment");
                if(db.isRecord(productList.get(position).getProduct_ID()))
                {
                    db.updateRecordById(productList.get(position).getProduct_ID(),Integer.parseInt(holder.counter.getNumber()));
                }
                else
                {
                    db.addToCart(cartItem);
                }

                holder.counter.setNumber("1");
                DialogView dialogView=new DialogView();
                dialogView.showDialog(view.getContext(),"Item added to cart",R.drawable.done);
            }
        });
        holder.itemView.setOnClickListener(view -> {
            myClickListener.onItemClick(productList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public interface ItemClickListener{
        void onItemClick(Product product);
    }
    public class MyProductHolder extends RecyclerView.ViewHolder
    {

        TextView name,price;
        ImageView image;
        Product data;
        Button addToCart;
        ElegantNumberButton counter;
        public MyProductHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.productName);
            price=itemView.findViewById(R.id.productPrice);
            image=itemView.findViewById(R.id.productImage);
            addToCart=itemView.findViewById(R.id.addToCartMainPage);
            counter=itemView.findViewById(R.id.counterMainPage);

        }
    }
}
