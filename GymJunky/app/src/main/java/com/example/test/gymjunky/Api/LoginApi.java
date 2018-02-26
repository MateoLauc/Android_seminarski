package com.example.test.gymjunky.Api;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.test.gymjunky.Api.modeli.Igrac;
import com.example.test.gymjunky.Api.modeli.Program;
import com.example.test.gymjunky.Helperi.MyApp;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.R;
import com.example.test.gymjunky.volley.GsonRequest;
import com.example.test.gymjunky.volley.MySingleton;

public class LoginApi {
    public static void LoginUser(final Context context, final MyRunnable<Igrac> x,String username,String password)
    {
        final ProgressDialog dialog = new ProgressDialog(context,R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Prijava u tijeku!");
        dialog.show();

        String urlStr = "http://mateol.app.fit.ba/api/Igraci/LoginUser/";
        String urlParam = ""+username+"/"+password;

        String url = urlStr + urlParam;


        Response.Listener<Igrac> successListener = new Response.Listener<Igrac>() {
            @Override
            public void onResponse(Igrac response) {
                dialog.dismiss();
                x.run(response); // ovo je onaj objekt poslan iz main activity-a i koristimo njegovu run funkciju..
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }

        };
        GsonRequest<?> gsonRequest = new GsonRequest<>(url, Igrac.class, null, null, successListener, errorListener, Request.Method.GET);

        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
}
