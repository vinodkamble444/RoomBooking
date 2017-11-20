

package com.example.roombooking.data;


import android.content.Context;

import com.example.roombooking.data.db.DbHelper;
import com.example.roombooking.data.network.ApiHelper;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.data.prefs.PreferencesHelper;
import com.example.roombooking.di.ApplicationContext;

import com.example.roombooking.ui.adduser.Atendee;
import com.example.roombooking.ui.adduser.BookingPass;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
     ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper
                          ) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public Observable<List<Rooms>> getRooms(JsonObject object) {
        return mApiHelper.getRooms(object);
    }

    @Override
    public Observable<JSONObject> sendPass(BookingPass bookingPass) {
        return mApiHelper.sendPass(bookingPass);
    }

    @Override
    public String getRoomName() {
        return mPreferencesHelper.getRoomName();
    }

    @Override
    public void setRoomName(String roomName) {
        mPreferencesHelper.setRoomName(roomName);
    }

    @Override
    public String getDate() {
        return mPreferencesHelper.getDate();
    }

    @Override
    public void setDate(String date) {
        mPreferencesHelper.setDate(date);
    }

    @Override
    public void setTimeslot(String[] timeslot) {
        mPreferencesHelper.setTimeslot(timeslot);
    }

    @Override
    public String[] getTimeslot() {
        return mPreferencesHelper.getTimeslot();
    }

    @Override
    public void setDataStored(boolean flag) {
        mPreferencesHelper.setDataStored(flag);
    }

    @Override
    public boolean isDataStored() {
        return mPreferencesHelper.isDataStored();
    }

    @Override
    public Observable<List<Rooms>> getAllRooms() {
        return mDbHelper.getAllRooms();
    }

    @Override
    public boolean saveRooms(List<Rooms> rooms) {
        return mDbHelper.saveRooms(rooms);
    }

    @Override
    public boolean saveAtendees(List<Atendee> atendeeList, String roomName) {
        return mDbHelper.saveAtendees(atendeeList,roomName);
    }

    @Override
    public boolean isRoomsEmpty() {
        return mDbHelper.isRoomsEmpty();
    }

    @Override
    public Observable<Rooms> getRoomDetails(String roomName) {
        return mDbHelper.getRoomDetails(roomName);
    }

    @Override
    public void setUserName() {
    }
}
