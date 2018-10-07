package com.example.a3ainfo.tcc;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class removerDiaReservaActivity extends AppCompatActivity {

    String Dia, idSala;
    ArrayList arrayHorario;
    SalaBLL sBLL = new SalaBLL(this);
    ListView lvHorario;
    Bundle extras;
    int posicao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_dia_reserva);

        extras = getIntent().getExtras();
        idSala = extras.getString("id");
        Dia = extras.getString("Dia");

        lvHorario = (ListView)findViewById(R.id.lvHorario);

        horaReserva();

        lvHorario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicao = position;
                AlertDialog.Builder dialog = new AlertDialog.Builder(removerDiaReservaActivity.this);
                dialog.setTitle("Remover Reserva");
                dialog.setMessage("Deseja mesmo retirar essa reserva?");
                dialog.setNegativeButton("Não", null);
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sBLL.retirarReserva(idSala,Dia,arrayHorario.get(posicao).toString());
                        Toast.makeText(removerDiaReservaActivity.this, "RESERVA REMOVIDA", Toast.LENGTH_SHORT).show();
                        horaReserva();

                    }
                });

                dialog.show();


            }
        });

    }

    public void horaReserva() {


        arrayHorario = sBLL.ReservasHora(idSala, Dia);
        ArrayAdapter adapter = new ArrayAdapter(removerDiaReservaActivity.this, android.R.layout.simple_list_item_1, arrayHorario);
        lvHorario.setAdapter(adapter);

    }
}
