

package com.example.roombooking.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.example.roombooking.BuildConfig;
import com.example.roombooking.R;
import com.example.roombooking.data.AppDataManager;
import com.example.roombooking.data.DataManager;
import com.example.roombooking.data.db.AppDbHelper;
import com.example.roombooking.data.db.DbHelper;
import com.example.roombooking.data.network.ApiHelper;
import com.example.roombooking.data.prefs.AppPreferencesHelper;
import com.example.roombooking.data.prefs.PreferencesHelper;
import com.example.roombooking.di.ApiInfo;
import com.example.roombooking.di.ApplicationContext;
import com.example.roombooking.di.DatabaseInfo;
import com.example.roombooking.di.PreferenceInfo;
import com.example.roombooking.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 *  Creaated by Vinod on   19/11/17.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return "";
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }



    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }



    @Provides
    @Singleton
    ApiHelper provideApiHelper()
    {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return    new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ApiHelper.class);


    }

}
