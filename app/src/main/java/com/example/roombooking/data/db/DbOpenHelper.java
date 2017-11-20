

package com.example.roombooking.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.roombooking.di.ApplicationContext;
import com.example.roombooking.di.DatabaseInfo;

import javax.inject.Inject;

/**
 *  Creaated by Vinod on 08/12/16.
 */

public class DbOpenHelper extends SQLiteOpenHelper{

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name, null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableMaster.tblRooms.CREATE_TABLE);
        db.execSQL(TableMaster.tblEquipment.CREATE_TABLE);
        db.execSQL(TableMaster.tblImages.CREATE_TABLE);
        db.execSQL(TableMaster.tblAvail.CREATE_TABLE);
        db.execSQL(TableMaster.tblAtendee.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        super.close();
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
