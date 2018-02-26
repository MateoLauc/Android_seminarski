package com.example.test.gymjunky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.gymjunky.Api.ProgramiApi;
import com.example.test.gymjunky.Api.modeli.IgracProgram;
import com.example.test.gymjunky.Api.modeli.Program;
import com.example.test.gymjunky.Helperi.MyBaseActivity;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.Helperi.Sesija;

public class ProgramDetalji extends MyBaseActivity {
    Program p;
    Toolbar t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_detalji);
        p= (Program) getIntent().getSerializableExtra("model");
        TextView naziv= (TextView) findViewById(R.id.Naziv_txt);
        TextView opis= (TextView) findViewById(R.id.opis_text);
        TextView vrijeme= (TextView) findViewById(R.id.vrijeme_txt);
        TextView brojTreninga= (TextView) findViewById(R.id.brojTreninga_txt);
        TextView kategorija= (TextView) findViewById(R.id.kategorija_txt);
        Button btn_pohrani=(Button) findViewById(R.id.btn_pridruzivanje);
        t= (Toolbar) findViewById(R.id.nav_action);
        t.setTitle("Detalji");
        naziv.setText(p.naziv);
        opis.setText(p.opis);
        brojTreninga.setText(p.brojTreninga);
        vrijeme.setText(p.vrijemeTrajanja);
        kategorija.setText(p.kategorije.naziv);
        btn_pohrani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyRunnable<IgracProgram> x= new MyRunnable<IgracProgram>() {
                    @Override
                    public void run(IgracProgram result) {
                        if (result!=null)
                            Toast.makeText(ProgramDetalji.this, "Uspješno ste se pretplatili!", Toast.LENGTH_SHORT).show();
                    }
                };
                ProgramiApi.PrijavaPrograma(ProgramDetalji.this,x, Sesija.getLogiraniKorisnik().igracId,p.programId);
            }
        });
    }

}
