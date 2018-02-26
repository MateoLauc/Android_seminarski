package com.example.test.gymjunky.volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.test.gymjunky.Api.modeli.Igrac;
import com.example.test.gymjunky.Helperi.MyApp;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.Helperi.Sesija;
import com.example.test.gymjunky.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenka on 19.2.2018..
 */

public class PostUser {
    public static boolean PostUser(String Url, final Igrac igrac,final Context context){

        StringRequest MyStringRequest= new StringRequest(Request.Method.POST,Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //success listener
                Toast.makeText(MyApp.getContext(), "Izmjene uspješno snimljne!", Toast.LENGTH_SHORT).show();
                Sesija.setOdabraniKorisnik(igrac);
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyApp.getContext(), "Došlo je do greške!", Toast.LENGTH_SHORT).show();
                    }

                }){
            protected Map<String,String> getParams(){
                Map<String,String> MyData= new HashMap<String, String>();
                MyData.put("IgracId",igrac.igracId.toString());
                MyData.put("KorisnickoIme",igrac.korisnickoIme);
                MyData.put("Lozinka",igrac.lozinka);
                MyData.put("Email",igrac.email);
                MyData.put("Visina",igrac.visina.toString());
                MyData.put("Tezina",igrac.tezina.toString());
                return  MyData;
            }

        };

        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(MyStringRequest);
        return  true;
    }


//nova
    public static boolean SignUser(String Url, final Igrac igrac, final Context context, final MyRunnable<Igrac> x){

        StringRequest MyStringRequest= new StringRequest(Request.Method.POST,Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //success listener
                Toast.makeText(MyApp.getContext(), "Izmjene uspješno snimljne!", Toast.LENGTH_SHORT).show();
                Sesija.setOdabraniKorisnik(igrac);
                x.run(igrac);
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        x.run(null);
                    }

                }){
            protected Map<String,String> getParams(){
                Map<String,String> MyData= new HashMap<String, String>();
                MyData.put("IgracId",igrac.igracId.toString());
                MyData.put("KorisnickoIme",igrac.korisnickoIme);
                MyData.put("Lozinka",igrac.lozinka);
                MyData.put("Email",igrac.email);
                MyData.put("Visina",igrac.visina.toString());
                MyData.put("Tezina",igrac.tezina.toString());
                return  MyData;
            }

        };

        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(MyStringRequest);
        return  true;
    }
}
