

package com.example.roombooking.ui.rooms;


import com.example.roombooking.di.PerActivity;
import com.example.roombooking.ui.base.MvpPresenter;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@PerActivity
public interface RoomsIPresenter<V extends RoomsIView> extends MvpPresenter<V> {

    void onViewInitialized();

    void loadFromNetwork();
}
