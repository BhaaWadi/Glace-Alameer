package com.example.glacealameer.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.glacealameer.Model.Item;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseFavorite extends SQLiteAssetHelper {
    public static final String DB_NAME = "favorite.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Product";
    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";
    public static final String COL_IMAGE = "image";
    public static final String COL_PRICE = "price";
    public static final String COL_DETAILS = "details";
    public static final String COL_CATEGORY = "category";

    public DatabaseFavorite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public boolean insertItem(Item item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID,item.getDocId());
        cv.put(COL_NAME,item.getName());
        cv.put(COL_IMAGE,item.getImageUrl());
        cv.put(COL_PRICE,item.getPrice());
        cv.put(COL_CATEGORY,item.getCategoryName());

        long result = db.insert(TABLE_NAME,null,cv);
        return result>0;
    }


    public ArrayList<Item> getAllItem(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Item> products = new ArrayList<>();
        Cursor cursor= db.query(TABLE_NAME,null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {

                String id = cursor.getString(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String image = cursor.getString(cursor.getColumnIndex(COL_IMAGE));
               // String details = cursor.getString(cursor.getColumnIndex(COL_DETAILS));
                String categoryName = cursor.getString(cursor.getColumnIndex(COL_CATEGORY));
                double price = cursor.getDouble(cursor.getColumnIndex(COL_PRICE));


                Item item=new Item();
                item.setDocId(id);
                item.setName(name);
                item.setImageUrl(image);
                item.setPrice(price);
                item.setCategoryName(categoryName);
                products.add(item);

            }
            while (cursor.moveToNext());
        }
        return products;

    }

    public boolean deleteFurniture(String id){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(TABLE_NAME, COL_ID + " =? ",new String[]{String.valueOf(id)});
        return result>0;
    }

    public boolean searchItem(String id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor= db.query(TABLE_NAME,null,COL_ID + " =? ",new String[]{String.valueOf(id)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                return true;
            }
            while (cursor.moveToNext());
        }
        else return false;


    }



}

