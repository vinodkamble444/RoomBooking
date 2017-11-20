package com.example.roombooking.ui.roomdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.roombooking.data.network.ApiEndPoint;
import com.example.roombooking.di.ActivityContext;
import com.example.roombooking.R;

import java.util.List;

/**
 *  Creaated by Vinod on   19/11/17.
 */

public class RoomDetailAdapter extends RecyclerView.Adapter<RoomDetailAdapter.ViewHolder> {

    private List<String> mItems;

    private Context mContext;
    private IRoomsImageClick mItemListener;


    public RoomDetailAdapter(List<String> mItems , @ActivityContext Context context) {
        this.mItems = mItems;
        mContext = context;
    }

    public void setCallback(IRoomsImageClick callback) {
        mItemListener = callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String item = getItem(getAdapterPosition());
               /*mItemListener.onTimeSlotClick(item.getLocation().getLat(),
                       item.getLocation().getLng(),item.getLocation().getAddress());*/
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.gallery_thumbnail, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imageUrl= mItems.get(position);
        Glide.with(mContext)
                .load(ApiEndPoint.BASE_URL + imageUrl)
                .into(holder.imageView);
        /*Glide.with(mContext).load(imageUrl)
                .thumbnail(0.2f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateDeliveries(List<String> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private String getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface IRoomsImageClick {
        void onRoomsImageClick(double lat, double lng, String address);
    }
}
