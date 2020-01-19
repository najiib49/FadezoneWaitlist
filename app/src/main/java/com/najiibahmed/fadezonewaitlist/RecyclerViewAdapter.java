package com.najiibahmed.fadezonewaitlist;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements
        ItemTouchHelperAdapter
{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> customerNames = new ArrayList<>();
    private ArrayList<String> customerPhoneNumbers = new ArrayList<>();
    private ItemTouchHelper itemTouchHelper;
    private OnCustomerListener onCustomerListener;




    public RecyclerViewAdapter(MainActivity mainActivity, ArrayList<String> customerNames, ArrayList<String> customerPhoneNumbers, OnCustomerListener onCustomerListener) {
        this.customerNames = customerNames;
        this.customerPhoneNumbers = customerPhoneNumbers;
        this.onCustomerListener = onCustomerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Responsible for inflating the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutomer_list_item,parent, false);
        ViewHolder holder = new ViewHolder(view, onCustomerListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //called every time a new item is added to the list
        Log.d(TAG, "onBindViewHolder: Called");
        holder.customerName.setText(customerNames.get(position));
        holder.customerPhone.setText(customerPhoneNumbers.get(position));

//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: clicked on  " + customerNames.get(position));
//
////                Toast.makeText(this, customerNames.get(position), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return customerNames.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        String fromName = customerNames.get(fromPosition);
        customerNames.remove(fromName);
        customerNames.add(toPosition,fromName);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSwiped(int position) {
        customerNames.remove(position);
        notifyItemRemoved(position);
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnTouchListener,
            GestureDetector.OnGestureListener {
        // Holds the widgets from customer list item in memory
        CheckBox checkBox;
        CardView parentLayout;
        TextView customerName;
        TextView customerPhone;
        GestureDetector gestureDetector;
        OnCustomerListener onCustomerListener;
        public ViewHolder(@NonNull View itemView, OnCustomerListener onCustomerListener) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            parentLayout = itemView.findViewById(R.id.cardview);
            customerName = itemView.findViewById(R.id.nameTextView);
            customerPhone = itemView.findViewById(R.id.phoneTextView);
            gestureDetector = new GestureDetector(itemView.getContext(),this);
            this.onCustomerListener = onCustomerListener;
//            parentLayout.setCardBackgroundColor(Color.pink);
            itemView.setOnTouchListener(this);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onCustomerListener.onCustomerClick(getAdapterPosition());
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            itemTouchHelper.startDrag(this);

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return true;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            gestureDetector.onTouchEvent(event);
            return true;
        }
    }

    public interface OnCustomerListener{
        void onCustomerClick(int position);
    }
}
