package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fyp.DB.CartDB;
import com.example.fyp.Dialogs.AddOrRemoveDialog;
import com.example.fyp.Entities.Cart;
import com.example.fyp.Entities.Product;
import com.example.fyp.Fragments.CartFragment;

public class ProductDetails extends AppCompatActivity {

    ImageView productImage;
    ElegantNumberButton counter;
    TextView name,price,description,category;
    Button addToCart;
    String caller;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Product product=(Product)getIntent().getSerializableExtra("Details");
        caller=getIntent().getStringExtra("Caller").toString();

        counter=findViewById(R.id.counter);
        name=findViewById(R.id.product_detail_name);
        productImage=findViewById(R.id.product_detail_image);
        description=findViewById(R.id.product_detail_description);
        price=findViewById(R.id.product_detail_price);
        addToCart=findViewById(R.id.add_to_cart);
        category=findViewById(R.id.product_detail_category);

        name.setText(product.getTitle());
        productImage.setImageResource(product.getImage_id());
        description.setText(product.getDescription());
        price.setText(Double.toString(product.getPrice()));
        category.setText(product.getCategory());


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDB db=new CartDB(view.getContext());
                Cart cartItem=new Cart(product.getProduct_ID(),Integer.parseInt(counter.getNumber()),"Direct Payment");
                if(db.isRecord(product.getProduct_ID()))
                {
                    db.updateRecordById(product.getProduct_ID(),Integer.parseInt(counter.getNumber()));
                }
                else
                {
                    db.addToCart(cartItem);
                }
                AddOrRemoveDialog addOrRemoveDialog =new AddOrRemoveDialog();
                addOrRemoveDialog.showDialog(view.getContext(),"Item added to cart",R.drawable.done);
                Cart.populateList(db.getAllItems());
                Intent intent=new Intent(ProductDetails.this,MainPage.class);
                intent.putExtra("Caller",caller);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ProductDetails.this,MainPage.class);
        intent.putExtra("Caller",caller);
        startActivity(intent);
        finish();
    }
}