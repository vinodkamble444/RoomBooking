package com.example.roombooking.ui.adduser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roombooking.R;
import com.example.roombooking.di.ActivityContext;

import java.util.List;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static final String TAG = "UserAdapter";
    private List<Atendee> mItems;

    private Context mContext;
    private IUserClick mItemListener;

    public UserAdapter(List<Atendee> mItems , @ActivityContext Context context) {
        this.mItems = mItems;
        mContext = context;
    }

    public void setCallback(IUserClick callback) {
        mItemListener = callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameTv;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            imageView = (ImageView) itemView.findViewById(R.id.iv_delete);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Atendee item = getItem(getAdapterPosition());
            mItems.remove(getAdapterPosition());
            if(mItems.size()>0)
            notifyDataSetChanged();
            else
                mItemListener.onUserDeleteClick();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Atendee item = mItems.get(position);

        if(item.getName()!=null)
        holder.nameTv.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAtendees(List<Atendee> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Atendee getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public List<Atendee> getAtendees() {
        return mItems;
    }
    public interface IUserClick {
        void onUserDeleteClick();
    }

}
