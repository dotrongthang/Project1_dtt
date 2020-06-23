package com.example.project1.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1.Fragments.InfoFragment;
import com.example.project1.Fragments.SendedFragment;
import com.example.project1.Fragments.SendingFragment;
import com.example.project1.Interface.ChangeInfo;
import com.example.project1.Interface.ChangeState;
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

public class SenderActivity extends AppCompatActivity implements ChangeState, ChangeInfo {

    TextView tvName, tvMoney;
    String phoneSender, idSender;
    Bundle bundle;
    Fragment fragment;
    private LinearLayout lnMoney;
    private ImageButton imgAddPk;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        AnhXa();
        GetDataFromMain();

        bundle = new Bundle();
        bundle.putString("Send", phoneSender);
        fragment = new SendingFragment();
        fragment.setArguments(bundle);
        lnMoney.setVisibility(View.INVISIBLE);
        imgAddPk.setVisibility(View.VISIBLE);
        loadFragment(fragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigationSender);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        imgAddPk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPack();
            }
        });

    }

    private void AnhXa () {
        tvName = (TextView) findViewById(R.id.tvNameSender);
        tvMoney = (TextView) findViewById(R.id.tvMoneySender);
        lnMoney = (LinearLayout) findViewById(R.id.lnMoney);
        imgAddPk = (ImageButton) findViewById(R.id.imgAddPk);
        }


    private void GetDataFromMain(){
        Intent intent = getIntent();
        phoneSender = intent.getStringExtra("SDTNG").trim();
        String tenNG = "";
        String tienNG ;

        for (int i = 0; i <MainActivity.listNG.size() ; i++) {
            if(MainActivity.listNG.get(i).getSDT().equals(phoneSender.trim())){
                tenNG = MainActivity.listNG.get(i).getHoTen();
                tienNG = MainActivity.listNG.get(i).getTienGui();
                idSender = MainActivity.listNG.get(i).getID();
                tvName.setText(tenNG);
                tvMoney.setText(tienNG);
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
                    lnMoney.setVisibility(View.INVISIBLE);
                    imgAddPk.setVisibility(View.VISIBLE);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_sended:
                    fragment = new SendedFragment();
                    fragment.setArguments(bundle);
                    lnMoney.setVisibility(View.VISIBLE);
                    imgAddPk.setVisibility(View.INVISIBLE);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_user:
                    fragment = new InfoFragment();
                    fragment.setArguments(bundle);
                    lnMoney.setVisibility(View.INVISIBLE);
                    imgAddPk.setVisibility(View.INVISIBLE);
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_Sender, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onClickBackSender(View view){
        finish();
    }

    @Override
    public void ChuyenTT(int position) {

    }

    @Override
    public void EditPackage(final Package pack) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_pack);
        dialog.setCanceledOnTouchOutside(false);

        final EditText edtChangeName = (EditText) dialog.findViewById(R.id.edtChangeNamePk);
        final EditText edtChangeReceiver = (EditText) dialog.findViewById(R.id.edtChangeReceiverPk);
        final EditText edtChangeDes = (EditText) dialog.findViewById(R.id.edtChangeDesPk);
        Button btnChange = (Button) dialog.findViewById(R.id.btnChangePk);
        Button btnNoChange = (Button) dialog.findViewById(R.id.btnNoChangePk);

        edtChangeName.setText(pack.getTenBK());

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtChangeName.getText().toString().isEmpty() || edtChangeReceiver.getText().toString().isEmpty()
                        || edtChangeDes.getText().toString().isEmpty()){
                    Toast.makeText(SenderActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    editPackage(pack.getID(), edtChangeName.getText().toString(), edtChangeReceiver.getText().toString(),
                            edtChangeDes.getText().toString());
                    AppUtil.startActivity(SenderActivity.this, SenderActivity.class, "SDTNG", phoneSender);
                    dialog.dismiss();
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

    @Override
    public void Call(String phone) {

    }

    private void editPackage(String id, String name, String receiver, String to){
        DataClient editPack = APIUtils.getData();
        Call<String> call = editPack.EditPackage(id, name, receiver, to);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void addPack(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_change_pack);
        dialog.setCanceledOnTouchOutside(false);

        final EditText edtChangeName = (EditText) dialog.findViewById(R.id.edtChangeNamePk);
        final EditText edtChangeReceiver = (EditText) dialog.findViewById(R.id.edtChangeReceiverPk);
        final EditText edtChangeDes = (EditText) dialog.findViewById(R.id.edtChangeDesPk);
        final TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitleSenderChange);
        Button btnChange = (Button) dialog.findViewById(R.id.btnChangePk);
        Button btnNoChange = (Button) dialog.findViewById(R.id.btnNoChangePk);

        tvTitle.setText("Thêm mới thông tin bưu kiện");

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtChangeName.getText().toString().isEmpty() || edtChangeReceiver.getText().toString().isEmpty()
                        || edtChangeDes.getText().toString().isEmpty()){
                    Toast.makeText(SenderActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    addPackageToDB(edtChangeName.getText().toString(), phoneSender, edtChangeReceiver.getText().toString(),
                            edtChangeDes.getText().toString());
                    AppUtil.startActivity(SenderActivity.this, SenderActivity.class, "SDTNG", phoneSender);
                    dialog.dismiss();
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

    private void addPackageToDB(String name, String sender, String receiver, String to){
            DataClient addPk = APIUtils.getData();
            Call<String> callback = addPk.AddPk(name, sender, receiver, to, "chưa gửi");
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
        tvTitle.setText("Cập nhật thông tin người gửi");
        edtChangeNameDr.setText(tvName.getText().toString());

        btnChangeDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtChangeNameDr.getText().toString().isEmpty() || edtChangePhoneDr.getText().toString().isEmpty()
                        || edtChangePwDr.getText().toString().isEmpty()){
                    Toast.makeText(SenderActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    UpdateSender(idSender, edtChangeNameDr.getText().toString(), edtChangePhoneDr.getText().toString(),
                            edtChangePwDr.getText().toString(), tvMoney.getText().toString());
                    UpDatePackageWhenChangeDriver(phoneSender, edtChangePhoneDr.getText().toString());
                    phoneSender = edtChangePhoneDr.getText().toString();
                    AppUtil.startActivity(SenderActivity.this, MainActivity.class);
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

    private void UpdateSender(String id, String ten, String sdt, String matkhau, String tiengui){
        DataClient editSender = APIUtils.getData();
        Call<String> callback = editSender.EditSender(id, ten, sdt, matkhau, tiengui);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(SenderActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(SenderActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpDatePackageWhenChangeDriver(String phoneOld, String phoneNew){
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

    @Override
    public void changeInfoSender(Sender sender) {

    }

    @Override
    public void DeleteSender(final Sender sender) {

    }


}

