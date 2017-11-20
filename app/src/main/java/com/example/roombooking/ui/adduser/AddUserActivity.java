
package com.example.roombooking.ui.adduser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.roombooking.R;
import com.example.roombooking.ui.base.BaseActivity;
import com.example.roombooking.ui.rooms.RoomsActivity;
import com.example.roombooking.utils.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUserActivity extends BaseActivity implements AddUserIView,UserAdapter.IUserClick {

    @Inject
    AddUserIPresenter<AddUserIView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.et_email)
    EditText mEmailEditText;

    @BindView(R.id.et_phone)
    EditText mPhoneEditText;

    @BindView(R.id.et_name)
    EditText mNameEditText;

    @BindView(R.id.user_Recycler)
    RecyclerView recyclerView;

    @BindView(R.id.lvEmptyData)
    LinearLayout lvEmptyData;

    @BindView(R.id.btn_book)
    Button btnBook;


    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    UserAdapter mUserAdapter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AddUserActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_user);

       getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @OnClick(R.id.btn_login)
    void onAddUserClick(View v) {
        mPresenter.onAddUserClick(mNameEditText.getText().toString(),
                mEmailEditText.getText().toString(),mPhoneEditText.getText().toString());
    }

    @OnClick(R.id.btn_book)
    void onBookClick(View v) {
        mPresenter.onBookClick(mUserAdapter.getAtendees());
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
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getString(R.string.add_user));
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mUserAdapter);
        mUserAdapter.setCallback(this);

    }

    @Override
    public void refreshUserData(List<Atendee> atendees) {
        recyclerView.setVisibility(View.VISIBLE);
        btnBook.setVisibility(View.VISIBLE);
        lvEmptyData.setVisibility(View.GONE);
        mUserAdapter.updateAtendees(atendees);
    }

    @Override
    public void resetFields() {
        mEmailEditText.setText("");
        mPhoneEditText.setText("");
        mNameEditText.setText("");
        showMessage(R.string.user_added);
    }

    @Override
    public void onUserDeleteClick() {
    recyclerView.setVisibility(View.GONE);
        btnBook.setVisibility(View.GONE);
        lvEmptyData.setVisibility(View.VISIBLE);
    }

    public void showSuccessDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(AddUserActivity.this);
        builder.setTitle(getString(R.string.dialog_title));
        builder.setMessage(getString(R.string.dialog_message));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(RoomsActivity.getStartIntent(AddUserActivity.this));
                        finish();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
