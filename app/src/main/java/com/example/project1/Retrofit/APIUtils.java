package com.example.project1.Retrofit;

import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class APIUtils {

    public static final String Base_Url = "http://192.168.1.14/project1/";

    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }

}
