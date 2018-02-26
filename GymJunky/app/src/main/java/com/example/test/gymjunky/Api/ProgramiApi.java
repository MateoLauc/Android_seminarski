package com.example.test.gymjunky.Api;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.test.gymjunky.Api.modeli.IgracProgram;
import com.example.test.gymjunky.Api.modeli.Program;
import com.example.test.gymjunky.Helperi.MyApp;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.R;
import com.example.test.gymjunky.volley.GsonRequest;
import com.example.test.gymjunky.volley.MySingleton;

public class ProgramiApi {

    public static void GetProgrami(final Context context, final MyRunnable<Program[]> x)// ovako ide poziv za app-i
    {

        final ProgressDialog dialog = new ProgressDialog(context,R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Pristup podacima u toku..");
        dialog.show();

        String urlStr = "http://mateol.app.fit.ba/api/Programi/";
        //String urlParam = "username="+username+"&password="+password;

        String url = urlStr; //+ "?" + urlParam;

        Response.Listener<Program[]> successListener = new Response.Listener<Program[]>() {
        @Override
        public void onResponse(Program[] response) {
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

        //Gson je converter iz jsona u java klase..
        /*
        prvi parametar je url
         drugi klasa u koju zelimo casta-t podatke i treba staviti [] ako zelimo niz objekata,
         onda ovi listeneri koji se aktiviraju ukoliko se dogodi uspjeh ili greska
         */
        GsonRequest<?> gsonRequest = new GsonRequest<>(url, Program[].class, null, null, successListener, errorListener, Request.Method.GET);

        // nakon toga se ovaj gsonRequest salje u RequestQue i tu se obradjuje
        //MySingleton je klasa koja omoguci da samo jednom instanciramo objekt getRequestQueue i context
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);


    }
    public static void PrijavaPrograma(final Context context, final MyRunnable<IgracProgram> x,int KorisnikId,int ProgramId){

        final ProgressDialog dialog = new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        dialog.setMessage("Pristup podacima u toku..");
        dialog.show();
        String urlStr = "http://mateol.app.fit.ba/api/IgraciProgrami/PrijavaPrograma/";
        String urlParam = ""+KorisnikId+"/"+ProgramId;

        String url = urlStr+  urlParam;

        Response.Listener<IgracProgram> successListener = new Response.Listener<IgracProgram>() {
            @Override
            public void onResponse(IgracProgram response) {
                dialog.dismiss();
                x.run(response); // ovo je onaj objekt poslan iz main activity-a i koristimo njegovu run funkciju..
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                if (error.networkResponse.statusCode==404){
                    Toast.makeText(MyApp.getContext(), "Već ste prijavili odabrani program!" , Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(MyApp.getContext(), "Greška u komunikaciji serverom: " + error.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        };

        //Gson je converter iz jsona u java klase..
        /*
        prvi parametar je url
         drugi klasa u koju zelimo casta-t podatke i treba staviti [] ako zelimo niz objekata,
         onda ovi listeneri koji se aktiviraju ukoliko se dogodi uspjeh ili greska
         */
        GsonRequest<?> gsonRequest = new GsonRequest<>(url, IgracProgram.class, null, null, successListener, errorListener, Request.Method.GET);

        // nakon toga se ovaj gsonRequest salje u RequestQue i tu se obradjuje
        //MySingleton je klasa koja omoguci da samo jednom instanciramo objekt getRequestQueue i context
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);


    }
}
