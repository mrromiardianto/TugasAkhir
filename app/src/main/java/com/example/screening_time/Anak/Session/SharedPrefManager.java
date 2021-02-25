package com.example.screening_time.Anak.Session;
import android.content.Context;
import android.content.SharedPreferences;

/*
Bandar Lampung 05/11/2020
 */
public class SharedPrefManager {

    public static final String SP_LOGIN_APP = "SollarCell";
    public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_TELPON= "spTelpon";
    public static final String SP_ALAMAT = "spAlamat";
    public static final String SP_IMEI = "imei";
    public static final String SP_Prov = "prov";
    public static final String SP_Kab = "kab";
    public static final String SP_Status = "status";
    public static final String SP_SUDAH_LOGIN = "SudahLogin";

//    Quiz jawaban
    public static final String Jawaban1 = "jawaban1";
    public static final String Jawaban2 = "jawaban2";
    public static final String Jawaban3 = "jawaban3";
    public static final String Jawaban4 = "jawaban4";
    public static final String Jawaban5 = "jawaban5";
    public static final String Jawaban6 = "jawaban6";
    public static final String Jawaban7 = "jawaban7";
    public static final String Jawaban8 = "jawaban8";
    public static final String Jawaban9 = "jawaban9";
    public static final String Jawaban10= "jawaban10";

//    Quiz Jawaban benar
    public static final String JawabanBenar1 = "jawabanbenar1";
    public static final String JawabanBenar2 = "jawabanbenar2";
    public static final String JawabanBenar3 = "jawabanbenar3";
    public static final String JawabanBenar4 = "jawabanbenar4";
    public static final String JawabanBenar5 = "jawabanbenar5";
    public static final String JawabanBenar6 = "jawabanbenar6";
    public static final String JawabanBenar7 = "jawabanbenar7";
    public static final String JawabanBenar8 = "jawabanbenar8";
    public static final String JawabanBenar9 = "jawabanbenar9";
    public static final String JawabanBenar10 = "jawabanbenar10";
    /*point*/
    public static final String point1 = "point1";
    public static final String point2 ="point2";
    public static final String point3 ="point3";
    public static final String point4 ="point4";
    public static final String point5 ="point5";
    public static final String point6="point6";
    public static final String point7="point7";
    public static final String point8="point8";
    public static final String point9="point9";
    public static final String point10="point10";
    public static final String Total="0";
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;
    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_LOGIN_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }
    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }
    public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPAlamat(){
        return sp.getString(SP_ALAMAT, "");
    }

    public String getSPTelpon(){
        return sp.getString(SP_TELPON, "");
    }
    public String getSP_IMEI(){
        return sp.getString(SP_IMEI, "");
    }
    public String getSP_Prov(){
        return sp.getString(SP_Prov, "");
    }
    public String getSP_Kab(){
        return sp.getString(SP_Kab, "");
    }
    public String getSP_Status(){
        return sp.getString(SP_Status, "");
    }

    public Boolean getSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }

//    Quiz Jawaban
    public String getJawaban1(){
    return sp.getString(Jawaban1, "");
    }
    public String getJawaban2(){
        return sp.getString(Jawaban2, "");
    }
    public String getJawaban3(){
        return sp.getString(Jawaban3, "");
    }
    public String getJawaban4(){
        return sp.getString(Jawaban4, "");
    }
    public String getJawaban5(){
        return sp.getString(Jawaban5, "");
    }
    public String getJawaban6(){
        return sp.getString(Jawaban6, "");
    }
    public String getJawaban7(){
        return sp.getString(Jawaban7, "");
    }
    public String getJawaban8(){
        return sp.getString(Jawaban8, "");
    }
    public String getJawaban9(){
        return sp.getString(Jawaban9, "");
    }
    public String getJawaban10(){
        return sp.getString(Jawaban10, "");
    }
//    QuizJawabanBenar
    public String getJawabanBenar1(){
            return sp.getString(JawabanBenar1, "");
    }
    public String getJawabanBenar2(){
        return sp.getString(JawabanBenar2, "");
    }
    public String getJawabanBenar3(){
        return sp.getString(JawabanBenar3, "");
    }
    public String getJawabanBenar4(){
        return sp.getString(JawabanBenar4, "");
    }
    public String getJawabanBenar5(){
        return sp.getString(JawabanBenar5, "");
    }
    public String getJawabanBenar6(){
        return sp.getString(JawabanBenar6, "");
    }
    public String getJawabanBenar7(){
        return sp.getString(JawabanBenar7, "");
    }
    public String getJawabanBenar8(){
        return sp.getString(JawabanBenar8, "");
    }
    public String getJawabanBenar9(){
        return sp.getString(JawabanBenar9, "");
    }
    public String getJawabanBenar10(){
        return sp.getString(JawabanBenar10, "");
    }
/*
* point*/
    public String getPoint1(){
        return sp.getString(point1, "");
    }
    public String getPoint2(){
        return sp.getString(point2, "");
    }
    public String getPoint3(){
        return sp.getString(point3, "");
    }
    public String getPoint4(){
        return sp.getString(point4, "");
    }
    public String getPoint5(){
        return sp.getString(point5, "");
    }
    public String getPoint6(){
        return sp.getString(point6, "");
    }
    public String getPoint7(){
        return sp.getString(point7, "");
    }
    public String getPoint8(){
        return sp.getString(point8, "");
    }
    public String getPoint9(){
        return sp.getString(point9, "");
    }
    public String getPoint10(){
        return sp.getString(point10, "");
    }
    public String getTotal(){
        return sp.getString(Total, "");
    }
}
