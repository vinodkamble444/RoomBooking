package com.example.roombooking.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.example.roombooking.data.db.DbOpenHelper;
import com.example.roombooking.data.db.TableMaster;
import com.example.roombooking.data.network.model.Rooms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinod  on 19-11-2017.
 *
 */

public class AvailDAO {


    private static final String TAG = "tblAvail";

    DbOpenHelper mDbmanager;

    AvailDAO(DbOpenHelper dbOpenHelper) {
        this.mDbmanager = dbOpenHelper;
    }

    public List<String> getData() {

        SQLiteDatabase sqLiteDatabase = null;

        List<String> lstAvail = new ArrayList<>();

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblAvail.TABLE_NAME;
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    lstAvail.add(cursor.getString(cursor.getColumnIndex(TableMaster.tblAvail.AVAIL)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return lstAvail;
    }


    public boolean insertAvailData(List<String> availList, String name) {
        boolean rowInserted = false;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = mDbmanager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        for (String avail : availList) {
            contentValues.put(TableMaster.tblAvail.AVAIL, avail);
            contentValues.put(TableMaster.tblAvail.NAME, name);
            if (sqLiteDatabase.insert(TableMaster.tblAvail.TABLE_NAME, null, contentValues) > 0) {
                Log.d(TAG, "Avail data inserted");
                rowInserted = true;
            }
        }
        //sqLiteDatabase.close();
        return rowInserted;
    }
    public List<String> getAvailData(String roomName) {
        if(TextUtils.isEmpty(roomName))
            return null;
        SQLiteDatabase sqLiteDatabase = null;

        List<String> availList=new ArrayList<>();
        Rooms rooms = null;

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblAvail.TABLE_NAME+" WHERE "+TableMaster.tblAvail.NAME +"='"+roomName+"'";
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    availList.add(cursor.getString(cursor.getColumnIndex(TableMaster.tblAvail.AVAIL)));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return availList;
    }


}
