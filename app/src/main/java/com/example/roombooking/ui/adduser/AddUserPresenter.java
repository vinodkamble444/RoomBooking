package com.example.roombooking.ui.adduser;

import android.text.TextUtils;
import android.util.Log;

import com.example.roombooking.R;
import com.example.roombooking.data.DataManager;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.BasePresenter;
import com.example.roombooking.utils.CommonUtils;
import com.example.roombooking.utils.rx.SchedulerProvider;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Creaated by Vinod on   19/11/17.
 */

public class AddUserPresenter<V extends AddUserIView> extends BasePresenter<V>
        implements AddUserIPresenter<V> {

    private static final String TAG = "AddUserPresenter";

    public String getRoomName() {
        return roomName;
    }

    private String roomName;

    private List<Atendee> listAtendee = new ArrayList<>();

    @Inject
    public AddUserPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    private void handleResponse(JSONObject response) {
        //getMvpView().refreshUserData(rooms);
        Log.d(TAG, "handleResponse: Sendpass" + response.toString());
        getMvpView().hideLoading();
        getMvpView().showSuccessDialog();

    }

    private void handleError(Throwable error) {
        Log.d(TAG, "handleError: Sendpass" + error.getLocalizedMessage());
        getMvpView().onError(error.getLocalizedMessage());
        getMvpView().hideLoading();

    }

    @Override
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public void onAddUserClick(String name, String email, String phone) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            getMvpView().onError(R.string.empty_field);
            return;
        }
        if (!CommonUtils.isValidMail(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (!CommonUtils.isValidMobile(phone)) {
            getMvpView().onError(R.string.invalid_phone);
            return;
        }
        listAtendee.add(new Atendee(name, email, phone));
        getMvpView().refreshUserData(listAtendee);
        getMvpView().resetFields();

    }

    @Override
    public void onBookClick(List<Atendee> listAtendee) {
        getMvpView().showLoading();
        getDataManager().saveAtendees(listAtendee, getDataManager().getRoomName());
        getMvpView().hideLoading();
        getMvpView().resetFields();

        Booking booking = new Booking();
        booking.setDate(getDataManager().getDate());
        booking.setTime_start(CommonUtils.timeConversion(getDataManager().getTimeslot()[0]));
        booking.setTime_end(CommonUtils.timeConversion(getDataManager().getTimeslot()[1]));
        booking.setRoom(getDataManager().getRoomName());
        booking.setTitle(getDataManager().getRoomName());
        booking.setDescription(getDataManager().getRoomName());
        BookingPass bookingPass = new BookingPass();
        bookingPass.setPasses(listAtendee);
        bookingPass.setBooking(booking);
    /*    Gson gson=new Gson();
        JSONObject jsonObject=null;
        try {
             jsonObject=new JSONObject(gson.toJson(bookingPass));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "onBookClick: "+e);
        }
    */   // gson.toJson(bookingPass);
        //  Log.d(TAG, "onBookClick: Json:"+gson.toJson(bookingPass));
        if (!getMvpView().isNetworkConnected()) {
            getMvpView().onError("Please check your internet Connection!");
            return;
        } else {
            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .sendPass(bookingPass)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(this::handleResponse, this::handleError));

        }

    }
}
