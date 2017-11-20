

package com.example.roombooking.ui.roomdetails;

import android.util.Log;

import com.example.roombooking.data.DataManager;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.BasePresenter;
import com.example.roombooking.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Creaated by Vinod on   19/11/17.
 */

public class RoomDetailPresenter<V extends RoomDetailIView> extends BasePresenter<V>
        implements RoomIPresenter<V> {

    private static final String TAG = "RoomDetailPresenter";
    private String roomName;

    @Inject
    public RoomDetailPresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onViewInitialized() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getRoomDetails(getRoomName())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(this::handleResponse, this::handleError));
    }

    public String getRoomName() {
        return roomName;
    }


    private void handleResponse(Rooms room) {

        getMvpView().refreshRoomData(room);
        getMvpView().hideLoading();

    }

    private void handleError(Throwable error) {
        Log.d(TAG, "Error" + error.getLocalizedMessage());
        getMvpView().onError(error.getLocalizedMessage());
        getMvpView().hideLoading();
    }

    @Override
    public void onCheckAvailabilityClick(String roomname) {
        getMvpView().openBookingActivity();
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
        getDataManager().setRoomName(roomName);
        getDataManager().setDate("now");
    }
}
