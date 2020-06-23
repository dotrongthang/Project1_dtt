package com.example.project1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.Adapter.MnSendingAdapter;
import com.example.project1.Objects.Package;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.Retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MnSendingFragment extends Fragment {

    private RecyclerView rvMnSending;
    List<Package> list;
    MnSendingAdapter adapter;

    public MnSendingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mn_sending, container, false);
        rvMnSending = (RecyclerView) view.findViewById(R.id.rvMnSending);
        list = new ArrayList<>();

        GetPackage();

        return view;
    }

    public void GetPackage(){
        DataClient getPackage = APIUtils.getData();
        Call<List<Package>> call = getPackage.GetPackageByMn("chưa gửi");
        call.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
                ArrayList<Package> arrayList = (ArrayList<Package>) response.body();
                list.addAll(arrayList);
                rvMnSending.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rvMnSending.setLayoutManager(layoutManager);
                adapter = new MnSendingAdapter(getContext(), list);
                rvMnSending.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {

            }
        });
    }
}
