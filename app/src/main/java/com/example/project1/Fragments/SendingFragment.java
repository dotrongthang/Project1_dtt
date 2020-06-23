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


public class SendingFragment extends Fragment {

    private RecyclerView rvPackage;
    public static ArrayList<Package> listPackageDr = new ArrayList<>();
    ArrayList<Package> listPackageSender;
    public static PackageDriverAdapter adapter;
    PackageSenderAdapter adapterSender;
    ChangeState changeState;
    String phoneDriver, phoneSender;

    public SendingFragment() {
    }

//    public SendingFragment(ChuyenTrangThai chuyenTrangThai) {
//        this.chuyenTrangThai = chuyenTrangThai;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sending, container, false);

        rvPackage = (RecyclerView) view.findViewById(R.id.rvBuuKienTX);
        changeState = (ChangeState) getActivity();
        listPackageSender = new ArrayList<>();

        GetData();
        if (phoneDriver != null){
            GetDataPackageDr();
        }
        if (phoneSender != null){
            GetDataPackageSender();
        }

        return view;
    }

    private void GetData(){
        Bundle bundle = getArguments();
        if (bundle != null){
            phoneDriver = bundle.getString("Driver");
            phoneSender = bundle.getString("Send");
        }
    }

    private void GetDataPackageDr(){
        listPackageDr.clear();
        DataClient getPackage = APIUtils.getData();
        Call<List<Package>> callback = getPackage.GetdataTX(phoneDriver, "chưa gửi");
        callback.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
                ArrayList<Package> mangbk = (ArrayList<Package>) response.body();
                listPackageDr.addAll(mangbk);
                rvPackage.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rvPackage.setLayoutManager(layoutManager);
                adapter = new PackageDriverAdapter(listPackageDr, getContext(), changeState);
                rvPackage.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {

            }
        });
    }

    private void GetDataPackageSender(){
        listPackageSender.clear();
        DataClient getBKTX = APIUtils.getData();
        Call<List<Package>> callback = getBKTX.GetdataSender(phoneSender, "chưa gửi");
        callback.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
                ArrayList<Package> mangbk = (ArrayList<Package>) response.body();
                listPackageSender.addAll(mangbk);
                rvPackage.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rvPackage.setLayoutManager(layoutManager);
                adapterSender = new PackageSenderAdapter(listPackageSender, getContext(), changeState);
                rvPackage.setAdapter(adapterSender);
            }

            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {

            }
        });
    }

}
