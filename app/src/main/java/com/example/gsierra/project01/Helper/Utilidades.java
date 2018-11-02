package com.example.gsierra.project01.Helper;

public class Utilidades {

    //permite cntrolar cuando selecciono el item ue muestra las pestañas.
    public static int rotacion=0;

    public static boolean isOnline(){
        try {
            return Runtime.getRuntime().exec("/system/bin/ping -c 1 www.google.com").waitFor() == 0; //  "8.8.8.8" is the server to ping
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//
//    public static boolean isConnectedToInternet(Context context) {
//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        return netInfo != null && netInfo.isConnectedOrConnecting();
//    }
}
