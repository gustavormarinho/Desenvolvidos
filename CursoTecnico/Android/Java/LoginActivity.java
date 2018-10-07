package com.example.a3ainfo.tcc;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText TXTlogin, TXTsenha;
    Button  btnAPAGABD;
    ImageButton BTNentrar;
    public static Integer nv_acesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TXTlogin = (EditText)findViewById(R.id.txtLogin);
        TXTsenha = (EditText)findViewById(R.id.txtSenha);
        BTNentrar = (ImageButton)findViewById(R.id.btnEntrar);
        final SalaBLL sBLL = new SalaBLL(this);
        final UserDTO uDTO = new UserDTO();

            BTNentrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(TXTlogin.getText().toString().equals("adm") && TXTsenha.getText().toString().equals("123")){
                        Intent it = new Intent(LoginActivity.this,MainActivity.class);
                        nv_acesso = 3;
                        startActivity(it);
                    }
                    else{

                    try {
                        Integer logar;
                        uDTO.setUserUser(TXTlogin.getText().toString());
                        uDTO.setSenhaUser(TXTsenha.getText().toString());
                        logar = sBLL.login(uDTO);


                        if(logar == 3){
                            Toast.makeText(LoginActivity.this, "USUÁRIO OU SENHA INVÁLIDOS", Toast.LENGTH_LONG).show();
                        }
                        else {
                            nv_acesso = logar;
                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(it);
                        }
                        }catch (Exception ex){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                        dialog.setMessage(ex.toString());
                        dialog.show();
                    }






                    }


                }
            });



    }

    @Override
    protected void onResume() {
        super.onResume();
        TXTlogin.setText("");
        TXTsenha.setText("");
        TXTlogin.requestFocus();
    }


}
