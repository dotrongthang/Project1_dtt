package com.example.project1.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.Adapter.ListShopAdapter;
import com.example.project1.Interface.ChangeInfo;
import com.example.project1.Objects.Sender;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.Retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class ListShopFragment extends Fragment {

    private RecyclerView rvListShop;
    List<Sender> listSender;
    ListShopAdapter adapter;
    ChangeInfo changeInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_shop, container, false);
        rvListShop = (RecyclerView) view.findViewById(R.id.rvListShop);
        listSender = new ArrayList<>();
        changeInfo = (ChangeInfo) getActivity();
        GetData();
        return view;
    }

    private void GetData(){
        DataClient getNG = APIUtils.getData();
        Call<List<Sender>> callback = getNG.GetNG();
        callback.enqueue(new Callback<List<Sender>>() {
            @Override
            public void onResponse(Call<List<Sender>> call, retrofit2.Response<List<Sender>> response) {
                ArrayList<Sender> mangNG = (ArrayList<Sender>) response.body();
                for (int i = 0; i <mangNG.size() ; i++) {
                    Sender nguoiGui = mangNG.get(i);
                    listSender.add(nguoiGui);
                    rvListShop.hasFixedSize();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rvListShop.setLayoutManager(layoutManager);
                    adapter = new ListShopAdapter(listSender, getContext(), changeInfo);
                    rvListShop.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Sender>> call, Throwable t) {

            }
        });
    }
}
