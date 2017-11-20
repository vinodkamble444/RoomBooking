

package com.example.roombooking.ui.roomdetails;


import com.example.roombooking.di.PerActivity;
import com.example.roombooking.ui.base.MvpPresenter;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@PerActivity
public interface RoomIPresenter<V extends RoomDetailIView> extends MvpPresenter<V> {

    void onViewInitialized();

    void onCheckAvailabilityClick(String roomname);

    void setRoomName(String roomname);

}
