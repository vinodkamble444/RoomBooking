

package com.example.roombooking.di.component;

import com.example.roombooking.di.PerActivity;
import com.example.roombooking.di.module.ActivityModule;
import com.example.roombooking.ui.adduser.AddUserActivity;
import com.example.roombooking.ui.booking.BookingActivity;
import com.example.roombooking.ui.roomdetails.RoomDetailActivity;
import com.example.roombooking.ui.rooms.RoomsActivity;

import dagger.Component;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RoomDetailActivity activity);

    void inject(AddUserActivity activity);

    void inject(RoomsActivity activity);

    void inject(BookingActivity activity);

}
