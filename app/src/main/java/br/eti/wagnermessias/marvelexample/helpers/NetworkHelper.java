package br.eti.wagnermessias.marvelexample.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

public class NetworkHelper {
    public static boolean verificaConexao(@NonNull Context contexto) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
