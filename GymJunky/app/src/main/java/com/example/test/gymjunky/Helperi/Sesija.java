package com.example.test.gymjunky.Helperi;

import android.content.SharedPreferences;

import com.example.test.gymjunky.Api.modeli.Igrac;
import com.example.test.gymjunky.Api.modeli.Program;

/**
 * Created by lenka on 16.2.2018..
 */

public class Sesija {
    private static final String PREFS_NAME ="DatotekaZaSharedPreferences";

    public  static Igrac getLogiraniKorisnik(){

        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        String str= settings.getString("logiraniKorisnikJson","");
        if ( str.length()==0){
            return null;
        }
        final Igrac igrac= MyGson.build().fromJson(str,Igrac.class);
        return  igrac;
    }
    public static  void setOdabraniKorisnik(Igrac I){
        final String str=MyGson.build().toJson(I);

        SharedPreferences settings= MyApp.getContext().getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("logiraniKorisnikJson",str);

        editor.commit();
    }

}
