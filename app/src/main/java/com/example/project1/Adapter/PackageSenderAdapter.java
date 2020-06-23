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

import com.example.project1.Interface.ChangeState;
import com.example.project1.Objects.Package;
import com.example.project1.R;

import java.util.List;

public class PackageSenderAdapter extends RecyclerView.Adapter<PackageSenderAdapter.ViewHolder> {

    List<Package> packages;
    Context context;
    ChangeState changeState;

    public PackageSenderAdapter(List<Package> packages, Context context, ChangeState changeState) {
        this.packages = packages;
        this.context = context;
        this.changeState = changeState;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_package_sender, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (packages.get(position).getTinhTrang().compareTo("chưa gửi") == 0){
            holder.lnEdit.setVisibility(View.VISIBLE);
        }
        holder.tvNamePackage.setText(packages.get(position).getTenBK());
        holder.tvReceiver.setText("Người nhận: " + packages.get(position).getNguoiNhan());
        holder.tvTo.setText("Nơi nhận: " + packages.get(position).getNoiNhan());
        holder.tvDriver.setText("Tài xế: " + packages.get(position).getTaiXe());
        holder.lnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeState.EditPackage(packages.get(position));
            }
        });
        holder.imgDeleteS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (packages.get(position).getTinhTrang().compareTo("chưa gửi") == 0){
                    changeState.DeletePackage(packages.get(position), 0);
                }else {
                    changeState.DeletePackage(packages.get(position), 1);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNamePackage;
        private TextView tvReceiver;
        private TextView tvTo;
        private TextView tvDriver;
        private LinearLayout lnEdit;
        private ImageView imgDeleteS;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamePackage = (TextView) itemView.findViewById(R.id.tvNamePackage);
            tvReceiver = (TextView) itemView.findViewById(R.id.tvReceiver);
            tvTo = (TextView) itemView.findViewById(R.id.tvTo);
            tvDriver = (TextView) itemView.findViewById(R.id.tvDriver);
            lnEdit = (LinearLayout) itemView.findViewById(R.id.lnEdit);
            imgDeleteS = (ImageView) itemView.findViewById(R.id.imgDeleteS);
        }
    }
}
