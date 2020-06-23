package com.example.project1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.Adapter.NewPackageAdapter;
import com.example.project1.Interface.AddDriverToPackage;
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


public class NewPackageFragment extends Fragment {

    private RecyclerView rvNewPackage;
    public static List<Package> list;
    public static  NewPackageAdapter adapter;
    AddDriverToPackage addDriverToPackage;

    public NewPackageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_package, container, false);
        rvNewPackage = (RecyclerView) view.findViewById(R.id.rvNewPackage);
        list = new ArrayList<>();
        addDriverToPackage = (AddDriverToPackage) getActivity();

        GetData();

        return view;
    }

    private void GetData(){
        DataClient getBKCG = APIUtils.getData();
        Call<List<Package>> callback = getBKCG.GetPackageCPC();
        callback.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, Response<List<Package>> response) {
                ArrayList<Package> mangBk = (ArrayList<Package>) response.body();
                list.addAll(mangBk);
                rvNewPackage.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                rvNewPackage.setLayoutManager(layoutManager);
                adapter = new NewPackageAdapter(list, getContext(), addDriverToPackage);
                rvNewPackage.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {

            }
        });
    }
}
