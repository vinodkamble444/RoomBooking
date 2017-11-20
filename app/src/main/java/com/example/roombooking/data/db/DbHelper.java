

package com.example.roombooking.data.db;

import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.adduser.Atendee;

import java.util.List;

import io.reactivex.Observable;


/**
 *  Creaated by Vinod on 08/12/16.
 */

public interface DbHelper {

    Observable<List<Rooms>> getAllRooms();

    boolean saveRooms(List<Rooms> rooms);

    boolean saveAtendees(List<Atendee> atendeeList,String roomName);

    boolean isRoomsEmpty();

    Observable<Rooms> getRoomDetails(String roomName);
}
