package com.example.project1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.Adapter.ListDriverAdapter;
import com.example.project1.Interface.ChangeInfoD;
import com.example.project1.Objects.Driver;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.Retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListDriverFragment extends Fragment {

    List<Driver> listDriver;
    ListDriverAdapter adapter;
    RecyclerView recyclerView;
    ChangeInfoD changeInfoD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_driver, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvListDriver);
        listDriver = new ArrayList<>();
        changeInfoD = (ChangeInfoD) getActivity();

        GetData();

        return view;
    }

    private void GetData(){
        listDriver.clear();
        DataClient getTX = APIUtils.getData();
        Call<List<Driver>> callback = getTX.GetTX();
        callback.enqueue(new Callback<List<Driver>>() {
            @Override
            public void onResponse(Call<List<Driver>> call, Response<List<Driver>> response) {
                ArrayList<Driver> mangTX = (ArrayList<Driver>) response.body();
                listDriver.addAll(mangTX);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new ListDriverAdapter(listDriver, getContext(), changeInfoD);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Driver>> call, Throwable t) {

            }
        });
    }
}
