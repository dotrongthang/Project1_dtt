package com.example.project1.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.Fragments.InfoFragment;
import com.example.project1.Fragments.SendedFragment;
import com.example.project1.Fragments.SendingFragment;
import com.example.project1.Interface.ChangeInfo;
import com.example.project1.Interface.ChangeState;
import com.example.project1.Objects.Driver;
import com.example.project1.Objects.Package;
import com.example.project1.Objects.Sender;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.Retrofit.DataClient;
import com.example.project1.utils.AppUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverActivity extends AppCompatActivity implements ChangeState, ChangeInfo {

    String SenderM, DriverM;
    String phoneDr, idDr, phoneSender;
    Bundle bundle;
    Fragment fragment;
    TextView tvNameDriver, tvMoney;
    private static final int REQUEST_CODE_CALL_PERMISSION = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        tvNameDriver = (TextView) findViewById(R.id.tvTenTX);
        tvMoney = (TextView) findViewById(R.id.tvTienTX);

        GetDataFromMain();

        bundle = new Bundle();
        bundle.putString("Driver", phoneDr);
        fragment = new SendingFragment();
        fragment.setArguments(bundle);
        loadFragment(fragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void GetDataFromMain(){
        Intent intent = getIntent();
        phoneDr = intent.getStringExtra("SDTTX");

        for (int i = 0; i < MainActivity.listTX.size(); i++) {
            if(MainActivity.listTX.get(i).getSDT().equals(phoneDr.trim())){
                tvMoney.setText(MainActivity.listTX.get(i).getDoanhThu());
                tvNameDriver.setText(MainActivity.listTX.get(i).getHoTen());
                idDr = MainActivity.listTX.get(i).getID();
            }
        }
    }


    private void ChangeTienNG(String sdt, String tien){
        DataClient changeNG = APIUtils.getData();
        Call<String> callback = changeNG.changeNG(sdt, tien);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    private void GetTienNG(String sdtNG){
        for (int i = 0; i < MainActivity.listNG.size(); i++) {
            if(MainActivity.listNG.get(i).getSDT().equals(sdtNG.trim())){
                SenderM = MainActivity.listNG.get(i).getTienGui();
            }
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_sending:
                    fragment = new SendingFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_sended:
                    fragment = new SendedFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_user:
                    fragment = new InfoFragment();
                    fragment.setArguments(bundle);
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void ClickItem(final int position){
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Xác nhận chuyển trạng thái");
            alertDialog.setMessage("Bạn có muốn chuyển " + SendingFragment.listPackageDr.get(position).getTenBK() +" thành đã gửi không?");
            alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                GetTienNG(SendingFragment.listPackageDr.get(position).getNguoiGui());
                int tienNGFinal = Integer.parseInt(SenderM) + 50000;
                int tienTXFinal = Integer.parseInt(tvMoney.getText().toString().trim()) + 30000;
                ChangeData(SendingFragment.listPackageDr.get(position).getID(), "đã gửi");
                ChangeTienNG(SendingFragment.listPackageDr.get(position).getNguoiGui(), String.valueOf(tienNGFinal));
                ChangeMoneyDr(SendingFragment.listPackageDr.get(position).getTaiXe(), String.valueOf(tienTXFinal));
                tvMoney.setText(String.valueOf(tienTXFinal));
                DriverM = String.valueOf(tienTXFinal);
                SendingFragment.listPackageDr.remove(position);
                SendingFragment.adapter.notifyDataSetChanged();
                MainActivity.GetdataNG();
            }
        });
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    private void ChangeData(String id, String tinhtrang){
        DataClient changeBK = APIUtils.getData();
        Call<String> callback = changeBK.changeState(id, tinhtrang);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void ChangeMoneyDr(String sdt, String tien){
        DataClient changeTX = APIUtils.getData();
        Call<String> callback = changeTX.changeTX(sdt, tien);
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
    public void ChuyenTT(int position) {
        ClickItem(position);
    }

    @Override
    public void EditPackage(Package pack) {

    }

    @Override
    public void Call(String phone) {
        phoneSender = phone;
        onClickCallShop(phone);
    }

    public void onClickBackDr(View view){
        finish();
    }

    @Override
    public void changeInfo() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_info_driver);
//        dialog.setCanceledOnTouchOutside(false);

        final EditText edtChangeNameDr = (EditText) dialog.findViewById(R.id.edtChangeNameDr);
        final EditText edtChangePhoneDr = (EditText) dialog.findViewById(R.id.edtChangePhoneDr);
        final EditText edtChangePwDr = (EditText) dialog.findViewById(R.id.edtChangePwDr);
        TextView tvNote = (TextView) dialog.findViewById(R.id.tvNote);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitleChange);
        Button btnChangeDr = (Button) dialog.findViewById(R.id.btnChangeDr);
        Button btnNoChangeDr = (Button) dialog.findViewById(R.id.btnNoChangeDr);

        tvNote.setVisibility(View.VISIBLE);
        tvTitle.setText("Cập nhật thông tin tài xế");
        edtChangeNameDr.setText(tvNameDriver.getText().toString());

        btnChangeDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtChangeNameDr.getText().toString().isEmpty() || edtChangePhoneDr.getText().toString().isEmpty()
                        || edtChangePwDr.getText().toString().isEmpty()){
                    Toast.makeText(DriverActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    UpdateDriver(idDr, edtChangeNameDr.getText().toString(), edtChangePhoneDr.getText().toString(),
                            edtChangePwDr.getText().toString(), tvMoney.getText().toString());
                    UpDatePackageWhenChangeDriver(phoneDr, edtChangePhoneDr.getText().toString());
                    phoneDr = edtChangePhoneDr.getText().toString();
                    AppUtil.startActivity(DriverActivity.this, MainActivity.class);
                    dialog.dismiss();
                }
            }
        });

        btnNoChangeDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void changeInfoSender(Sender sender) {

    }

    private void UpdateDriver(String id, String ten, String sdt, String matkhau, String doanhthu){
        DataClient editTX = APIUtils.getData();
        Call<String> callback = editTX.EditTX(id, ten, sdt, matkhau, doanhthu);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(DriverActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(DriverActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
//            onClickCallShop(phoneSender);
        } else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CODE_CALL_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_CALL_PERMISSION:
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        onClickCallShop(phoneSender);
                    }
                }
                break;
        }
    }

    public void onClickCallShop(String phone){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

}
