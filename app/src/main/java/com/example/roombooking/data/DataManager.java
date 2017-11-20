

package com.example.roombooking.data;


import com.example.roombooking.data.db.DbHelper;
import com.example.roombooking.data.network.ApiHelper;
import com.example.roombooking.data.prefs.PreferencesHelper;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void setUserName();

}
