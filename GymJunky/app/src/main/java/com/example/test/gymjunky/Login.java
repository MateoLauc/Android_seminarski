package com.example.test.gymjunky;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.gymjunky.Api.LoginApi;
import com.example.test.gymjunky.Api.modeli.Igrac;
import com.example.test.gymjunky.Helperi.MyBaseActivity;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.Helperi.Sesija;

public class Login extends MyBaseActivity {
    Toolbar t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_prijava= (Button) findViewById(R.id.btn_Prijava);
        final EditText username_et= (EditText) findViewById(R.id.korisnickoIme_input);
        final EditText password_et= (EditText) findViewById(R.id.password_input);
        final TextView registracija_tv= (TextView) findViewById(R.id.Registracija_tv);
        registracija_tv.setPaintFlags(registracija_tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        t = (Toolbar) findViewById(R.id.nav_action);
        t.setTitleMarginStart(120);
        t.setTitle("Prijava");

        btn_prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logiranje
                MyRunnable<Igrac> x= new MyRunnable<Igrac>() {
                    @Override
                    public void run(Igrac result)
                    {
                        if (result==null)
                        {
                            Toast.makeText(Login.this, "Neispravni podaci za prijavu!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Sesija.setOdabraniKorisnik(result);
                            Intent intent= new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                };

                String username = username_et.getText().toString();
                String password = password_et.getText().toString();

                if(username.length()==0 || password.length()==0){
                    Toast.makeText(Login.this, "Korisnicko ime i lozinka moraju biti unešeni!", Toast.LENGTH_SHORT).show();
                }
                else{
                    LoginApi.LoginUser(Login.this,x,username,password);
                }

            }
        });
    }

            public void onClick(View v) {
                Intent intent= new Intent(Login.this,RegistracijaActivity.class);
                startActivity(intent);
            }


    @Override
    public void onBackPressed() {}
    @Override
    protected void onStart() {
        super.onStart();
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        // mTogle.setDrawerIndicatorEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
