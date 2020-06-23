package com.example.project1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.Adapter.PackageDriverAdapter;
import com.example.project1.Adapter.PackageSenderAdapter;
import com.example.project1.Interface.ChangeState;
import com.example.project1.Objects.Package;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.Retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SendedFragment extends Fragment {

    private RecyclerView rvSendedDr;
    ArrayList<Package> listDriver = new ArrayList<>();
    ArrayList<Package> listSender = new ArrayList<>();
    PackageDriverAdapter adapter2;
    PackageSenderAdapter adapterSender2;
    ChangeState changeState;
    String phoneDr, phoneSender;

    public SendedFragment() {
    }

//    public SendedFragment(ChuyenTrangThai chuyenTrangThai) {
//        this.chuyenTrangThai = chuyenTrangThai;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sended, container, false);

        rvSendedDr = (RecyclerView) view.findViewById(R.id.rvSendedDr);
        changeState = (ChangeState) getActivity();

        GetData();
        if (phoneDr != null){
            GetdataSendedOfDriver();
        }
        if (phoneSender != null){
            GetdataSendedOfSender();
        }
        return view;
    }

    private void GetData(){
        Bundle bundle = getArguments();
        if (bundle != null){
            phoneDr = bundle.getString("Driver");
            phoneSender = bundle.getString("Send");
        }
    }

    private void GetdataSendedOfDriver(){
        DataClient getDr = APIUtils.getData();
        Call<List<Package>> callback = getDr.GetdataTX(phoneDr, "đã gửi");
        callback.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
                ArrayList<Package> arrayList = (ArrayList<Package>) response.body();
                listDriver.addAll(arrayList);
                rvSendedDr.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rvSendedDr.setLayoutManager(layoutManager);
                adapter2 = new PackageDriverAdapter(listDriver, getContext(), changeState);
                rvSendedDr.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {

            }
        });
    }

    private void GetdataSendedOfSender(){
        DataClient getSender = APIUtils.getData();
        Call<List<Package>> callback = getSender.GetdataSender(phoneSender, "đã gửi");
        callback.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
                ArrayList<Package> arrayList = (ArrayList<Package>) response.body();
                listSender.addAll(arrayList);
                rvSendedDr.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rvSendedDr.setLayoutManager(layoutManager);
                adapterSender2 = new PackageSenderAdapter(listSender, getContext(), changeState);
                rvSendedDr.setAdapter(adapterSender2);
                adapterSender2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {

            }
        });
    }

}
