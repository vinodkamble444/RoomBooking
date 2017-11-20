
package com.example.roombooking.ui.rooms;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.roombooking.R;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.ui.base.BaseActivity;
import com.example.roombooking.ui.roomdetails.RoomDetailActivity;
import com.example.roombooking.ui.adduser.AddUserActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomsActivity extends BaseActivity implements RoomsIView, RoomAdapter.IDeliveryClick {

    @Inject
    RoomsIPresenter<RoomsIView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recylerr)
    RecyclerView recyclerView;

    @BindView(R.id.lvEmptyData)
    LinearLayout lvEmptyData;

    @Inject
    RoomAdapter recMainAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, RoomsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadFromNetwork();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
            case R.id.action_refresh:
                mPresenter.loadFromNetwork();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mPresenter.onViewInitialized();

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recMainAdapter);
        recMainAdapter.setCallback(this);
    }



    @Override
    public void refreshRooms(List<Rooms> rooms) {
        lvEmptyData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recMainAdapter.updateRooms(rooms);
    }


    @Override
    public void onDeliveryClick(String name) {
        Log.d("RoomActivty","Name"+name);
        Intent intent= RoomDetailActivity.getStartIntent(this);
        intent.putExtra("Room_Name",name);
        startActivity(intent);
    }

    @Override
    public void ShowNoDataView() {
        lvEmptyData.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }
}
