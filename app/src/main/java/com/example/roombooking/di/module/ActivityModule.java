

package com.example.roombooking.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.example.roombooking.data.network.ApiEndPoint;
import com.example.roombooking.data.network.ApiHelper;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.di.ActivityContext;
import com.example.roombooking.di.PerActivity;
import com.example.roombooking.ui.adduser.AddUserIPresenter;
import com.example.roombooking.ui.adduser.AddUserIView;
import com.example.roombooking.ui.adduser.AddUserPresenter;
import com.example.roombooking.ui.adduser.Atendee;
import com.example.roombooking.ui.adduser.UserAdapter;
import com.example.roombooking.ui.booking.TimeSlot;
import com.example.roombooking.ui.booking.BookingAdapter;
import com.example.roombooking.ui.booking.BookingIPresenter;
import com.example.roombooking.ui.booking.BookingIView;
import com.example.roombooking.ui.booking.BookingPresenter;
import com.example.roombooking.ui.roomdetails.RoomDetailAdapter;
import com.example.roombooking.ui.roomdetails.RoomIPresenter;
import com.example.roombooking.ui.roomdetails.RoomDetailIView;
import com.example.roombooking.ui.roomdetails.RoomDetailPresenter;
import com.example.roombooking.ui.rooms.RoomAdapter;
import com.example.roombooking.ui.rooms.RoomsIPresenter;
import com.example.roombooking.ui.rooms.RoomsIView;
import com.example.roombooking.ui.rooms.RoomsPresenter;
import com.example.roombooking.utils.rx.AppSchedulerProvider;
import com.example.roombooking.utils.rx.SchedulerProvider;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    RoomIPresenter<RoomDetailIView> provideMainPresenter(
            RoomDetailPresenter<RoomDetailIView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    RoomsIPresenter<RoomsIView> provideRoomsPresenter(
            RoomsPresenter<RoomsIView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    AddUserIPresenter<AddUserIView> provideAddUserPresenter(
            AddUserPresenter<AddUserIView> presenter) {
        return presenter;
    }


    @Provides
    @PerActivity
    RoomDetailAdapter provideMainAdapter() {
        return new RoomDetailAdapter(new ArrayList<String>() , mActivity);
    }

    @Provides
    @PerActivity
    RoomAdapter provideRoomsAdapter() {
        return new RoomAdapter(new ArrayList<Rooms>() , mActivity);
    }

    @Provides
    @PerActivity
    BookingAdapter provideBookingAdapter() {
        return new BookingAdapter(new ArrayList<TimeSlot>() , mActivity);
    }

    @Provides
    @PerActivity
    UserAdapter provideUserAdapter() {
        return new UserAdapter(new ArrayList<Atendee>() , mActivity);
    }

    @Provides
    @PerActivity
    BookingIPresenter<BookingIView> provideBookingPresenter(
            BookingPresenter<BookingIView> presenter) {
        return presenter;
    }



    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    GridLayoutManager provideGridLayoutManager(AppCompatActivity activity) {
        return new GridLayoutManager(activity,3);
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper()
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        client.interceptors().add(interceptor);
        return    new Retrofit.Builder()
                .baseUrl(ApiEndPoint.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiHelper.class);
    }

}
