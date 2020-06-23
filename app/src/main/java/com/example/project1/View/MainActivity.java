package com.example.project1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.Objects.Package;
import com.example.project1.Objects.Sender;
import com.example.project1.Objects.Driver;
import com.example.project1.R;
import com.example.project1.Retrofit.APIUtils;
import com.example.project1.utils.AppUtil;
import com.example.project1.Retrofit.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout lnChoose;

    TextView txtChonDoiTuong;
    EditText edtTaiKhoan, edtMatKhau;
    public static ArrayList<Driver> listTX;
    public static ArrayList<Sender> listNG;
    public static ArrayList<Package> listBK;
    String sdt = "";
    int Chon = 0; // biến chọn để chuyển activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        listTX = new ArrayList<>();
        listNG = new ArrayList<>();
        listBK = new ArrayList<>();
        GetdataTX();
        GetdataNG();
        GetDataBK();
    }

    private void AnhXa() {
        txtChonDoiTuong = (TextView) findViewById(R.id.tvMainChonDT);
        edtTaiKhoan = (EditText) findViewById(R.id.edtMainUser);
        edtMatKhau = (EditText) findViewById(R.id.edtMainPW);
        lnChoose = (RelativeLayout) findViewById(R.id.lnChoose);
    }

    public void onClickChoose(View view) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, lnChoose);
        popupMenu.getMenuInflater().inflate(R.menu.menu_choose_obj, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuNVQL:
                        txtChonDoiTuong.setText("Nhân viên quản lý");
                        Chon = 1;
                        break;
                    case R.id.menuNGH:
                        txtChonDoiTuong.setText("Người gửi hàng");
                        Chon = 2;
                        break;
                    case R.id.menuTX:
                        txtChonDoiTuong.setText("Tài xế");
                        Chon = 3;
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    public void onClickLogin(View view){
        boolean check = false;
        switch (Chon) {
            case 0:
                Toast.makeText(MainActivity.this, "Vui lòng chọn đối tượng!", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (edtTaiKhoan.getText().toString().equals("123") //adm, pass 123
                        && edtMatKhau.getText().toString().equals("123")) {
                    check = true;
                }
                if (!check) {
                    Toast.makeText(MainActivity.this, "Thông tin không chính xác!", Toast.LENGTH_SHORT).show();
                } else {
                    AppUtil.startActivity(MainActivity.this, ManagerActivity.class);
                }
                break;

            case 2:
                for (int i = 0; i < listNG.size(); i++) {
                    if (listNG.get(i).getSDT().equals(edtTaiKhoan.getText().toString())
                            && listNG.get(i).getMatKhau().equals(edtMatKhau.getText().toString())) {
                        check = true;
                        sdt = listNG.get(i).getSDT();
                    }
                }
                if (!check) {
                    Toast.makeText(MainActivity.this, "Thông tin không chính xác!", Toast.LENGTH_SHORT).show();
                } else {
                    AppUtil.startActivity(MainActivity.this, SenderActivity.class, "SDTNG", sdt);
                }
                break;

            case 3:
                for (int i = 0; i < listTX.size(); i++) {
                    if (listTX.get(i).getSDT().equals(edtTaiKhoan.getText().toString()) &&
                            listTX.get(i).getMatKhau().equals(edtMatKhau.getText().toString())) {
                        check = true;
                        sdt = listTX.get(i).getSDT();
                    }
                }
                if (!check) {
                    Toast.makeText(MainActivity.this, "Thông tin không chính xác!", Toast.LENGTH_SHORT).show();
                } else {
                    AppUtil.startActivity(MainActivity.this, DriverActivity.class, "SDTTX", sdt);
                }
                break;
        }
    }

    public void onClickExit(View view){
        finish();
    }

    public static void GetdataTX(){
        listTX.clear();
        DataClient getTX = APIUtils.getData();
        Call<List<Driver>> callback = getTX.GetTX();
        callback.enqueue(new Callback<List<Driver>>() {
            @Override
            public void onResponse(Call<List<Driver>> call, Response<List<Driver>> response) {
                ArrayList<Driver> mangTX = (ArrayList<Driver>) response.body();
                listTX.addAll(mangTX);
            }

            @Override
            public void onFailure(Call<List<Driver>> call, Throwable t) {

            }
        });
    }

    public static void GetdataNG(){
        DataClient getNG = APIUtils.getData();
        Call<List<Sender>> callback = getNG.GetNG();
        callback.enqueue(new Callback<List<Sender>>() {
            @Override
            public void onResponse(Call<List<Sender>> call, retrofit2.Response<List<Sender>> response) {
                ArrayList<Sender> mangNG = (ArrayList<Sender>) response.body();
                for (int i = 0; i <mangNG.size() ; i++) {
                    Sender nguoiGui = mangNG.get(i);
                    listNG.add(nguoiGui);
                }
            }
            @Override
            public void onFailure(Call<List<Sender>> call, Throwable t) {

            }
        });
    }

    public void GetDataBK(){
        DataClient getdataBK = APIUtils.getData();
        Call<List<Package>> callback = getdataBK.GetBK();
        callback.enqueue(new Callback<List<Package>>() {
            @Override
            public void onResponse(Call<List<Package>> call, retrofit2.Response<List<Package>> response) {
                ArrayList<Package> mangBk = (ArrayList<Package>) response.body();
                listBK.addAll(mangBk);
            }
            @Override
            public void onFailure(Call<List<Package>> call, Throwable t) {
            }
        });

    }
}
