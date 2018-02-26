package com.example.test.gymjunky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.gymjunky.Api.modeli.Igrac;
import com.example.test.gymjunky.Helperi.MyApp;
import com.example.test.gymjunky.Helperi.MyBaseActivity;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.volley.PostUser;


public class RegistracijaActivity extends MyBaseActivity {
    Igrac i = new Igrac();
    Toolbar t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);

        final EditText ime_et = (EditText) findViewById(R.id.ime_input);
        final EditText lozinka_et = (EditText) findViewById(R.id.lozinka_input);
        final EditText email_et = (EditText) findViewById(R.id.email_input);
        final EditText visina_et = (EditText) findViewById(R.id.visina_input);
        final EditText tezina_et = (EditText) findViewById(R.id.tezina_input);
        Button btn_spremi = (Button) findViewById(R.id.btn_Spremi);
        t = (Toolbar) findViewById(R.id.nav_action);
        t.setTitleMarginStart(120);
        t.setTitle("Registracija");

                btn_spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean greska = false;
                // ide u bazu
                // i.visina= Integer.parseInt( visina_et.getText().toString());
                //i.tezina= Integer.parseInt( tezina_et.getText().toString());
                if (ime_et.getText().toString().length()<4 || lozinka_et.getText().toString().length()<4){
                    Toast.makeText(RegistracijaActivity.this, "Ime i lozinka moraju imati vise od 4 znamenke", Toast.LENGTH_SHORT).show();
                }
                String[] arr = visina_et.getText().toString().split(" ");
                try {
                    i.visina = Integer.parseInt(arr[0]);
                    if (i.visina<100 || i.visina>250){
                        throw new Exception();
                    }
                    visina_et.setText(i.visina + " cm");
                } catch (Exception ex) {
                    Toast.makeText(RegistracijaActivity.this, "Visina mora biti valjana brojčana vrijednost", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RegistracijaActivity.this, "Težina mora biti valjana brojčana vrijednost", Toast.LENGTH_SHORT).show();
                    greska = true;
                }

                i.igracId = 0;
                i.korisnickoIme = ime_et.getText().toString();
                i.email = email_et.getText().toString();
                i.lozinka = lozinka_et.getText().toString();
                String url = "http://mateol.app.fit.ba/api/Igraci";
                MyRunnable<Igrac> x = new MyRunnable<Igrac>() {
                    @Override
                    public void run(Igrac result) {

                        if (result != null) {
                            Toast.makeText(RegistracijaActivity.this, "Uspjesno ste se registrirali", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegistracijaActivity.this, Login.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(RegistracijaActivity.this, "Korisnicko ime je zauzeto!", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                if (greska == false && ime_et.getText().toString().length() > 0 && lozinka_et.getText().length() > 1 && isValidEmail(email_et.getText())) {

                    PostUser.SignUser(url, i, RegistracijaActivity.this, x);
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
       // mTogle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
    public final static boolean isValidEmail(CharSequence target) {
        boolean ret = !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        if (ret == false) {
            Toast.makeText(MyApp.getContext(), "Email nije u ispravnom formatu.", Toast.LENGTH_SHORT).show();
        }
        return ret;
    }
}
