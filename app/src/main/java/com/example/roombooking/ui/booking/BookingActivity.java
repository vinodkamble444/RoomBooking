
package com.example.roombooking.ui.booking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.roombooking.R;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.adduser.AddUserActivity;
import com.example.roombooking.ui.base.BaseActivity;
import com.example.roombooking.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends BaseActivity implements BookingIView, BookingAdapter.ITimeSlotClick {

    @Inject
    BookingIPresenter<BookingIView> mBookingPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.time_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.tv_start_time)
    TextView mStartTextView;

    @BindView(R.id.tv_end_time)
    TextView mEndTextView;

    @Inject
    BookingAdapter mBookingAdapter;

    @Inject
    GridLayoutManager mGridLayoutManager;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, BookingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
       /* getActionBar().setDisplayHomeAsUpEnabled(true);*/
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mBookingPresenter.onAttach(this);

        mBookingPresenter.setRoomName(getIntent().getStringExtra("Room_Name"));

        setUp();
    }


    @Override
    public void refreshBookingData(Rooms rooms) {
        List<TimeSlot> timeSlotList = new ArrayList<>();
        if (rooms.getAvail() != null && rooms.getAvail().size() > 0) {
            for (String s : rooms.getAvail()) {
                timeSlotList.addAll(CommonUtils.getTimeSlots(s));
            }
            mBookingAdapter.updateTimeSlots(timeSlotList);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mBookingPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.select_timeslot));
        mBookingPresenter.onViewInitialized();

        recyclerView.setLayoutManager(mGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mBookingAdapter);
        mBookingAdapter.setCallback(this);
    }

    @Override
    public void onTimeSlotClick(String[] time) {
        mBookingPresenter.setTimeSlot(time);
        mStartTextView.setText("Start Time : " + time[0]);
        mEndTextView.setText("End Time : " + time[1]);
        Intent intent = AddUserActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

}
