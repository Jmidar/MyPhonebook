package com.example.jmidar.myphonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteDB extends SQLiteOpenHelper{

    private static  final int DB_VERSION=1;
    private static  final String DB_NAME="phonebook.db";
    private static  final String TABLE_NAME="contact";

    private static  final String COLUMN1="id";
    private static  final String COLUMN2="name";
    private static  final String COLUMN3="cell";

    //constructor
    public MySqliteDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //for create table
    @Override
    public void onCreate(SQLiteDatabase db) {


        String query;
        query = "CREATE TABLE "+TABLE_NAME + "(id integer primary key,name text,cell text)" ;
        db.execSQL(query);
    }


    //for
    @Override
    public void onUpgrade(SQLiteDatabase db, int newVersion, int oldVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addToTable(int id,String name,String cell){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1, id);
        values.put(COLUMN2, name);
        values.put(COLUMN3, cell);

        long check = db.insert(TABLE_NAME, null, values);

        if(check == -1){
            return  false;
        }
        else {
            return true;
        }
    }

    //for view data
    public Cursor viewDate(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor result;
        result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return result;

    }

    //for update data
    public boolean updateData(String id,String name,String cell){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN1, id);
        values.put(COLUMN2, name);
        values.put(COLUMN3, cell);

        long check = db.update(TABLE_NAME,values,"id = ?",new String[]{id});

        if(check == -1){
            return  false;
        }
        else {
            return true;
        }
    }

    //for delete data
    public int deleteData(String id){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,"id = ?",new String[]{id});
    }
}
