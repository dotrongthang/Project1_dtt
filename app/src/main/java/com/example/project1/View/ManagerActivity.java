package com.example.project1.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.Adapter.ChooseDriverAdapter;
import com.example.project1.Fragments.ListDriverFragment;
import com.example.project1.Fragments.ListShopFragment;
import com.example.project1.Fragments.MnSendedFragment;
import com.example.project1.Fragments.MnSendingFragment;
import com.example.project1.Fragments.NewPackageFragment;
import com.example.project1.Interface.AddDriverToPackage;
import com.example.project1.Interface.ChangeInfo;
import com.example.project1.Interface.ChangeInfoD;
import com.example.project1.Objects.Driver;
import com.example.project1.Objects.Package;
import com.example.project1.Objects.Sender;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.Retrofit.DataClient;
import com.example.project1.utils.AppUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerActivity extends AppCompatActivity implements AddDriverToPackage, ChangeInfo, ChangeInfoD {

    Fragment fragment;
    final int KEY_SENDER = 21;
    final int KEY_SENDER_ADD = 211;
    final int KEY_DRIVER = 22;
    final int KEY_DRIVER_ADD = 212;
    int ob, pos, add; //get index package
    String idPack, oldPhone, oldPhoneS;
    Dialog dialog;
    ImageView imageAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        imageAdd = (ImageView) findViewById(R.id.imgAdd);

        Intent intent = getIntent();
        ob = intent.getIntExtra("key", -9999);
        if (ob == KEY_SENDER){
            fragment = new ListShopFragment();
            add = KEY_SENDER_ADD;
            loadFragment(fragment);
        } else if (ob == KEY_DRIVER){
            fragment = new ListDriverFragment();
            add = KEY_DRIVER_ADD;
            loadFragment(fragment);
        }else {
            imageAdd.setVisibility(View.INVISIBLE);
            fragment = new NewPackageFragment();
            loadFragment(fragment);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_mn);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });
    }

    private void AnhXa(){
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_noDr_mn:
                    fragment = new NewPackageFragment();
                    imageAdd.setVisibility(View.INVISIBLE);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_sending_mn:
                    fragment = new MnSendingFragment();
                    imageAdd.setVisibility(View.INVISIBLE);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_sended_mn:
                    fragment = new MnSendedFragment();
                    imageAdd.setVisibility(View.INVISIBLE);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_sender_mn:
                    fragment = new ListShopFragment();
                    imageAdd.setVisibility(View.VISIBLE);
                    add = KEY_SENDER_ADD;
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_driver:
                    fragment = new ListDriverFragment();
                    imageAdd.setVisibility(View.VISIBLE);
                    add = KEY_DRIVER_ADD;
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_manager, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onClickBackMn(View view){
        AppUtil.startActivity(ManagerActivity.this, MainActivity.class);
        finish();
    }

    @Override
    public void addDriver(Package pack, int position) {
        idPack = pack.getID();
        pos = position;
        dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_driver_to_package);
//        dialog.setCanceledOnTouchOutside(false);

        RecyclerView rvDriver = (RecyclerView) dialog.findViewById(R.id.rvChooseDriver);
        rvDriver.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDriver.setLayoutManager(layoutManager);
        ChooseDriverAdapter adapter = new ChooseDriverAdapter(this, MainActivity.listTX, this);
        rvDriver.setAdapter(adapter);
        dialog.show();
    }

    @Override
    public void sendData(Driver driver) {
        addDriver(idPack, driver.getSDT());
        dialog.dismiss();
        NewPackageFragment.list.remove(pos);
        NewPackageFragment.adapter.notifyDataSetChanged();
    }

    public void addDriver(String idPackage, String phoneDriver){
        DataClient addDriver = APIUtils.getData();
        Call<String> call = addDriver.addDriverToPackage(idPackage, phoneDriver);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    @Override
    public void changeInfo() {

    }

    @Override
    public void changeInfoSender(final Sender sender) {
        oldPhoneS = sender.getSDT();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_info_driver);

        final EditText edtChangeName = (EditText) dialog.findViewById(R.id.edtChangeNameDr);
        final EditText edtChangePhone = (EditText) dialog.findViewById(R.id.edtChangePhoneDr);
        final EditText edtChangePw = (EditText) dialog.findViewById(R.id.edtChangePwDr);
        final EditText edtChangeMoney = (EditText) dialog.findViewById(R.id.edtChangeMoney);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitleChange);
        Button btnChange = (Button) dialog.findViewById(R.id.btnChangeDr);
        Button btnNoChange = (Button) dialog.findViewById(R.id.btnNoChangeDr);

        tvTitle.setText("Cập nhật thông tin người gửi");
        edtChangeName.setText(sender.getHoTen());
        edtChangeMoney.setVisibility(View.VISIBLE);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtChangeName.getText().toString().isEmpty() || edtChangePhone.getText().toString().isEmpty()
                || edtChangePw.getText().toString().isEmpty() || edtChangeMoney.getText().toString().isEmpty()){
                    Toast.makeText(ManagerActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                EditSender(sender.getID(), edtChangeName.getText().toString(), edtChangePhone.getText().toString(),
                        edtChangePw.getText().toString(), edtChangeMoney.getText().toString());
                UpDatePackageWhenChangeSender(oldPhoneS, edtChangePhone.getText().toString());
                dialog.dismiss();
                AppUtil.startActivity(ManagerActivity.this, ManagerActivity.class, KEY_SENDER);
            }
        });

        btnNoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void DeleteSender(final Sender sender) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Bạn có chắc chắn xóa người gửi " +sender.getHoTen() + " không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteSender(sender.getID());
                AppUtil.startActivity(ManagerActivity.this, ManagerActivity.class, KEY_SENDER);
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();

    }

    public void DeleteSender(String id){
        DataClient DeleteS = APIUtils.getData();
        Call<String> call = DeleteS.DeleteSender(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void EditSender(String id, String ten, String sdt, String matkhau, String tiengui){
        DataClient editNG = APIUtils.getData();
        Call<String> callback = editNG.EditSender(id, ten, sdt, matkhau, tiengui);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    @Override
    public void changeInfoDriver(final Driver driver) {
        oldPhone = driver.getSDT();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_info_driver);

        final EditText edtChangeName = (EditText) dialog.findViewById(R.id.edtChangeNameDr);
        final EditText edtChangePhone = (EditText) dialog.findViewById(R.id.edtChangePhoneDr);
        final EditText edtChangePw = (EditText) dialog.findViewById(R.id.edtChangePwDr);
        final EditText edtChangeMoney = (EditText) dialog.findViewById(R.id.edtChangeMoney);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitleChange);
        Button btnChange = (Button) dialog.findViewById(R.id.btnChangeDr);
        Button btnNoChange = (Button) dialog.findViewById(R.id.btnNoChangeDr);

        edtChangeName.setText(driver.getHoTen());
        edtChangeMoney.setVisibility(View.VISIBLE);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtChangeName.getText().toString().isEmpty() || edtChangePhone.getText().toString().isEmpty()
                        || edtChangePw.getText().toString().isEmpty() || edtChangeMoney.getText().toString().isEmpty()){
                    Toast.makeText(ManagerActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                EditDriver(driver.getID(), edtChangeName.getText().toString(), edtChangePhone.getText().toString(),
                        edtChangePw.getText().toString(), edtChangeMoney.getText().toString());
                UpDatePackageWhenChangeDriver(oldPhone, edtChangePhone.getText().toString());
                dialog.dismiss();
                AppUtil.startActivity(ManagerActivity.this, ManagerActivity.class, KEY_DRIVER);
            }
        });

        btnNoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void DeleteDriver(final Driver driver) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Bạn có chắc chắn xóa tài xế " + driver.getHoTen() + " không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteDriverDB(driver.getID());
                AppUtil.startActivity(ManagerActivity.this, ManagerActivity.class, KEY_DRIVER);
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    public void DeleteDriverDB(String id){
        DataClient DeleteS = APIUtils.getData();
        Call<String> call = DeleteS.DeleteDriver(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void EditDriver(String id, String ten, String sdt, String matkhau, String doanhthu){
        DataClient editTX = APIUtils.getData();
        Call<String> callback = editTX.EditTX(id, ten, sdt, matkhau, doanhthu);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void UpDatePackageWhenChangeSender(String phoneOld, String phoneNew){
        DataClient ChangePackage = APIUtils.getData();
        Call<String> call = ChangePackage.UpDatePackageWhenChangeSender(phoneOld, phoneNew);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA", "Thành công");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("AAA", "Thất bại");
            }
        });
    }

    private void UpDatePackageWhenChangeDriver(String phoneOld, String phoneNew){
        DataClient ChangePackage = APIUtils.getData();
        Call<String> call = ChangePackage.UpDatePackageWhenChangeDriver(phoneOld, phoneNew);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("AAA", "Thành công");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("AAA", "Thất bại");
            }
        });
    }

    private void addPerson(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_info_driver);

        final EditText edtChangeName = (EditText) dialog.findViewById(R.id.edtChangeNameDr);
        final EditText edtChangePhone = (EditText) dialog.findViewById(R.id.edtChangePhoneDr);
        final EditText edtChangePw = (EditText) dialog.findViewById(R.id.edtChangePwDr);
        final EditText edtChangeMoney = (EditText) dialog.findViewById(R.id.edtChangeMoney);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitleChange);
        Button btnChange = (Button) dialog.findViewById(R.id.btnChangeDr);
        Button btnNoChange = (Button) dialog.findViewById(R.id.btnNoChangeDr);

        if (add == KEY_SENDER_ADD){
            tvTitle.setText("Thêm thông tin người gửi");
        }
        if (add == KEY_DRIVER_ADD){
            tvTitle.setText("Thêm thông tin tài xế");
        }

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtChangeName.getText().toString().isEmpty() || edtChangePhone.getText().toString().isEmpty()
                        || edtChangePw.getText().toString().isEmpty()){
                    Toast.makeText(ManagerActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                if (add == KEY_SENDER_ADD){
                    AddSender(edtChangeName.getText().toString(), edtChangePhone.getText().toString(),
                            edtChangePw.getText().toString());
                    dialog.dismiss();
                    AppUtil.startActivity(ManagerActivity.this, ManagerActivity.class, KEY_SENDER);
                }
                if (add == KEY_DRIVER_ADD){
                    AddDriver(edtChangeName.getText().toString(), edtChangePhone.getText().toString(),
                            edtChangePw.getText().toString());
                    dialog.dismiss();
                    AppUtil.startActivity(ManagerActivity.this, ManagerActivity.class, KEY_DRIVER);
                }
            }
        });

        btnNoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void AddSender(String ten, String sdt, String mk){
        DataClient addNG = APIUtils.getData();
        Call<String> callback = addNG.AddNG(ten, sdt, mk, "0");
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void AddDriver(String ten, String sdt, String mk){
        DataClient addTX = APIUtils.getData();
        Call<String> callback = addTX.AddTX(ten, sdt, mk, "0");
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}
