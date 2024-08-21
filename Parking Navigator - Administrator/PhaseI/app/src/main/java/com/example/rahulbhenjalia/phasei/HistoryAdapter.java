package com.example.rahulbhenjalia.phasei;

/**
 * Created by Rahul Bhenjalia on 16-04-2018.
 **/
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private List<History> mainList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name,Amount,Phone,Vehicle,FromTo,Date;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            Amount = (TextView) view.findViewById(R.id.Amount);
            Phone = (TextView) view.findViewById(R.id.Phone);
            Vehicle = (TextView) view.findViewById(R.id.Vehicle);
            FromTo = (TextView) view.findViewById(R.id.FromTo);
            Date = (TextView) view.findViewById(R.id.Date);

        }
    }


    public HistoryAdapter(List<History> mainList) {
        this.mainList = mainList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        History history = mainList.get(position);
        holder.Name.setText(history.getUsername());
        holder.Amount.setText(history.getAmount());
        holder.FromTo.setText(history.getStartTime()+"-"+history.getEndTime());
        holder.Vehicle.setText(history.getVehicleNo());
        holder.Date.setText(history.getDate());
        holder.Phone.setText(history.getPhone());



    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }
}