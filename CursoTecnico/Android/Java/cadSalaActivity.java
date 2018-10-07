package com.example.a3ainfo.tcc;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cadSalaActivity extends AppCompatActivity {

    int verificador;

    TextView textview_abaixobutton, txtSeekBar;
    EditText txtCadnome;
    ImageButton btnCadsala;
    RadioButton rbPequena, rbGrande;
    CheckBox cbTelevisao, cbProjetor, cbAr, cbVentilador;
    SeekBar sbMesa;
    SalaBLL sBLL = new SalaBLL(this);
    SalaDTO sDTO = new SalaDTO();
    Bundle extras;
    String idSala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadsala);




        txtSeekBar = (TextView) findViewById(R.id.txtSeekBar);
        textview_abaixobutton = (TextView) findViewById(R.id.textview_abaixobutton);
        txtCadnome = (EditText) findViewById(R.id.txtCadnome);
        btnCadsala = (ImageButton) findViewById(R.id.btnCadsala);
        rbPequena = (RadioButton) findViewById(R.id.rbPequena);
        rbGrande = (RadioButton) findViewById(R.id.rbGrande);
        cbTelevisao = (CheckBox) findViewById(R.id.cbTelevisao);
        cbProjetor = (CheckBox) findViewById(R.id.cbProjetor);
        cbAr = (CheckBox) findViewById(R.id.cbAr);
        cbVentilador = (CheckBox) findViewById(R.id.cbVentilador);
        sbMesa = (SeekBar) findViewById(R.id.sbMesa);

        Bundle bundle = getIntent().getExtras();
        verificador = bundle.getInt("codigoAlteracao");

        if (verificador == 1) {
            textview_abaixobutton.setText("CADASTRAR");
        } else {
            textview_abaixobutton.setText("ATUALIZAR");
            extras = getIntent().getExtras();
            idSala = extras.getString("id");

            ArrayList<SalaDTO> arraySala =  sBLL.listarDetalhesSala(idSala);
            txtCadnome.setText(arraySala.get(0).getNome());


        }

        rbPequena.setChecked(true);



        txtSeekBar.setText(sbMesa.getProgress() + "/" + sbMesa.getMax());

        sbMesa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtSeekBar.setText(sbMesa.getProgress() + "/" + sbMesa.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                txtSeekBar.setText(sbMesa.getProgress() + "/" + sbMesa.getMax());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txtSeekBar.setText(sbMesa.getProgress() + "/" + sbMesa.getMax());
            }
        });


        btnCadsala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (verificador == 1) {

                    try {

                        //Nome
                        sDTO.setNome(txtCadnome.getText().toString().toUpperCase());

                        //Tamanho
                        if (rbPequena.isChecked()) {
                            sDTO.setTamanho(rbPequena.getText().toString());
                        } else if (rbGrande.isChecked()) {
                            sDTO.setTamanho(rbGrande.getText().toString());
                        }

                        //Multimídia
                        if (cbProjetor.isChecked() && cbTelevisao.isChecked()) {
                            sDTO.setMidia(cbProjetor.getText().toString() + "/TV");
                        } else if (cbProjetor.isChecked()) {
                            sDTO.setMidia(cbProjetor.getText().toString());
                        } else if (cbTelevisao.isChecked()) {
                            sDTO.setMidia("TV");
                        } else {
                            sDTO.setMidia("N/A");
                        }

                        //Refrigeração
                        if (cbVentilador.isChecked() && cbAr.isChecked()) {
                            sDTO.setRefrigera(cbVentilador.getText().toString() + "/AC");
                        } else if (cbVentilador.isChecked()) {
                            sDTO.setRefrigera(cbVentilador.getText().toString());
                        } else if (cbAr.isChecked()) {
                            sDTO.setRefrigera("AC");
                        } else {
                            sDTO.setRefrigera("N/A");
                        }

                        //Cadeiras/Mesas
                        sDTO.setMesa(String.valueOf(sbMesa.getProgress()));

                    } catch (Exception ex) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(cadSalaActivity.this);
                        dialog.setMessage(ex.toString());
                        dialog.show();
                    }


                    try {
                        sBLL.inserirSala(sDTO);
                        Toast.makeText(cadSalaActivity.this, "Sala Cadastrada com Sucesso!", Toast.LENGTH_LONG).show();
                        cadSalaActivity.this.finish();

                    } catch (SQLiteException ex) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(cadSalaActivity.this);
                        dialog.setMessage("Erro ao inserirSala:\n\n" + ex.toString());
                        dialog.show();
                    } catch (Exception ex) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(cadSalaActivity.this);
                        dialog.setMessage("Erro inesperado:\n\n" + ex.toString());
                        dialog.show();
                    }
                } else {





                    sDTO.setNome(txtCadnome.getText().toString());

                    //Tamanho
                    if (rbPequena.isChecked()) {
                        sDTO.setTamanho(rbPequena.getText().toString());
                    } else if (rbGrande.isChecked()) {
                        sDTO.setTamanho(rbGrande.getText().toString());
                    }

                    //Multimídia
                    if (cbProjetor.isChecked() && cbTelevisao.isChecked()) {
                        sDTO.setMidia(cbProjetor.getText().toString() + "/TV");
                    } else if (cbProjetor.isChecked()) {
                        sDTO.setMidia(cbProjetor.getText().toString());
                    } else if (cbTelevisao.isChecked()) {
                        sDTO.setMidia("TV");
                    } else {
                        sDTO.setMidia("N/A");
                    }

                    //Refrigeração
                    if (cbVentilador.isChecked() && cbAr.isChecked()) {
                        sDTO.setRefrigera(cbVentilador.getText().toString() + "/AC");
                    } else if (cbVentilador.isChecked()) {
                        sDTO.setRefrigera(cbVentilador.getText().toString());
                    } else if (cbAr.isChecked()) {
                        sDTO.setRefrigera("AC");
                    } else {
                        sDTO.setRefrigera("N/A");
                    }

                    //Cadeiras/Mesas
                    sDTO.setMesa(String.valueOf(sbMesa.getProgress()));

                    try {
                        sBLL.atualizarSala(sDTO, idSala);
                        cadSalaActivity.this.finish();
                    } catch (Exception ex) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(cadSalaActivity.this);
                        dialog.setMessage("Erro no atualizarSala():\n\n" + ex.toString());
                        dialog.show();
                    }
                }

            }
        });

    }
}
