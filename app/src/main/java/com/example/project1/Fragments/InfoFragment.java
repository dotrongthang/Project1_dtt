package com.example.project1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project1.Interface.ChangeInfo;
import com.example.project1.Objects.Driver;
import com.example.project1.Objects.Sender;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.Retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfoFragment extends Fragment {

    String phoneDr, phoneSender;
    ChangeInfo changeInfo;
    ArrayList<Driver> listDr;
    ArrayList<Sender> listSender;
    private TextView tvNameDr;
    private TextView tvPhoneDr;
    private TextView tvPwDr;
    private TextView tvMoneyDr;
    private LinearLayout lnUpdateDr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        tvNameDr    = (TextView) view.findViewById(R.id.tvNameDr);
        tvPhoneDr   = (TextView) view.findViewById(R.id.tvPhoneDr);
        tvPwDr      = (TextView) view.findViewById(R.id.tvPwDr);
        tvMoneyDr   = (TextView) view.findViewById(R.id.tvMoneyDr);
        lnUpdateDr  = (LinearLayout) view.findViewById(R.id.lnUpdateDr);
        listDr = new ArrayList<>();
        listSender = new ArrayList<>();
        changeInfo = (ChangeInfo) getActivity();

        GetData();
        if (phoneDr != null){
            GetDataDriver();
        }
        if (phoneSender != null){
            GetDataSender();
        }

        lnUpdateDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeInfo.changeInfo();
            }
        });

        return view;
    }

    private void GetData(){
        Bundle bundle = getArguments();
        if (bundle != null){
            phoneDr = bundle.getString("Driver");
            phoneSender = bundle.getString("Send");
        }

    }

    private void GetDataDriver(){
        DataClient getDr = APIUtils.getData();
        Call<List<Driver>> callback = getDr.GetDrByPhone(phoneDr);
        callback.enqueue(new Callback<List<Driver>>() {
            @Override
            public void onResponse(Call<List<Driver>> call, Response<List<Driver>> response) {
                ArrayList<Driver> drivers = (ArrayList<Driver>) response.body();
                listDr.addAll(drivers);
                tvNameDr.setText(listDr.get(0).getHoTen());
                tvPhoneDr.setText(listDr.get(0).getSDT());
                tvPwDr.setText(listDr.get(0).getMatKhau());
                tvMoneyDr.setText(listDr.get(0).getDoanhThu());
            }

            @Override
            public void onFailure(Call<List<Driver>> call, Throwable t) {

            }
        });
    }

    private void GetDataSender() {
        DataClient getSender = APIUtils.getData();
        Call<List<Sender>> call = getSender.GetSenderByPhone(phoneSender);
        call.enqueue(new Callback<List<Sender>>() {
            @Override
            public void onResponse(Call<List<Sender>> call, Response<List<Sender>> response) {
                ArrayList<Sender> senders = (ArrayList<Sender>) response.body();
                listSender.addAll(senders);
                tvNameDr.setText(listSender.get(0).getHoTen());
                tvPhoneDr.setText(listSender.get(0).getSDT());
                tvPwDr.setText(listSender.get(0).getMatKhau());
                tvMoneyDr.setText(listSender.get(0).getTienGui());
            }

            @Override
            public void onFailure(Call<List<Sender>> call, Throwable t) {

            }
        });

    }
}
