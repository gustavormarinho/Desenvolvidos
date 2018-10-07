package com.example.a3ainfo.tcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class addUser_Activity extends AppCompatActivity {

    int var;
    Bundle bundle;
    String id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        final EditText txtNomeUser, txtEmail, txtTelUser, txtUserUser, txtSenhaUser;
        final RadioButton rdAdm, rdProf;
        final TextView txtDescUser;
        ImageButton btnCadUser;

        final UserDTO uDTO = new UserDTO();
        final SalaBLL sBLL = new SalaBLL(addUser_Activity.this);

        txtNomeUser = (EditText) findViewById(R.id.txtNomeUser);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtTelUser = (EditText) findViewById(R.id.txtTelUser);
        txtUserUser = (EditText) findViewById(R.id.txtUserUser);
        txtSenhaUser = (EditText) findViewById(R.id.txtSenhaUser);
        rdAdm = (RadioButton) findViewById(R.id.rdAdm);
        rdProf = (RadioButton) findViewById(R.id.rdProf);
        btnCadUser = (ImageButton) findViewById(R.id.btnCadUser);
        txtDescUser = (TextView) findViewById(R.id.txtDescUser);

        rdProf.requestFocus();

        bundle = getIntent().getExtras();
        var = bundle.getInt("Verifica");

        if (var != 666 ){
            id_user = bundle.getString("ID_USUARIO");

            txtDescUser.setText("ALTERAR");

            sBLL.listarUser(id_user,uDTO);

            txtNomeUser.setText(uDTO.getNomeUser());
            txtEmail.setText(uDTO.getEmailUser());
            txtTelUser.setText(uDTO.getTelUser());
            txtUserUser.setText(uDTO.getUserUser());
            txtSenhaUser.setText(uDTO.getSenhaUser());
        }

        rdProf.setChecked(true);


        btnCadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    uDTO.setNomeUser(txtNomeUser.getText().toString());
                    uDTO.setEmailUser(txtEmail.getText().toString());
                    uDTO.setTelUser(txtTelUser.getText().toString());
                    uDTO.setUserUser(txtUserUser.getText().toString());
                    uDTO.setSenhaUser(txtSenhaUser.getText().toString());

                    if (rdAdm.isChecked()) {
                        uDTO.setAcessoUser(0);
                    } else if (rdProf.isChecked()) {
                        uDTO.setAcessoUser(1);
                    }


                    if(var != 1) {
                        sBLL.cadUser(uDTO);
                        Toast.makeText(addUser_Activity.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                        addUser_Activity.this.finish();
                    }
                    else if (var == 1){

                        sBLL.altUser(id_user,uDTO);
                        Toast.makeText(addUser_Activity.this, "Informações Alteradas com Sucesso!", Toast.LENGTH_SHORT).show();
                        addUser_Activity.this.finish();
                    }




                }
        });

    }
}
