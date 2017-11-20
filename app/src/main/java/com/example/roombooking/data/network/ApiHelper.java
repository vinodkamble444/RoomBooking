

package com.example.roombooking.data.network;

import com.example.roombooking.data.network.model.Rooms;

import com.example.roombooking.ui.adduser.BookingPass;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public interface ApiHelper {

    @POST("getrooms")
    Observable<List<Rooms>> getRooms(@Body JsonObject request);

    @POST("sendpass")
    Observable<JSONObject> sendPass(@Body BookingPass bookingPass);

}
