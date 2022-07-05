package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fyp.DB.CartDB;
import com.example.fyp.Entities.Cart;
import com.example.fyp.Entities.Product;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class ProductDetails extends AppCompatActivity implements Serializable {

    ImageView productImage;
    TextView name, price, description, category,daily,monthly;
    Button addToCart;
    String caller;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_product_details);
        Product product = (Product) getIntent().getSerializableExtra("Details");
        caller = getIntent().getStringExtra("Caller").toString();
       

        name = findViewById(R.id.product_detail_name);
        productImage = findViewById(R.id.product_detail_image);
        description = findViewById(R.id.product_detail_description);
        price = findViewById(R.id.product_detail_price);
        addToCart = findViewById(R.id.add_to_cart);
        category = findViewById(R.id.product_detail_category);
        daily=findViewById(R.id.product_detail_daily);
        monthly=findViewById(R.id.product_detail_monthly);


        name.setText(product.getTitle());
        Picasso.get().load(product.getImage_id()).into(productImage);
        description.setText(product.getDescription());
        price.setText(Double.toString(product.getPrice()+((product.getPercentage()*product.getPrice())/100)));
        category.setText(product.getCategory());


        int price=(int)((product.getPrice()+((product.getPrice()*product.getPercentage())/100))/product.getMinimumInstallments());
        monthly.setText(Integer.toString(price));

        if(product.isDaily())
        {
            daily.setText(Integer.toString(price/30));
        }
        else
        {
            daily.setText("N/A");
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDB db = new CartDB(view.getContext());
                Cart cartItem = new Cart(product.getProduct_ID(), 1, "Direct Payment");
                if (db.isRecord(product.getProduct_ID())) {
                    db.updateRecordById(product.getProduct_ID(), 1);
                } else {
                    db.addToCart(cartItem);
                }
                Toast.makeText(ProductDetails.this, "Item Added To Cart", Toast.LENGTH_SHORT).show();
                Cart.populateList(db.getAllItems());
                Intent intent = new Intent(ProductDetails.this, MainPage.class);
                intent.putExtra("Caller", caller);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProductDetails.this, MainPage.class);
        intent.putExtra("Caller", caller);
        startActivity(intent);
        finish();
    }
}