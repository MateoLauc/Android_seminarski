package com.example.test.gymjunky.Helperi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.test.gymjunky.KorisnickiProfil;
import com.example.test.gymjunky.Login;
import com.example.test.gymjunky.MainActivity;
import com.example.test.gymjunky.MojiProgrami;
import com.example.test.gymjunky.R;

public class MyBaseActivity extends AppCompatActivity {
    public DrawerLayout getmDrawerLayout() {
        return mDrawerLayout;
    }

    public DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mTogle;
    protected Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mToolbar= (Toolbar) findViewById(R.id.nav_action);

        setSupportActionBar(mToolbar);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.activity_main);
        mTogle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mTogle);
        mTogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nv = (NavigationView)findViewById(R.id.nv1);

        //dodavanje header-a
        if (nv.getHeaderCount()<1) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.user_section, null);
            TextView tv_imeprezime = (TextView) view.findViewById(R.id.Ime_korisnika);
            TextView tv_email = (TextView) view.findViewById(R.id.email_korisnika);
            if (Sesija.getLogiraniKorisnik() != null) {
                tv_imeprezime.setText(Sesija.getLogiraniKorisnik().korisnickoIme);
                tv_email.setText(Sesija.getLogiraniKorisnik().email);
            }

            nv.addHeaderView(view);
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case(R.id.Programi):
                        Intent in1 = new Intent(MyApp.getContext(),MainActivity.class);
                        startActivity(in1);
                        break;
                    case(R.id.Odjava):
                        Sesija.setOdabraniKorisnik(null);
                        Intent in2 = new Intent(MyApp.getContext(),Login.class);
                        startActivity(in2);
                        break;
                    case(R.id.UrediProfil):
                        Intent in3= new Intent(MyApp.getContext(),KorisnickiProfil.class);
                        startActivity(in3);
                        break;
                    case(R.id.MojiProgrami):
                        Intent in4= new Intent(MyApp.getContext(),MojiProgrami.class);
                        startActivity(in4);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mTogle.onOptionsItemSelected(item))
            return  true;
        return super.onOptionsItemSelected(item);
    }
}
