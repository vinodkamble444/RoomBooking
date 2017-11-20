

package com.example.roombooking.ui.rooms;

import android.util.Log;

import com.example.roombooking.data.DataManager;
import com.example.roombooking.data.network.model.Request;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.BasePresenter;
import com.example.roombooking.utils.rx.SchedulerProvider;
import com.google.gson.JsonObject;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;


/**
 * Creaated by Vinod on   19/11/17.
 */

public class RoomsPresenter<V extends RoomsIView> extends BasePresenter<V>
        implements RoomsIPresenter<V> {

    private static final String TAG = "RoomsPresenter";

    @Inject
    public RoomsPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }



    @Override
    public void onViewInitialized() {

        if (getDataManager().isRoomsEmpty()) {
            if (!getMvpView().isNetworkConnected()) {
                getMvpView().onError("Please check your internet Connection!");
                return;
            } else {
                loadFromNetwork();
            }
        } else {
            getMvpView().showLoading();
            getCompositeDisposable().add(getDataManager()
                    .getAllRooms()
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(this::handleResponse, this::handleError));
        }
    }


    private void handleResponse(List<Rooms> rooms) {
        getMvpView().refreshRooms(rooms);
        if(!getDataManager().isDataStored()) {
            getDataManager().saveRooms(rooms);
            getDataManager().setDataStored(true);
        }
        getMvpView().hideLoading();

    }

    private void handleError(Throwable error) {
        Log.d(TAG,""+error.toString());
        getMvpView().onError(error.getLocalizedMessage());
        getMvpView().hideLoading();

        if (error instanceof HttpException) {
            ResponseBody responseBody = ((HttpException)error).response().errorBody();
            Log.d("data",""+getErrorMessage(responseBody));
        } else if (error instanceof SocketTimeoutException) {
            Log.d("data","tIMEOUT");
        } else if (error instanceof IOException) {
            Log.d("data","onNetworkError");
        } else {
            Log.d("data",""+error.getMessage());
        }


    }
    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @Override
    public void loadFromNetwork() {


        if (!getMvpView().isNetworkConnected()) {
            getMvpView().onError("Please check your internet Connection!");
            return;
        } else {
            getMvpView().showLoading();
            JsonObject payerReg = new JsonObject();
            payerReg.addProperty("date","now");
            getCompositeDisposable().add(getDataManager()
                    .getRooms(payerReg)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(this::handleResponse, this::handleError));

        }

    }

}
