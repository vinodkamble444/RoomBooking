

package com.example.roombooking.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.roombooking.data.DataManager;
import com.example.roombooking.di.ApplicationContext;
import com.example.roombooking.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Creaated by Vinod on   19/11/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_CURRENT_ROOM_NAME = "PREF_KEY_CURRENT_ROOM_NAME";
    private static final String PREF_KEY_CURRENT_DB_STORE = "PREF_KEY_CURRENT_DB_STORE";
    private static final String PREF_KEY_CURRENT_DATE = "PREF_KEY_CURRENT_DATE";
    private static final String PREF_KEY_CURRENT_START_TIME = "PREF_KEY_CURRENT_START_TIME";
    private static final String PREF_KEY_CURRENT_END_TIME = "PREF_KEY_CURRENT_END_TIME";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public String getRoomName() {
        return mPrefs.getString(PREF_KEY_CURRENT_ROOM_NAME, null);
    }

    @Override
    public void setRoomName(String roomName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_ROOM_NAME, roomName).apply();
    }

    @Override
    public String getDate() {
        return mPrefs.getString(PREF_KEY_CURRENT_DATE, null);
    }

    @Override
    public void setDate(String date) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_DATE, date).apply();
    }

    @Override
    public void setTimeslot(String[] timeslot) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_START_TIME, timeslot[0]).apply();
        mPrefs.edit().putString(PREF_KEY_CURRENT_END_TIME, timeslot[1]).apply();
    }

    @Override
    public String[] getTimeslot() {
        return new String[]{mPrefs.getString(PREF_KEY_CURRENT_START_TIME, null), mPrefs.getString(PREF_KEY_CURRENT_END_TIME, null)};
    }

    @Override
    public void setDataStored(boolean flag) {
        mPrefs.edit().putBoolean(PREF_KEY_CURRENT_DB_STORE, true).apply();
    }

    @Override
    public boolean isDataStored() {
        return mPrefs.getBoolean(PREF_KEY_CURRENT_DB_STORE, false);
    }

}
