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

public class ImagesDAO {


    private static final String TAG = "ImagesDAO";

    DbOpenHelper mDbmanager;

    ImagesDAO(DbOpenHelper dbOpenHelper) {
        this.mDbmanager = dbOpenHelper;
    }

    public List<String> getData() {

        SQLiteDatabase sqLiteDatabase = null;

        List<String> lstImagesUrl = new ArrayList<>();

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblImages.TABLE_NAME;
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    lstImagesUrl.add(cursor.getString(cursor.getColumnIndex(TableMaster.tblImages.IMAGE_URL)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return lstImagesUrl;
    }


    public boolean insertImagesData(List<String> imagesList, String name) {
        boolean rowInserted = false;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = mDbmanager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        for (String image : imagesList) {
            contentValues.put(TableMaster.tblImages.IMAGE_URL, image);
            contentValues.put(TableMaster.tblImages.NAME, name);
            if (sqLiteDatabase.insert(TableMaster.tblImages.TABLE_NAME, null, contentValues) > 0) {
                Log.d(TAG, "Images inserted");
                rowInserted = true;
            }
        }
        //sqLiteDatabase.close();
        return rowInserted;
    }


    public List<String> getImagesData(String roomName) {
        if (TextUtils.isEmpty(roomName))
            return null;
        SQLiteDatabase sqLiteDatabase = null;
        AvailDAO availDAO = new AvailDAO(mDbmanager);
        EquipmentDAO equipmentDAO = new EquipmentDAO(mDbmanager);
        ImagesDAO imagesDAO = new ImagesDAO(mDbmanager);

        List<String> imgStringList = new ArrayList<>();
        Rooms rooms = null;

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblImages.TABLE_NAME + " WHERE " + TableMaster.tblImages.NAME + "='" + roomName + "'";
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    imgStringList.add(cursor.getString(cursor.getColumnIndex(TableMaster.tblImages.IMAGE_URL)));

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return imgStringList;
    }


}
