package com.example.project1.Retrofit;

import com.example.project1.Objects.Package;
import com.example.project1.Objects.Sender;
import com.example.project1.Objects.Driver;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataClient {

    @GET("getPackage.php")
    Call<List<Package>> GetBK();

    @GET("getSender.php")
    Call<List<Sender>> GetNG();

    @GET("getDriver.php")
    Call<List<Driver>> GetTX();

    @GET("getPackageNV.php")
    Call<List<Package>> GetPackageCPC();

    @FormUrlEncoded
    @POST("getDriverByPhone.php")
    Call<List<Driver>> GetDrByPhone(@Field("phoneDriver") String sdtTX);

    @FormUrlEncoded
    @POST("getSenderByPhone.php")
    Call<List<Sender>> GetSenderByPhone(@Field("phoneSender") String sdtTX);

    @FormUrlEncoded
    @POST("getPackageNG.php")
    Call<List<Package>> GetdataSender(@Field("nguoiguiBKN") String sdtNG,
                                  @Field("tinhtrangBKN") String tinhtrang);

    @FormUrlEncoded
    @POST("getPackageByMn.php")
    Call<List<Package>> GetPackageByMn(@Field("tinhtrangBKN") String tinhtrang);

    @FormUrlEncoded
    @POST("getPackageTX.php")
    Call<List<Package>> GetdataTX(@Field("taixeBK") String taixe,
                                  @Field("tinhtrangBKN") String tinhtrang);

    @FormUrlEncoded
    @POST("changeState.php")
    Call<String> changeState(@Field("idBK") String id,
                             @Field("tinhtrangBK") String tinhtrang);

    @FormUrlEncoded
    @POST("updatePackageFromMNG.php")
    Call<String> updateFromNV(@Field("idBK") String id,
                              @Field("tenBK") String ten,
                              @Field("nguoiguiBK") String nguoigui,
                              @Field("taixeBK") String taixe,
                              @Field("nguoinhanBK") String noigui,
                              @Field("noinhanBK") String noinhan,
                             @Field("tinhtrangBK") String tinhtrang);

    @FormUrlEncoded
    @POST("changeNG.php")
    Call<String> changeNG(@Field("sdtNG") String sdt,
                             @Field("tienguiTC") String tiengui);

    @FormUrlEncoded
    @POST("addDriverToPackage.php")
    Call<String> addDriverToPackage(@Field("idBK") String id,
                          @Field("taixeBK") String phoneDriver);

    @FormUrlEncoded
    @POST("changeTX.php")
    Call<String> changeTX(@Field("sdtTX") String sdt,
                          @Field("doanhthuTC") String doanhthu);

    @FormUrlEncoded
    @POST("deleteDriver.php")
    Call<String> DeleteDriver(@Field("idTX") String id);

    @FormUrlEncoded
    @POST("deletePackage.php")
    Call<String> DeletePackage(@Field("idBK") String id);

    @FormUrlEncoded
    @POST("deleteSender.php")
    Call<String> DeleteSender(@Field("idNG") String id);

    @FormUrlEncoded
    @POST("updatePackage.php")
    Call<String> EditPackage(@Field("idBK") String id,
                        @Field("tenBK") String tenbk,
                        @Field("nguoinhanBK") String noigui,
                        @Field("noinhanBK") String noinhan);

    @FormUrlEncoded
    @POST("updatePackageWhenChangeDriver.php")
    Call<String> UpDatePackageWhenChangeDriver(@Field("Old") String phoneOld,
                        @Field("New") String PhoneNew);


    @FormUrlEncoded
    @POST("updatePackageWhenChangeSender.php")
    Call<String> UpDatePackageWhenChangeSender(@Field("OldS") String phoneOld,
                                               @Field("NewS") String PhoneNew);

    @FormUrlEncoded
    @POST("updateDriver.php")
    Call<String> EditTX(@Field("idTX") String id,
                        @Field("tenTX") String hoten,
                        @Field("sdtTX") String sdt,
                        @Field("matkhauTX") String matkhau,
                        @Field("doanhthuTX") String doanhthu);

    @FormUrlEncoded
    @POST("updateSender.php")
    Call<String> EditSender(@Field("idNG") String id,
                        @Field("tenNG") String hoten,
                        @Field("sdtNG") String sdt,
                        @Field("matkhauNG") String matkhau,
                        @Field("tienguiNG") String tiengui);

    @FormUrlEncoded
    @POST("insertPackage.php")
    Call<String> AddPk(@Field("tenBK") String ten,
                       @Field("nguoiguiBK") String nguoigui,
                       @Field("nguoinhanBK") String noigui,
                       @Field("noinhanBK") String noinhan,
                       @Field("tinhtrangBK") String tinhtrang);

    @FormUrlEncoded
    @POST("insertDriver.php")
    Call<String> AddTX(@Field("tenTX") String ten,
                       @Field("sdtTX") String sdt,
                       @Field("matkhauTX") String matkhau,
                       @Field("tienTX") String doanhthu);

    @FormUrlEncoded
    @POST("insertSender.php")
    Call<String> AddNG(@Field("tenNG") String ten,
                       @Field("sdtNG") String sdt,
                       @Field("matkhauNG") String matkhau,
                       @Field("tienNG") String tiengui);
}
