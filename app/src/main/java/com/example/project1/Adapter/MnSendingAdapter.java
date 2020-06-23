package com.example.project1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Objects.Package;
import com.example.project1.R;

import java.util.List;

public class MnSendingAdapter extends RecyclerView.Adapter<MnSendingAdapter.ViewHolder> {

    Context context;
    List<Package> packages;

    public MnSendingAdapter(Context context, List<Package> packages) {
        this.context = context;
        this.packages = packages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_package_driver, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if (packages.get(position).getTaiXe().compareTo("chưa có") == 0){
////            holder.myLnPackage.setVisibility(View.INVISIBLE);
////            removePackage.remove(position);
//        }
        holder.imgCall.setVisibility(View.INVISIBLE);
        holder.tvSender.setText(packages.get(position).getNguoiGui());
        holder.tvNamePack.setText(packages.get(position).getTenBK());
        holder.tvReceiver.setText("Người nhận: " + packages.get(position).getNguoiNhan());
        holder.tvDes.setText("Nơi nhận: " + packages.get(position).getNoiNhan());
        holder.rlTvDriver.setVisibility(View.VISIBLE);
        holder.tvDriver.setText("Tài xế: " + packages.get(position).getTaiXe());
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSender, tvNamePack, tvReceiver, tvDes, tvState, tvDriver;
        LinearLayout lnTrangThai, myLnPackage;
        RelativeLayout rlTvDriver;
        ImageView imgCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSender = (TextView) itemView.findViewById(R.id.txtNgG);
            tvNamePack = (TextView) itemView.findViewById(R.id.txtTenBk);
            tvReceiver = (TextView) itemView.findViewById(R.id.tvReceiverMn);
            tvDes = (TextView) itemView.findViewById(R.id.txtNN);
            tvDriver = (TextView) itemView.findViewById(R.id.txtDriver);
            tvState = (TextView) itemView.findViewById(R.id.txtTT);
            lnTrangThai = (LinearLayout) itemView.findViewById(R.id.lnTtTx);
            rlTvDriver = (RelativeLayout) itemView.findViewById(R.id.rlTvDriver);
            imgCall = (ImageView) itemView.findViewById(R.id.imgCall);
//            myLnPackage = (LinearLayout) itemView.findViewById(R.id.myLnPackage);

        }
    }
}
