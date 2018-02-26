package com.example.test.gymjunky.Api;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.test.gymjunky.Api.modeli.Igrac;
import com.example.test.gymjunky.Api.modeli.IgracProgram;
import com.example.test.gymjunky.Helperi.MyApp;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.R;
import com.example.test.gymjunky.volley.GsonRequest;
import com.example.test.gymjunky.volley.MySingleton;


public class IgracProgramApi {
    public static void ProgramiByIgrac(final Context context, final MyRunnable<IgracProgram[]> x, String KorisnikId)
    {
        final ProgressDialog dialog = new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Pristup podacima u toku..");
        dialog.show();

        String urlStr = "http://mateol.app.fit.ba/api/IgraciProgrami/ProgramiByIgrac/";
        String urlParam = ""+KorisnikId;

        String url = urlStr + urlParam;


        Response.Listener<IgracProgram[]> successListener = new Response.Listener<IgracProgram[]>() {
            @Override
            public void onResponse(IgracProgram[] response) {
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
        GsonRequest<?> gsonRequest = new GsonRequest<>(url, IgracProgram[].class, null, null, successListener, errorListener, Request.Method.GET);

        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
    public static void ObrisiPrijavu(final Context context,final MyRunnable<IgracProgram> y, String KorisnikId, String ProgramId)
    {
        final ProgressDialog dialog = new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Prijava u tijeku!");
        dialog.show();

        String urlStr = "http://mateol.app.fit.ba/api/IgraciProgrami/ObrisiPrijavu/";
        String urlParam = ""+KorisnikId+"/"+ProgramId ;

        String url = urlStr + urlParam;


        Response.Listener<IgracProgram> successListener = new Response.Listener<IgracProgram>() {
            @Override
            public void onResponse(IgracProgram response) {
                dialog.dismiss();
                Toast.makeText(context, "Prijava ukonjena!", Toast.LENGTH_SHORT).show();
                y.run(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }

        };
        GsonRequest<?> gsonRequest = new GsonRequest<>(url, IgracProgram.class, null, null, successListener, errorListener, Request.Method.GET);

        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
}
