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


public class MnSendedFragment extends Fragment {

    private RecyclerView rvMnSended;
    List<Package> listSended;
    MnSendingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mn_sended, container, false);
        rvMnSended = (RecyclerView) view.findViewById(R.id.rvMnSended);
        listSended = new ArrayList<>();
        GetPackage();
        return view;
    }

    public void GetPackage(){
        DataClient getPackage = APIUtils.getData();
        Call<List<Package>> call = getPackage.GetPackageByMn("đã gửi");
        call.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
                ArrayList<Package> list = (ArrayList<Package>) response.body();
                listSended.addAll(list);
                rvMnSended.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rvMnSended.setLayoutManager(layoutManager);
                adapter = new MnSendingAdapter(getContext(), listSended);
                rvMnSended.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {

            }
        });
    }
}
