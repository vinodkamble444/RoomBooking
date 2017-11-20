package com.example.roombooking.ui.adduser;

import java.util.List;

/**
 * Created by Vinod on 11/18/2017.
 */

public class BookingPass {

    private Booking booking;
    private List<Atendee> passes;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public List<Atendee> getPasses() {
        return passes;
    }

    public void setPasses(List<Atendee> passes) {
        this.passes = passes;
    }

}
