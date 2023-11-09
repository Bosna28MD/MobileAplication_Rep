package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME="User_Table";
    public static final String Column_id="id";
    public static final String Column_name="name";
    public static final String Column_email="email";
    public static final String Column_pwd="password";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "User_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable="CREATE TABLE "+TABLE_NAME+" ("+Column_id+" INTEGER  PRIMARY KEY AUTOINCREMENT,"+Column_name+" TEXT,"+Column_email+" TEXT,"+Column_pwd+" TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean add_Row(UserClass userModel){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();

        cv.put(Column_name,userModel.getName());
        cv.put(Column_email,userModel.getEmail());
        cv.put(Column_pwd,userModel.getPwd());


        long insert = db.insert(TABLE_NAME, null, cv);
        db.close();

        if(insert==-1){
            return false;
        }

        return true;
    }

    public ArrayList<UserClass> Select_All(){
        ArrayList<UserClass> arr_users=new ArrayList<>();

        String queryString="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){

            do{
                arr_users.add(new UserClass(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());

        }else{

        }

        cursor.close();
        db.close();

        return arr_users;

    }

    public boolean delete_Row(UserClass userModel){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery("DELETE FROM "+TABLE_NAME+" WHERE "+Column_id+"="+userModel.getId(),null);
        if(cursor.moveToFirst()){
            return true;
        }


        return false;
    }


}
