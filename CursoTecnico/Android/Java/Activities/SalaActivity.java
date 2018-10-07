package com.example.a3ainfo.tcc;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import java.util.ArrayList;

public class SalaActivity extends AppCompatActivity {

    ArrayList<SalaDTO> arraySala;
    SalaBLL sBLL = new SalaBLL(this);
    SalaDTO sDTO = new SalaDTO();
    String[] detalhes = new String[6];
    ImageButton btnDetalhe, btnReservar;
    Spinner spinnerDia, spinnerHora;
    Bundle extras;
    String idSala, diaSelecionado, mesSelecionado;

    ArrayList<String> arrayHora_apagador = new ArrayList<>();
    ArrayList<String> arrayHora = new ArrayList<>();
    ArrayList<String> arrayDia = new ArrayList<>();


    Calendar c = Calendar.getInstance();
    java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);

        btnReservar = (ImageButton) findViewById(R.id.btnReservar);
        btnDetalhe = (ImageButton) findViewById(R.id.btnDetalhe);
        spinnerDia = (Spinner) findViewById(R.id.spinnerDia);
        spinnerHora = (Spinner) findViewById(R.id.spinnerHorario);

        diaSelecionado = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        mesSelecionado = df1.format(c.getTime());


        try {

            extras = getIntent().getExtras();
            idSala = extras.getString("id");

        } catch (Exception ex) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("ERRO NO Bundle;\n\n" + ex.toString());
            dialog.show();
        }

        try {
            carregaDados();

        } catch (Exception ex) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("ERRO NO carregaDados();\n\n" + ex.toString());
            dialog.show();
        }

        try {
            //essa ordem é crucial para que o SpinnerDia seja apagado de acordo com o SpinnerHora
            carregaArrayDia();
            carregaArrayHora();

            carregaSpinnerDia();
            carregaSpinnerHora();


        } catch (Exception ex) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("ERRO NOS SPINNERS;\n\n" + ex.toString());
            dialog.show();
        }


        spinnerDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //verifica os horários disponíveis
                String diaSelecionado = spinnerDia.getSelectedItem().toString();
                arrayHora.removeAll(sBLL.verificaHoraReserva(idSala, diaSelecionado));

                //inicializa o spinner de Hora
                ArrayAdapter adapterHora = new ArrayAdapter(SalaActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayHora);
                adapterHora.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spinnerHora.setAdapter(adapterHora);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        /*
        //deleta o dia, se não houverem horários disponíveis
        if (spinnerHora.getSelectedItem() == null) {
            arrayDia.clear();
            arrayHora.clear();
            carregaArrayDia();
            carregaArrayHora();

            //deleta o dia, se não houverem horários disponíveis
            arrayDia.removeAll(sBLL.verificaDiaReserva(idSala));
            carregaSpinnerDia();
            carregaSpinnerHora();



        }
        */


        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    sDTO.setDia(spinnerDia.getSelectedItem().toString());
                    sDTO.setHora(spinnerHora.getSelectedItem().toString());
                } catch (Exception ex) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SalaActivity.this);
                    dialog.setMessage("ERRO NOS SPINNERS, getSelectedItem().toString());\n\n" + ex.toString());
                    dialog.show();
                }


                try {
                    sBLL.reservarSala(idSala, sDTO);
                    Toast.makeText(SalaActivity.this, "SALA RESERVADA!", Toast.LENGTH_SHORT).show();
                    SalaActivity.this.finish();

                } catch (Exception ex) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(SalaActivity.this);
                    dialog.setMessage("ERRO NO reservarSala();\n\n" + ex.toString());
                    dialog.show();

                }


            }
        });

        btnDetalhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(SalaActivity.this);
                dialog.setMessage("Nome: " + detalhes[0] + "\n" +
                        "Mídia: " + detalhes[1] + "\n" +
                        "Tamanho: " + detalhes[2] + "\n" +
                        "Cadeiras/Mesas: " + detalhes[3] + "\n" +
                        "Refrigeração: " + detalhes[4] + "\n");

                dialog.show();
            }


        });


    }


    public void carregaSpinnerDia() {


        ArrayAdapter adapterDia = new ArrayAdapter(SalaActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayDia);
        adapterDia.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDia.setAdapter(adapterDia);


    }

    public void carregaArrayDia() {

        Integer dia = Integer.parseInt(diaSelecionado);
        arrayDia.add(dia.toString() + "/" + mesSelecionado);
        Integer mes;

        if (mesSelecionado == "1" && mesSelecionado == "3" && mesSelecionado == "5" && mesSelecionado == "7" && mesSelecionado == "8" && mesSelecionado == "10" && mesSelecionado == "12") {



            for (int i = 0; i < 7; i++) {

                if (dia > 31) {
                    dia = 0;
                    mes = Integer.parseInt(mesSelecionado);
                    mes = mes + 1;
                    mesSelecionado = "0"+mes.toString();
                }

                dia = dia + 1;
                arrayDia.add(dia.toString() + "/" + mesSelecionado);

            }
        } else {




            for (int i = 0; i < 7; i++) {

                if (dia > 30) {
                    dia = 0;
                    mes = Integer.parseInt(mesSelecionado);
                    mes = mes + 1;
                    mesSelecionado = "0"+mes.toString();

                }

                dia = dia + 1;
                arrayDia.add(dia.toString() + "/" + mesSelecionado);

            }


        }

    }

    public void carregaSpinnerHora() {


        ArrayAdapter adapterHora = new ArrayAdapter(SalaActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayHora);
        adapterHora.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerHora.setAdapter(adapterHora);


    }

    public void carregaArrayHora() {

        arrayHora.add("07:00 - 07:50");
        arrayHora.add("07:50 - 08:40");
        arrayHora.add("08:40 - 09:30");
        arrayHora.add("09:50 - 10:40");
        arrayHora.add("10:40 - 11:30");
        arrayHora.add("13:20 - 14:10");
        arrayHora.add("14:10 - 15:00");
        arrayHora.add("15:00 - 15:40");
        arrayHora.add("16:00 - 16:40");
        arrayHora.add("13:20 - 15:20");
        arrayHora.add("15:50 - 17:40");
        arrayHora.add("18:50 - 20:40");
        arrayHora.add("21:00 - 22:50");
    }


    public void carregaDados() {
        Bundle extras = getIntent().getExtras();
        String idSala = extras.getString("id");

        try {
            arraySala = sBLL.listarDetalhesSala(idSala);
        } catch (Exception ex) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("ERRO NO sBLL.listarDetalhesSala(idSala);\n\n" + ex.toString());
            dialog.show();
        }


        detalhes[0] = arraySala.get(0).getNome();
        detalhes[1] = arraySala.get(0).getMidia();
        detalhes[2] = arraySala.get(0).getTamanho();
        detalhes[3] = arraySala.get(0).getMesa();
        detalhes[4] = arraySala.get(0).getRefrigera();


    }


}
