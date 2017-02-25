package ConfCompartilhada;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jhonathan on 23/02/17.
 */

public class PrefManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context mContext;

    int PRIVATE_MODE = 0;
    private static String  PREF_NAME = "OnibusTB-Bem-Vindo";
    private static String  PRIMEIRA_VEZ_ABERTO = "IsFirstTimeLaunch";

    public PrefManager(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = this.mContext.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(PRIMEIRA_VEZ_ABERTO, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch(){
        return sharedPreferences.getBoolean(PRIMEIRA_VEZ_ABERTO, true);
    }
}
