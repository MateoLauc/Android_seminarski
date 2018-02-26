package com.example.test.gymjunky;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.gymjunky.Api.ProgramiApi;
import com.example.test.gymjunky.Api.modeli.Kategorija;
import com.example.test.gymjunky.Api.modeli.Program;
import com.example.test.gymjunky.Helperi.MyBaseActivity;
import com.example.test.gymjunky.Helperi.MyRunnable;

import java.io.Serializable;
import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends MyBaseActivity {
    List<Program> programi= new ArrayList<>();
    private ArrayAdapter<Program> adapter;
    private ArrayList<Program> arrayList;
    Toolbar t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView programiList = (ListView) findViewById(R.id.programiList);
        t= (Toolbar) findViewById(R.id.nav_action);
        t.setTitle("Programi");
        final BaseAdapter f=  new BaseAdapter() {
            @Override
            public int getCount() {
              return programi.size();
           }

         @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {

                if (view==null){
                    final LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    view= layoutInflater.inflate(R.layout.programi_stavka,parent,false);
                }
                Program p= programi.get(position);
                TextView naziv= (TextView) view.findViewById(R.id.program_naziv);
                TextView kategorija= (TextView) view.findViewById(R.id.program_kategorija);

                naziv.setText("Naziv: "+ p.naziv);

                kategorija.setText("Kategorija: " +p.kategorije.naziv);
                return view;
            }
        };
        programiList.setAdapter(f);
        programiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Program program=programi.get(position);
            final Intent intent= new Intent(MainActivity.this,ProgramDetalji.class);
            intent.putExtra("model",  program);
            startActivity(intent);
            }
        });


        // ovaj objekt se salje u api i onda se ova funkcija run izvrsi u api-u
        MyRunnable<Program[]> x = new MyRunnable<Program[]>() {
            @Override
            public void run(Program[] result) {
                programi=new ArrayList<>(Arrays.asList(result));
                f.notifyDataSetChanged(); // popuniti listu nakon sto dobijemo podatke
            }
        };
        ProgramiApi.GetProgrami(this,x);// poziv api-a , GetProgrami je funkcija u koju stavljas parametre koji trebaju u api-u
    }

    @Override
    public void onBackPressed() {}
}
