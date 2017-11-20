package com.example.roombooking.ui.booking;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.roombooking.R;
import com.example.roombooking.di.ActivityContext;

import java.util.List;

/**
 * Creaated by Vinod on   19/11/17.
 */

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private static final String TAG = "BookingAdapter";
    private List<TimeSlot> mItems;
    private Context mContext;
    private ITimeSlotClick mItemListener;
    private int slotCount;
    private String[] time=new String[2];
    public BookingAdapter(List<TimeSlot> mItems, @ActivityContext Context context) {
        this.mItems = mItems;
        mContext = context;
    }

    public void setCallback(ITimeSlotClick callback) {
        mItemListener = callback;
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public Button btnTime;

        public BookingViewHolder(View itemView) {
            super(itemView);
            btnTime = (Button) itemView.findViewById(R.id.btn_time);
            btnTime.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String item = getItem(getAdapterPosition());
            Button button = (Button) view;
            if (button.isActivated()) {
                button.setActivated(false);
                button.setTextColor(Color.WHITE);
                mItems.get(getAdapterPosition()).setSelected(false);
                slotCount--;
            } else {
                button.setActivated(true);
                mItems.get(getAdapterPosition()).setSelected(true);
                button.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                slotCount++;
                if (slotCount == 1) {
                    Log.d(TAG, "onClick: StartTime " + mItems.get(getAdapterPosition()).getTime());
                    time[0]=mItems.get(getAdapterPosition()).getTime();
                } else if(slotCount == 2) {
                    Log.d(TAG, "onClick: EndTime " + mItems.get(getAdapterPosition()).getTime());
                    time[1]=mItems.get(getAdapterPosition()).getTime();
                    mItemListener.onTimeSlotClick(time);
                }
            }
            notifyDataSetChanged();


               /*mItemListener.onTimeSlotClick(item.getLocation().getLat(),
                       item.getLocation().getLng(),item.getLocation().getAddress());*/
        }
    }


    @Override
    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.time_slot_item, parent, false);

        BookingViewHolder viewHolder = new BookingViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookingViewHolder holder, int position) {
        TimeSlot time = mItems.get(position);
        holder.btnTime.setText(time.getTime());
        if (time.isSelected()) {
            holder.btnTime.setActivated(true);
            holder.btnTime.setTextColor(Color.WHITE);
        } else {
            holder.btnTime.setActivated(false);
            holder.btnTime.setTextColor(ContextCompat.getColor(mContext, R.color.navy_blue_dark));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateTimeSlots(List<TimeSlot> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private String getItem(int adapterPosition) {
        return mItems.get(adapterPosition).getTime();
    }

    public interface ITimeSlotClick {
        void onTimeSlotClick(String[] time);
    }
}
