

package com.example.roombooking.data.prefs;

import com.example.roombooking.data.DataManager;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public interface PreferencesHelper {

    String getRoomName();

    void setRoomName(String roomName);

    String getDate();

    void setDate(String date);

    void setTimeslot(String[] timeslot);

    String[] getTimeslot();

    void setDataStored(boolean flag);

    boolean isDataStored();

}
