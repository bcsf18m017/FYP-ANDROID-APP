package com.example.fyp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.fyp.Entities.Cart;

import java.util.ArrayList;

public class CartDB extends SQLiteOpenHelper {

    public static final String PRODUCT_ID="Product_ID";
    public static final String QUANTITY="Quantity";
    public static final String TABLE="Cart";
    public static final String CART_ID="Id";
    public static final String PAYMENT_METHOD="payment";



    public CartDB(@Nullable Context context) {
        super(context, "cart.db", null, 4);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSTatement = "CREATE TABLE " + TABLE+ "(" + CART_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + PRODUCT_ID + " Text, " + QUANTITY + " Int,"+PAYMENT_METHOD+" Text) ";
        sqLiteDatabase.execSQL(createTableSTatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addToCart(Cart c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(PRODUCT_ID,c.getProduct_id());
        cv.put(QUANTITY,c.getQuantity());
        cv.put(PAYMENT_METHOD,c.getPayment_method());
        db.insert(TABLE,null,cv);
        db.close();
    }
    public void setPaymentMethod(String p_id,String method)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateRow = "update "+TABLE+" set "+PAYMENT_METHOD+"='"+method+"' where "+PRODUCT_ID+"='"+p_id+"'";
        Log.i("query", updateRow);
        db.execSQL(updateRow);
    }


    public ArrayList<Cart> getAllItems()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE, null);
        ArrayList<Cart>list=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                list.add(new Cart(cursor.getString(1),cursor.getInt(2),cursor.getString(3)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;

    }
    public void deleteRecordById(String p_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteRow = "Delete from "+TABLE+" where "+PRODUCT_ID+"='"+p_id+"'";
        Log.i("query", deleteRow);
        db.execSQL(deleteRow);
    }

    public boolean isRecord(String p_id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String Query = "Select * from " + TABLE + " where " + PRODUCT_ID + " = " + p_id;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void updateRecordById(String p_id,int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateRow = "update "+TABLE+" set "+QUANTITY+"="+quantity+" where "+PRODUCT_ID+"='"+p_id+"'";
        Log.i("query", updateRow);
        db.execSQL(updateRow);
    }

    public Cart getItemByID(String p_id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("select * from " + TABLE + " where " + PRODUCT_ID + "='" + p_id + "'" , null);
        if(cursor.moveToFirst())
        {
            return  new Cart(cursor.getString(1), cursor.getInt(2), cursor.getString(3) );
        }
        cursor.close();
        return null;
    }

}
