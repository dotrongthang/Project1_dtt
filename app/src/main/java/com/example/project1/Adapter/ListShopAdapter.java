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
import com.example.project1.Objects.Sender;
import com.example.project1.R;

import java.util.List;

public class ListShopAdapter extends RecyclerView.Adapter<ListShopAdapter.ViewHolder> {

    List<Sender> senders;
    Context context;
    ChangeInfo changeInfo;

    public ListShopAdapter(List<Sender> senders, Context context, ChangeInfo changeInfo) {
        this.senders = senders;
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
        holder.tvListName.setText(senders.get(position).getHoTen());
        holder.tvListPhone.setText(senders.get(position).getSDT());
        holder.tvListPw.setText(senders.get(position).getMatKhau());
        holder.tvListMoney.setText(senders.get(position).getTienGui());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInfo.changeInfoSender(senders.get(position));
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInfo.DeleteSender(senders.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return senders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvListName;
        private TextView tvListPhone;
        private TextView tvListPw;
        private TextView tvListMoney;
        ImageView imgEdit, imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListName = (TextView) itemView.findViewById(R.id.tvListName);
            tvListPhone = (TextView) itemView.findViewById(R.id.tvListPhone);
            tvListPw = (TextView) itemView.findViewById(R.id.tvListPw);
            tvListMoney = (TextView) itemView.findViewById(R.id.tvListMoney);
            imgEdit = (ImageView) itemView.findViewById(R.id.ListEdit);
            imgDelete = (ImageView) itemView.findViewById(R.id.ListDelete);
        }
    }
}
