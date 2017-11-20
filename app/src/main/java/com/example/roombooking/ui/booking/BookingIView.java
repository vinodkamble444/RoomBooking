

package com.example.roombooking.ui.booking;

import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.IView;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public interface BookingIView extends IView {

    void refreshBookingData(Rooms rooms);

}
