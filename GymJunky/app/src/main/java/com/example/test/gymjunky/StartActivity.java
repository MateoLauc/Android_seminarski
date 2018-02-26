package com.example.test.gymjunky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.test.gymjunky.Helperi.Sesija;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    if (Sesija.getLogiraniKorisnik()==null) {
        final  Intent intent= new Intent(this,Login.class);
        startActivity(intent);
    }
    else {
        final  Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    }
}
