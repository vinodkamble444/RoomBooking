
package com.example.roombooking.ui.roomdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roombooking.R;
import com.example.roombooking.data.network.ApiEndPoint;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.BaseActivity;
import com.example.roombooking.ui.booking.BookingActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomDetailActivity extends BaseActivity implements RoomDetailIView, RoomDetailAdapter.IRoomsImageClick {

    @Inject
    RoomIPresenter<RoomDetailIView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.galleryRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar_title)
    TextView mRoomNameTextView;

    @BindView(R.id.toolbar_image)
    ImageView  mImageViewToolBar;

    @BindView(R.id.tv_location)
    TextView mLocationTextView;

    @BindView(R.id.tv_size)
    TextView mSizeTextView;

    @BindView(R.id.tv_capacity)
    TextView mCapacityTextView;

    @BindView(R.id.tv_equipment)
    TextView mEquipmentTextView;

    @Inject
    RoomDetailAdapter recMainAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    private String mRoomName;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RoomDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);
       /* getActionBar().setDisplayHomeAsUpEnabled(true);*/
        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        mPresenter.setRoomName(getIntent().getStringExtra("Room_Name"));
        mRoomName=getIntent().getStringExtra("Room_Name");
        setUp();
    }

    @OnClick(R.id.fab)
    void onServerLoginClick(View v) {
       mPresenter.onCheckAvailabilityClick(mRoomName);
    }


    @Override
    public void refreshRoomData(Rooms rooms) {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setTitle(mRoomName);

        mCapacityTextView.setText(String.valueOf(rooms.getCapacity()));
        mSizeTextView.setText(rooms.getSize());
        mLocationTextView.setText(rooms.getLocation());
        StringBuilder myString = new StringBuilder();

        for(String classLine: rooms.getEquipment())
        {
            myString.append(classLine).append("\n");
        }
        mEquipmentTextView.setText(myString.toString());
        if(rooms.getImages()!=null && rooms.getImages().size()>0)
        recMainAdapter.updateDeliveries(rooms.getImages());
        Glide.with(this)
                .load(ApiEndPoint.BASE_URL + rooms.getImages().get(0))
                .into(mImageViewToolBar);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

        mPresenter.onViewInitialized();
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recMainAdapter);
        recMainAdapter.setCallback(this);
    }


    @Override
    public void openBookingActivity() {
        Intent intent= BookingActivity.getStartIntent(this);
        intent.putExtra("Room_Name",mRoomName);
        startActivity(intent);
    }

    @Override
    public void onRoomsImageClick(double lat, double lng, String address) {

    }

}
