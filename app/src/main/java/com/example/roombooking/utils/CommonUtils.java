

package com.example.roombooking.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;


import com.example.roombooking.R;
import com.example.roombooking.ui.booking.TimeSlot;


import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creaated by Vinod on   19/11/17.
 */

public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static  boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(new Date());
    }


    public static List<TimeSlot> getTimeSlots(@NonNull String availTimeRange) {
        List<TimeSlot> timeSlotList=null;
        try {
            if(!TextUtils.isEmpty(availTimeRange)){
            String[] elements = availTimeRange.split("-");
            String startTime = elements[0].trim();
            String lastTime = elements[1].trim();
            int gapInMinutes = 15;  // Define your span-of-time.
            LocalTime time1 = LocalTime.parse(startTime);
            LocalTime time2 = LocalTime.parse(lastTime);  // '00:00'
            Minutes minutes = Minutes.minutesBetween(time1, time2);

            int loops = ((int) minutes.getMinutes() / gapInMinutes);
            timeSlotList = new ArrayList<>(loops);

            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("12:00");
            for (int i = 1; i <= loops; i++) {
              //  Log.d(TAG, "getTimeSlots: "+DateTimeFormat.forPattern("hh:mma").print(time1));
                timeSlotList.add(new TimeSlot(DateTimeFormat.forPattern("hh:mm").print(time1)));
                // Set up next loop.
                time1 = time1.plusMinutes(gapInMinutes);
            }

            System.out.println(timeSlotList.size() + " time slots: ");
            Log.d(TAG, "getTimeSlots: " + timeSlotList.size());

            // System.out.println( duration.getSeconds()/60 + " time diif: " ) ;
            System.out.println(timeSlotList);
            Log.d(TAG, "getTimeSlots: " + timeSlotList.size());
            }
        } catch (Exception e) {
            Log.d(TAG, "getTimeSlots: " + e);
        }
        return timeSlotList;
    }


    public static long timeConversion(String time) {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH); //Specify your locale
        long unixTime = 0;
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30")); //Specify your timezone
        try {
            unixTime = dateFormat.parse(time).getTime();
            unixTime = unixTime / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return unixTime;
    }
}
