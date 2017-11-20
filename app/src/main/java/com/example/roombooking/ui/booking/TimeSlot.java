package com.example.roombooking.ui.booking;

/**
 * Created by Vinod on 11/19/2017.
 */

public class TimeSlot {


    private String time;
    private boolean isSelected = false;
    public TimeSlot(String time)
    {
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
