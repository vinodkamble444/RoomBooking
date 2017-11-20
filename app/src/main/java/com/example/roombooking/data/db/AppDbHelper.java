

package com.example.roombooking.data.db;

import com.example.roombooking.data.dao.AtendeeDAO;
import com.example.roombooking.data.dao.RoomsDAO;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.adduser.Atendee;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 *  Creaated by Vinod on 08/12/16.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private RoomsDAO mRoomsDao;
    private AtendeeDAO mAtendeeDAO;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        this.mRoomsDao=new RoomsDAO(dbOpenHelper);
        this.mAtendeeDAO=new AtendeeDAO(dbOpenHelper);
    }

    @Override
    public Observable<List<Rooms>> getAllRooms() {
        return Observable.fromCallable(new Callable<List<Rooms>>() {
            @Override
            public List<Rooms> call() throws Exception {
                return mRoomsDao.getData();
            }
        });
    }

    @Override
    public boolean saveRooms(List<Rooms> rooms) {
        return mRoomsDao.insertRoomsData(rooms);
    }

    @Override
    public boolean saveAtendees(List<Atendee> atendeeList,String roomName) {
        return mAtendeeDAO.insertAtendeeData(atendeeList,roomName);
    }

    @Override
    public boolean isRoomsEmpty() {
        return mRoomsDao.getData().isEmpty();
    }

    @Override
    public Observable<Rooms> getRoomDetails(String roomName) {
         return Observable.fromCallable(new Callable<Rooms>() {
            @Override
            public Rooms call() throws Exception {
                return mRoomsDao.getRoomData(roomName);
            }
        });
    }


}
