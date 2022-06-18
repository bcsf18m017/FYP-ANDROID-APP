package com.example.fyp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.DB.CartDB;
import com.example.fyp.Entities.Cart;
import com.example.fyp.Entities.Product;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyCartHolder> {

    List<Cart> cartList;
    Context myContext;


    public CartAdapter(Context context, List<Cart> cartList) {
        this.cartList = cartList;
        this.myContext = context;
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
        holder.data = cartList.get(position);
        Product p = Product.getProductByID(holder.data.getProduct_id());
        if (p != null) {
            holder.name.setText(p.getTitle());
            holder.total.setText(Double.toString(holder.data.getQuantity() * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))));
            Picasso.get().load(p.getImage_id()).into(holder.image);
            CartDB db = new CartDB(myContext);
            holder.quantity.setText(Integer.toString(db.getItemByID(p.getProduct_ID()).getQuantity()));
            double dailyAmount=(holder.data.getQuantity() * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))/p.getMinimumInstallments())/30;
            double monthlyAmount=(holder.data.getQuantity() * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))/p.getMinimumInstallments());
            holder.daily.setText(Integer.toString((int)(dailyAmount)));
            holder.monthly.setText(Integer.toString((int)(monthlyAmount)));
            RadioButton daily=(RadioButton)holder.itemView.findViewById(R.id.radioDaily);
            RadioButton monthly=(RadioButton)holder.itemView.findViewById(R.id.radioMonthly);

            if(p.isDaily())
            {
                daily.setChecked(true);
            }
            if(p.isMonthly())
            {
                monthly.setChecked(true);
            }

            if(db.getItemByID(p.getProduct_ID()).getPayment_method().equals("Daily"))
            {
                daily.setChecked(true);
            }
            else
            {
                monthly.setChecked(true);
            }

            if(!p.isDaily())
            {
                daily.setEnabled(false);
            }
            if(!p.isMonthly())
            {
               monthly.setEnabled(false);
            }
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
                                    CartDB cartDb = new CartDB(view.getContext());
                                    cartDb.deleteRecordById(p.getProduct_ID());
                                    cartList.remove(position);
                                    Cart.deleteFromList(p.getProduct_ID());
                                    notifyDataSetChanged();
                                    Toast toast = Toast.makeText(myContext, "Item Removed Successfully", Toast.LENGTH_SHORT);
                                    toast.show();
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

            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qty = Integer.parseInt(holder.quantity.getText().toString());
                    if (qty + 1 < 11) {
                        holder.quantity.setText(Integer.toString(qty + 1));
                        qty++;
                        holder.total.setText((Double.toString(qty * (p.getPrice()+((p.getPrice()*p.getPercentage())/100)))));
                        holder.daily.setText((Integer.toString((int)(qty * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))/p.getMinimumInstallments())/30)));
                        holder.monthly.setText((Integer.toString((int)(qty * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))/p.getMinimumInstallments()))));
                        db.updateRecordById(p.getProduct_ID(), qty);
                    } else {
                        Toast.makeText(view.getContext(), "Maximum Limit reached", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qty = Integer.parseInt(holder.quantity.getText().toString());
                    if (qty - 1 > 0) {
                        holder.quantity.setText(Integer.toString(qty - 1));
                        qty--;
                        holder.total.setText(Double.toString(qty * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))));
                        holder.daily.setText((Integer.toString((int)(qty * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))/p.getMinimumInstallments())/30)));
                        holder.monthly.setText((Integer.toString((int)(qty * (p.getPrice()+((p.getPrice()*p.getPercentage())/100))/p.getMinimumInstallments()))));
                        db.updateRecordById(p.getProduct_ID(), qty);
                    } else {
                        Toast.makeText(view.getContext(), "Minimum Limit Reached", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb=(RadioButton)holder.itemView.findViewById(checkedId);
                    db.setPaymentMethod(p.getProduct_ID(),rb.getText().toString());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class MyCartHolder extends RecyclerView.ViewHolder {
        TextView name, total, quantity,daily,monthly;
        ImageView image, delete;
        Cart data;
        Button plus, minus;
        RadioGroup radioGroup;

        public MyCartHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productNameCart);
            total = itemView.findViewById(R.id.productTotalCart);

            image = itemView.findViewById(R.id.productImageCart);

            delete = itemView.findViewById(R.id.delButtonCart);
            plus = itemView.findViewById(R.id.plusButton);
            plus.setBackgroundDrawable(null);

            minus = itemView.findViewById(R.id.minusButton);
            minus.setBackground(null);
            quantity = itemView.findViewById(R.id.cartQuantity);

            radioGroup=itemView.findViewById(R.id.radioGroup);

            daily=itemView.findViewById(R.id.dailyMinimumInstallment);
            monthly=itemView.findViewById(R.id.monthlyMinimumInstallment);



        }
    }
}

