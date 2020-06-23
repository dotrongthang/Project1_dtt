package com.example.project1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Interface.ChangeInfo;
import com.example.project1.Interface.ChangeInfoD;
import com.example.project1.Objects.Driver;
import com.example.project1.R;

import java.util.List;

public class ListDriverAdapter extends RecyclerView.Adapter<ListDriverAdapter.ViewHolder> {

    List<Driver> drivers;
    Context context;
    ChangeInfoD changeInfo;

    public ListDriverAdapter(List<Driver> drivers, Context context, ChangeInfoD changeInfo) {
        this.drivers = drivers;
        this.context = context;
        this.changeInfo = changeInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_person, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvListName.setText(drivers.get(position).getHoTen());
        holder.tvListPhone.setText(drivers.get(position).getSDT());
        holder.tvListPw.setText(drivers.get(position).getMatKhau());
        holder.tvListMoney.setText(drivers.get(position).getDoanhThu());
        holder.imgList.setImageResource(R.drawable.icon_dri);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInfo.changeInfoDriver(drivers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvListName;
        private TextView tvListPhone;
        private TextView tvListPw;
        private TextView tvListMoney;
        ImageView imgList, imgEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListName = (TextView) itemView.findViewById(R.id.tvListName);
            tvListPhone = (TextView) itemView.findViewById(R.id.tvListPhone);
            tvListPw = (TextView) itemView.findViewById(R.id.tvListPw);
            tvListMoney = (TextView) itemView.findViewById(R.id.tvListMoney);
            imgEdit = (ImageView) itemView.findViewById(R.id.ListEdit);
            imgList = (ImageView) itemView.findViewById(R.id.imgList);
        }
    }
}
