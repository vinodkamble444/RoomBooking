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

public class RoomsDAO {


    private static final String TAG = "RoomsDAO";

    DbOpenHelper mDbmanager;

    public RoomsDAO(DbOpenHelper dbOpenHelper) {
        this.mDbmanager = dbOpenHelper;
    }

    public List<Rooms> getData() {

        SQLiteDatabase sqLiteDatabase = null;

        Rooms rooms;
        List<Rooms> lstRooms = new ArrayList<>();

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblRooms.TABLE_NAME;
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    rooms = new Rooms();
                    rooms.setName(cursor.getString(cursor.getColumnIndex(TableMaster.tblRooms.NAME)));
                    rooms.setLocation(cursor.getString(cursor.getColumnIndex(TableMaster.tblRooms.LOCATION)));
                    rooms.setCapacity(cursor.getInt(cursor.getColumnIndex(TableMaster.tblRooms.CAPACITY)));
                    rooms.setSize(cursor.getString(cursor.getColumnIndex(TableMaster.tblRooms.SIZE)));

                    lstRooms.add(rooms);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return lstRooms;
    }


    public boolean insertRoomsData(List<Rooms> roomsList) {
        boolean rowInserted = false;
        SQLiteDatabase sqLiteDatabase = null;
        sqLiteDatabase = mDbmanager.getWritableDatabase();
        //sqLiteDatabase.delete(TableMaster.tblRooms.TABLE_NAME,null,null);
        AvailDAO availDAO = new AvailDAO(mDbmanager);
        EquipmentDAO equipmentDAO = new EquipmentDAO(mDbmanager);
        ImagesDAO imagesDAO = new ImagesDAO(mDbmanager);

        ContentValues contentValues = new ContentValues();
        for (Rooms mRooms : roomsList) {
            contentValues.put(TableMaster.tblRooms.NAME, mRooms.getName());
            contentValues.put(TableMaster.tblRooms.LOCATION, mRooms.getLocation());
            contentValues.put(TableMaster.tblRooms.SIZE, mRooms.getSize());
            contentValues.put(TableMaster.tblRooms.CAPACITY, mRooms.getCapacity());
            if (mRooms.getEquipment() != null && (mRooms.getEquipment().size() > 0)) {
                equipmentDAO.insertEquipmentData(mRooms.getEquipment(), mRooms.getName());
            }
            if (mRooms.getAvail() != null && (mRooms.getAvail().size() > 0)) {
                availDAO.insertAvailData(mRooms.getAvail(), mRooms.getName());
            }
            if (mRooms.getImages() != null && (mRooms.getImages().size() > 0)) {
                imagesDAO.insertImagesData(mRooms.getImages(), mRooms.getName());
            }

            if (sqLiteDatabase.insert(TableMaster.tblRooms.TABLE_NAME, null, contentValues) > 0) {
                Log.d(TAG, "Rooms inserted");
                rowInserted = true;
            }
        }
        //sqLiteDatabase.close();
        return rowInserted;
    }

    public Rooms getRoomData(String roomName) {
        if (TextUtils.isEmpty(roomName))
            return null;
        SQLiteDatabase sqLiteDatabase = null;
        AvailDAO availDAO = new AvailDAO(mDbmanager);
        EquipmentDAO equipmentDAO = new EquipmentDAO(mDbmanager);
        ImagesDAO imagesDAO = new ImagesDAO(mDbmanager);


        Rooms rooms = null;

        Cursor cursor = null;
        try {
            sqLiteDatabase = mDbmanager.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TableMaster.tblRooms.TABLE_NAME + " WHERE " + TableMaster.tblRooms.NAME + "='" + roomName + "'";
            cursor = sqLiteDatabase.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    rooms = new Rooms();
                    rooms.setName(cursor.getString(cursor.getColumnIndex(TableMaster.tblRooms.NAME)));
                    rooms.setLocation(cursor.getString(cursor.getColumnIndex(TableMaster.tblRooms.LOCATION)));
                    rooms.setCapacity(cursor.getInt(cursor.getColumnIndex(TableMaster.tblRooms.CAPACITY)));
                    rooms.setSize(cursor.getString(cursor.getColumnIndex(TableMaster.tblRooms.SIZE)));
                    rooms.setImages(imagesDAO.getImagesData(roomName));
                    rooms.setEquipment(equipmentDAO.getEquipmentData(roomName));
                    rooms.setAvail(availDAO.getAvailData(roomName));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            //sqLiteDatabase.close();
        }
        return rooms;
    }


}
