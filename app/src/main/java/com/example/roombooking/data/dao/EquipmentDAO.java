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

public class EquipmentDAO {


    private static final String TAG = "tblEquipment";

    DbOpenHelper mDbmanager;

    EquipmentDAO(DbOpenHelper dbOpenHelper) {
        this.mDbmanager = dbOpenHelper;
    }

    public List<String> getData() {

        SQLiteDatabase sqLiteDatabase = null;

        List<String> lstEquipment = new ArrayList<>();

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblEquipment.TABLE_NAME;
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    lstEquipment.add(cursor.getString(cursor.getColumnIndex(TableMaster.tblEquipment.Equipment)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return lstEquipment;
    }


    public boolean insertEquipmentData(List<String> lstEquipment, String name) {
        boolean rowInserted = false;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = mDbmanager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        for (String equipment : lstEquipment) {
            contentValues.put(TableMaster.tblEquipment.Equipment, equipment);
            contentValues.put(TableMaster.tblEquipment.NAME, name);
            if (sqLiteDatabase.insert(TableMaster.tblEquipment.TABLE_NAME, null, contentValues) > 0) {
                Log.d(TAG, "Avail data inserted");
                rowInserted = true;
            }
        }
        //sqLiteDatabase.close();
        return rowInserted;
    }
    public List<String> getEquipmentData(String roomName) {
        if(TextUtils.isEmpty(roomName))
            return null;
        SQLiteDatabase sqLiteDatabase = null;

        List<String> equipmentList=new ArrayList<>();
        Rooms rooms = null;

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblEquipment.TABLE_NAME+" WHERE "+TableMaster.tblEquipment.NAME +"='"+roomName+"'";
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    equipmentList.add(cursor.getString(cursor.getColumnIndex(TableMaster.tblEquipment.Equipment)));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return equipmentList;
    }



}
