package com.example.roombooking.ui.rooms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roombooking.R;
import com.example.roombooking.data.network.ApiEndPoint;
import com.example.roombooking.data.network.model.Rooms;
import com.example.roombooking.di.ActivityContext;

import java.util.List;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private static final String TAG = "UserAdapter";
    private List<Rooms> mItems;

    private Context mContext;
    private IDeliveryClick mItemListener;

    public RoomAdapter(List<Rooms> mItems , @ActivityContext Context context) {
        this.mItems = mItems;
        mContext = context;
    }

    public void setCallback(IDeliveryClick callback) {
        mItemListener = callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameTv;
        public TextView locationTv;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            locationTv = (TextView) itemView.findViewById(R.id.tv_loaction);
            imageView = (ImageView) itemView.findViewById(R.id.iv_room);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Rooms item = getItem(getAdapterPosition());
               mItemListener.onDeliveryClick(item.getName());
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_room, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rooms item = mItems.get(position);
        if(item.getImages()!=null && item.getImages().size()>0) {
            Log.d(TAG, "onBindViewHolder: " + item.getImages().get(0));
            Glide.with(mContext)
                    .load(ApiEndPoint.BASE_URL + item.getImages().get(0))
                    .into(holder.imageView);
        }
        if(item.getName()!=null)
        holder.nameTv.setText(item.getName());
        holder.locationTv.setText(item.getLocation());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateRooms(List<Rooms> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void refreshRoom() {
        notifyDataSetChanged();
    }


    private Rooms getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface IDeliveryClick {
        void onDeliveryClick(String name);
    }

}
