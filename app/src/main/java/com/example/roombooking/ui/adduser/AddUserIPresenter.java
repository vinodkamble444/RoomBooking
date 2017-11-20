

package com.example.roombooking.ui.adduser;


import com.example.roombooking.di.PerActivity;
import com.example.roombooking.ui.base.MvpPresenter;

import java.util.List;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@PerActivity
public interface AddUserIPresenter<V extends AddUserIView> extends MvpPresenter<V> {

    void setRoomName(String roomname);

    void onAddUserClick(String name, String email,String phone);

    void onBookClick(List<Atendee> listAtendee);

}
