

package com.example.roombooking.ui.booking;


import com.example.roombooking.di.PerActivity;
import com.example.roombooking.ui.base.MvpPresenter;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@PerActivity
public interface BookingIPresenter<V extends BookingIView> extends MvpPresenter<V> {

    void onViewInitialized();

    void setRoomName(String roomname);

    void setTimeSlot(String[] timeslot);


}
