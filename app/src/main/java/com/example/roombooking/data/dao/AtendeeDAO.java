package com.example.roombooking.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import com.example.roombooking.data.db.DbOpenHelper;
import com.example.roombooking.data.db.TableMaster;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.adduser.Atendee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinod  on 19-11-2017.
 *
 */

public class AtendeeDAO {


    private static final String TAG = "AtendeeDAO";

    DbOpenHelper mDbmanager;

    public AtendeeDAO(DbOpenHelper dbOpenHelper) {
        this.mDbmanager = dbOpenHelper;
    }

    public List<String> getData() {

        SQLiteDatabase sqLiteDatabase = null;

        List<String> lstAtendee = new ArrayList<>();

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblAtendee.TABLE_NAME;
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    lstAtendee.add(cursor.getString(cursor.getColumnIndex(TableMaster.tblAtendee.ATENDEE_NAME)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return lstAtendee;
    }


    public boolean insertAtendeeData(List<Atendee> atendeeList, String name) {
        boolean rowInserted = false;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = mDbmanager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        for (Atendee atendee : atendeeList) {
            contentValues.put(TableMaster.tblAtendee.ATENDEE_NAME, atendee.getName());
            contentValues.put(TableMaster.tblAtendee.EMAIL, atendee.getEmail());
            contentValues.put(TableMaster.tblAtendee.PHONE, atendee.getNumber());
            contentValues.put(TableMaster.tblAtendee.ROOM_NAME, name);
            if (sqLiteDatabase.insert(TableMaster.tblAtendee.TABLE_NAME, null, contentValues) > 0) {
                Log.d(TAG, "tblAtendee data inserted");
                rowInserted = true;
            }
        }
        //sqLiteDatabase.close();
        return rowInserted;
    }
    public List<Atendee> getAtendeeData(String roomName) {
        if(TextUtils.isEmpty(roomName))
            return null;
        SQLiteDatabase sqLiteDatabase = null;
        Atendee atendee=null;
        List<Atendee> lstAtendee=new ArrayList<>();

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblAtendee.TABLE_NAME+" WHERE "+TableMaster.tblAtendee.ROOM_NAME +"='"+roomName+"'";
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    atendee=new Atendee();
                    atendee.setName(cursor.getString(cursor.getColumnIndex(TableMaster.tblAtendee.ATENDEE_NAME)));
                    atendee.setEmail(cursor.getString(cursor.getColumnIndex(TableMaster.tblAtendee.EMAIL)));
                    atendee.setNumber(cursor.getString(cursor.getColumnIndex(TableMaster.tblAtendee.PHONE)));
                    lstAtendee.add(atendee);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return lstAtendee;
    }


}
