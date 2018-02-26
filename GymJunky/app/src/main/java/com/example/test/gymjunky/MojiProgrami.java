package com.example.test.gymjunky;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.gymjunky.Api.IgracProgramApi;
import com.example.test.gymjunky.Api.modeli.IgracProgram;
import com.example.test.gymjunky.Helperi.MyBaseActivity;
import com.example.test.gymjunky.Helperi.MyRunnable;
import com.example.test.gymjunky.Helperi.Sesija;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MojiProgrami extends MyBaseActivity {
    Toolbar t;
    List<IgracProgram> ip= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moji_programi);
        t= (Toolbar) findViewById(R.id.nav_action);
        t.setTitle("Moji programi");
        ListView mojiProgrami= (ListView) findViewById(R.id.programiIgrac_list);
            final BaseAdapter f=new BaseAdapter() {
            @Override
            public int getCount() {
                return ip.size();
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
                //render view
                if (view==null)
                {
                    final LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    view= layoutInflater.inflate(R.layout.moji_programi_list,parent,false);
                }
                IgracProgram igracProgram= ip.get(position);
                TextView tv_naziv= (TextView) view.findViewById(R.id.naziv_Mprogram);
                TextView tv_kategorija= (TextView) view.findViewById(R.id.kategorija_Mprogram);

                tv_naziv.setText("Naziv:"+ igracProgram.programi.naziv);
                tv_kategorija.setText("Kategorija:"+ igracProgram.programi.kategorije.naziv);
                return view;
            }
            };
            mojiProgrami.setAdapter(f);
        mojiProgrami.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                IgracProgram igracProgram=ip.get(position);
                                MyRunnable<IgracProgram> y= new MyRunnable<IgracProgram>() {
                                    @Override
                                    public void run(IgracProgram result) {
                                        ip.remove(position);
                                        f.notifyDataSetChanged();
                                    }
                                };
                                IgracProgramApi.ObrisiPrijavu(MojiProgrami.this,y,Sesija.getLogiraniKorisnik().igracId.toString(),igracProgram.programId.toString());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MojiProgrami.this,R.style.AppCompatAlertDialogStyle);
                builder.setMessage("Jeste li sigurni?").setPositiveButton("DA", dialogClickListener)
                        .setNegativeButton("Ne", dialogClickListener).show();


            }
        });

        MyRunnable<IgracProgram[]> x=  new MyRunnable<IgracProgram[]>() {
            @Override
            public void run(IgracProgram[] result) {
                ip=new ArrayList<>(Arrays.asList(result));;
                f.notifyDataSetChanged();
            }
        };
        IgracProgramApi.ProgramiByIgrac(this,x, Sesija.getLogiraniKorisnik().igracId.toString());
    }
}
