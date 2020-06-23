package com.example.project1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Interface.AddDriverToPackage;
import com.example.project1.Interface.ChangeState;
import com.example.project1.Objects.Package;
import com.example.project1.R;

import java.util.List;

public class NewPackageAdapter extends RecyclerView.Adapter<NewPackageAdapter.ViewHolder> {
    List<Package> packages;
    Context context;
    AddDriverToPackage addDriverToPackage;

    public NewPackageAdapter(List<Package> packages, Context context, AddDriverToPackage addDriverToPackage) {
        this.packages = packages;
        this.context = context;
        this.addDriverToPackage = addDriverToPackage;
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
        holder.imgCall.setVisibility(View.INVISIBLE);
        holder.tvSender.setText(packages.get(position).getNguoiGui());
        holder.tvNamePk.setText(packages.get(position).getTenBK());
        holder.tvReceiver.setText("Người nhận: " + packages.get(position).getNguoiNhan());
        holder.tvDes.setText("Nơi nhận: " + packages.get(position).getNoiNhan());
        holder.tvState.setText("Phân công");
        holder.lnTrangThai.setVisibility(View.VISIBLE);
        holder.lnTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addDriverToPackage.addDriver(packages.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSender, tvNamePk, tvReceiver, tvDes, tvState;
        LinearLayout lnTrangThai;
        ImageView imgCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSender = (TextView) itemView.findViewById(R.id.txtNgG);
            tvNamePk = (TextView) itemView.findViewById(R.id.txtTenBk);
            tvReceiver = (TextView) itemView.findViewById(R.id.tvReceiverMn);
            tvDes = (TextView) itemView.findViewById(R.id.txtNN);
            tvState = (TextView) itemView.findViewById(R.id.txtTT);
            lnTrangThai = (LinearLayout) itemView.findViewById(R.id.lnTtTx);
            imgCall = (ImageView) itemView.findViewById(R.id.imgCall);
        }
    }
}
