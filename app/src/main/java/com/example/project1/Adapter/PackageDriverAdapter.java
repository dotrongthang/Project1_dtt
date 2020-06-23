package com.example.project1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Interface.ChangeState;
import com.example.project1.Objects.Package;
import com.example.project1.R;

import java.util.List;

public class PackageDriverAdapter extends RecyclerView.Adapter<PackageDriverAdapter.ViewHolder>{
    List<Package> packages;
    Context context;
    ChangeState changeState;

    public PackageDriverAdapter(List<Package> packages, Context context, ChangeState changeState) {
        this.packages = packages;
        this.context = context;
        this.changeState = changeState;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_package_driver, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (packages.get(position).getTinhTrang().compareTo("chưa gửi") == 0){
            holder.lnTrangThai.setVisibility(View.VISIBLE);
        }
        holder.tvSender.setText(packages.get(position).getNguoiGui());
        holder.tvNamePack.setText(packages.get(position).getTenBK());
        holder.tvReceiver.setText("Người nhận: " + packages.get(position).getNguoiNhan());
        holder.tvDes.setText("Nơi nhận: " + packages.get(position).getNoiNhan());
        holder.tvState.setText(packages.get(position).getTinhTrang());
        holder.lnTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState.ChuyenTT(position);
            }
        });
        holder.rlCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState.Call(packages.get(position).getNguoiNhan());
            }
        });
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSender, tvNamePack, tvReceiver, tvDes, tvState, tvDriver;
        LinearLayout lnTrangThai;
        RelativeLayout rlCall;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           tvSender = (TextView) itemView.findViewById(R.id.txtNgG);
           tvNamePack = (TextView) itemView.findViewById(R.id.txtTenBk);
           tvReceiver = (TextView) itemView.findViewById(R.id.tvReceiverMn);
           tvDes = (TextView) itemView.findViewById(R.id.txtNN);
           tvDriver = (TextView) itemView.findViewById(R.id.txtDriver);
           tvState = (TextView) itemView.findViewById(R.id.txtTT);
           lnTrangThai = (LinearLayout) itemView.findViewById(R.id.lnTtTx);
           rlCall = (RelativeLayout) itemView.findViewById(R.id.rlCall);
       }
   }
}
