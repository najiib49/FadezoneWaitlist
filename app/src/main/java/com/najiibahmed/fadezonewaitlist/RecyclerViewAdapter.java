package com.najiibahmed.fadezonewaitlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> customerNames = new ArrayList<>();
    private ArrayList<String> customerPhoneNumbers = new ArrayList<>();



    public RecyclerViewAdapter(MainActivity mainActivity, ArrayList<String> customerNames, ArrayList<String> customerPhoneNumbers) {
        this.customerNames = customerNames;
        this.customerPhoneNumbers = customerPhoneNumbers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Responsible for inflating the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cutomer_list_item,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //called everytime a new item is added to the list
        Log.d(TAG, "onBindViewHolder: Called");
        holder.customerName.setText(customerNames.get(position));
        holder.customerPhone.setText(customerPhoneNumbers.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on  " + customerNames.get(position));

//                Toast.makeText(this, customerNames.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return customerNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // Holds the widgets from customer list item in memory
        CheckBox checkBox;
        CardView parentLayout;
        TextView customerName;
        TextView customerPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            customerName = itemView.findViewById(R.id.nameTextView);
            customerPhone = itemView.findViewById(R.id.phoneTextView);
        }
    }

}
