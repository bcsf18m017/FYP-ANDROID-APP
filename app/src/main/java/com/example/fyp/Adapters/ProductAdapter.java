package com.example.fyp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.Entities.Cart;
import com.example.fyp.DB.CartDB;
import com.example.fyp.Entities.Product;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyProductHolder>{


    List<Product> productList;
    Context myContext;
    private ItemClickListener myClickListener;

    public ProductAdapter(Context context, List<Product> productList, ItemClickListener itemClickListener) {
        this.productList = productList;
        this.myContext = context;
        this.myClickListener = itemClickListener;
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
        holder.data = productList.get(position);
        holder.name.setText(holder.data.getTitle());
        holder.price.setText(Double.toString((holder.data.getPrice()+(holder.data.getPercentage()*holder.data.getPrice())/100)) + " RS");
        Picasso.get().load(holder.data.getImage_id()).into(holder.image);


        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDB db = new CartDB(view.getContext());
                Cart cartItem = new Cart(productList.get(position).getProduct_ID(), 1, "Direct Payment");
                if (db.isRecord(productList.get(position).getProduct_ID())) {
                    int count = db.getItemByID(productList.get(position).getProduct_ID()).getQuantity();
                    db.updateRecordById(productList.get(position).getProduct_ID(), count + 1);
                } else {
                    db.addToCart(cartItem);
                }
                Toast.makeText(myContext, "Item Added To Cart", Toast.LENGTH_SHORT).show();
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

    public interface ItemClickListener {
        void onItemClick(Product product);
    }

    public class MyProductHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        ImageView image;
        Product data;
        Button addToCart;

        public MyProductHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.productPrice);
            image = itemView.findViewById(R.id.productImage);
            addToCart = itemView.findViewById(R.id.addToCartMainPage);

        }
    }
}
