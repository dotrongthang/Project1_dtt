package com.example.project1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Interface.AddDriverToPackage;
import com.example.project1.Objects.Driver;
import com.example.project1.R;

import java.util.List;

public class ChooseDriverAdapter extends RecyclerView.Adapter<ChooseDriverAdapter.ViewHolder> {

    Context context;
    List<Driver> drivers;
    AddDriverToPackage addDriverToPackage;

    public ChooseDriverAdapter(Context context, List<Driver> drivers, AddDriverToPackage addDriverToPackage) {
        this.context = context;
        this.drivers = drivers;
        this.addDriverToPackage = addDriverToPackage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_choose_driver, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvName.setText(drivers.get(position).getHoTen());
        holder.tvPhone.setText(drivers.get(position).getSDT());
        holder.lnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDriverToPackage.sendData(drivers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPhone;
        LinearLayout lnChoose;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvNameChoose);
            tvPhone = (TextView) itemView.findViewById(R.id.tvPhoneChoose);
            lnChoose = (LinearLayout) itemView.findViewById(R.id.lnChooseDriver);
        }
    }


}
