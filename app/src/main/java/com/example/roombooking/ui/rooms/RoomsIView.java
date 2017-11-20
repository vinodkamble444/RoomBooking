

package com.example.roombooking.ui.rooms;

import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.IView;

import java.util.List;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public interface RoomsIView extends IView {

    void refreshRooms(List<Rooms> rooms);

    void ShowNoDataView();
}
