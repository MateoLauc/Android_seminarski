package com.example.test.gymjunky;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.test.gymjunky.Api.modeli.Igrac;
import com.example.test.gymjunky.Helperi.MyApp;
import com.example.test.gymjunky.Helperi.MyBaseActivity;
import com.example.test.gymjunky.Helperi.Sesija;
import com.example.test.gymjunky.volley.GsonRequest;
import com.example.test.gymjunky.volley.PostUser;

public class KorisnickiProfil extends MyBaseActivity {
    Igrac i;
    Toolbar t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korisnicki_profil);
        i = Sesija.getLogiraniKorisnik();
        final EditText ime_et = (EditText) findViewById(R.id.ime_input);
        final EditText lozinka_et = (EditText) findViewById(R.id.lozinka_input);
        final EditText email_et = (EditText) findViewById(R.id.email_input);
        final EditText visina_et = (EditText) findViewById(R.id.visina_input);

        final EditText tezina_et = (EditText) findViewById(R.id.tezina_input);
        Button btn_spremi = (Button) findViewById(R.id.btn_Spremi);
        t = (Toolbar) findViewById(R.id.nav_action);
        t.setTitle("Uredi profil");
        ime_et.setText(i.korisnickoIme);
        lozinka_et.setText(i.lozinka);
        email_et.setText(i.email);
        visina_et.setText(i.visina.toString() + " cm");
        //visina=i.visina;
        tezina_et.setText(i.tezina.toString() + " kg");


        btn_spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean greska = false;
                // ide u bazu
                // i.visina= Integer.parseInt( visina_et.getText().toString());
                //i.tezina= Integer.parseInt( tezina_et.getText().toString());
                String[] arr = visina_et.getText().toString().split(" ");
                try {
                    i.visina = Integer.parseInt(arr[0]);
                    if (i.visina<100 || i.visina>250){
                        throw new Exception();
                    }
                    visina_et.setText(i.visina + " cm");
                } catch (Exception ex) {
                    Toast.makeText(KorisnickiProfil.this, "Visina mora biti valjana brojčana vrijednost", Toast.LENGTH_SHORT).show();
                    greska = true;
                }
                arr = tezina_et.getText().toString().split(" ");
                try {
                    i.tezina = Integer.parseInt(arr[0]);
                    if (i.tezina<0 || i.tezina>500){
                       throw new Exception();
                    }
                    tezina_et.setText(i.tezina + " kg");
                } catch (Exception ex) {
                    Toast.makeText(KorisnickiProfil.this, "Težina mora biti valjana brojčana vrijednost", Toast.LENGTH_SHORT).show();
                    greska = true;
                }

                i.korisnickoIme = ime_et.getText().toString();
                i.email = email_et.getText().toString();
                i.lozinka = lozinka_et.getText().toString();
                String url = "http://mateol.app.fit.ba/api/Igraci/";
                if (greska == false && ime_et.getText().toString().length() > 0 && lozinka_et.getText().length() > 1 && isValidEmail(email_et.getText())) {
                    PostUser.PostUser(url, i, KorisnickiProfil.this);
                }
            }
        });

    }

    public final static boolean isValidEmail(CharSequence target) {
        boolean ret = !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        if (ret == false) {
            Toast.makeText(MyApp.getContext(), "Email nije u ispravnom formatu.", Toast.LENGTH_SHORT).show();
        }
        return ret;
    }
}
