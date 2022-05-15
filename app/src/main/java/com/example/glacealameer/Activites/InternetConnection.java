package com.example.glacealameer.Activites;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class InternetConnection {


    public static boolean checkConnection(Activity context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo wificon=connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo moblieCon=connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if((wificon!=null && wificon.isConnected())||(moblieCon!=null&&moblieCon.isConnected())){
            return true;
        }
        return false;
    }

}
