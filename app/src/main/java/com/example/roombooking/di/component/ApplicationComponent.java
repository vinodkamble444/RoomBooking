

package com.example.roombooking.di.component;

import android.app.Application;
import android.content.Context;

import com.example.roombooking.MyApp;
import com.example.roombooking.data.DataManager;
import com.example.roombooking.di.ApplicationContext;
import com.example.roombooking.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MyApp app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}