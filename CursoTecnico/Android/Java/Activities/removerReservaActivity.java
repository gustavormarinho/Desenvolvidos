package com.example.a3ainfo.tcc;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class removerReservaActivity extends AppCompatActivity {

    ListView lvReservas;
    ArrayList arrayReservados;
    SalaBLL sBLL = new SalaBLL(this);
    Bundle extras;
    String idSala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_reserva);

        lvReservas = (ListView) findViewById(R.id.lvReservas);

        //BUNDLE
        extras = getIntent().getExtras();
        idSala = extras.getString("id");


        diaReserva();


        lvReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent it = new Intent(removerReservaActivity.this, removerDiaReservaActivity.class);
                it.putExtra("id", idSala);
                it.putExtra("Dia", arrayReservados.get(position).toString());
                startActivity(it);

            }
        });


    }

    public void diaReserva() {

        //Iterator + LinkedHashSet impedirá dados reptidos


        arrayReservados = sBLL.ReservaDia(idSala);

        ArrayAdapter adapter = new ArrayAdapter(removerReservaActivity.this, android.R.layout.simple_list_item_1, arrayReservados);
        lvReservas.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        diaReserva();
        super.onResume();
    }
}
