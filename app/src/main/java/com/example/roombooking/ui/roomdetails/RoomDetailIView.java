

package com.example.roombooking.ui.roomdetails;

import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.IView;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public interface RoomDetailIView extends IView {

    void openBookingActivity();

    void refreshRoomData(Rooms rooms);

}
