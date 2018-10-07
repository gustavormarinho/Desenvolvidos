package com.example.a3ainfo.tcc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {

    ArrayList<UserDTO> arrayUser = new ArrayList<>();
    SalaBLL sBLL = new SalaBLL(UserActivity.this);
    UserDTO uDTO = new UserDTO();
    ListView lvUser;
    String ID_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            lvUser = (ListView) findViewById(R.id.lvUser);

            carregaLVUser();



            lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ID_User = arrayUser.get(position).getIdUser().toString();
                    sBLL.listarUser(ID_User,uDTO);

                    String acesso;
                    if (uDTO.getAcessoUser() == 0){
                        acesso = "Adm";
                    }
                    else{
                        acesso = "Prof";
                    }
                    AlertDialog.Builder dialog = new AlertDialog.Builder(UserActivity.this);
                    dialog.setTitle("Detalhes");
                    dialog.setMessage("Nome: " + uDTO.getNomeUser().toString() + "\n" +
                            "Email: " + uDTO.getEmailUser().toString() + "\n" +
                            "Telefone: " + uDTO.getTelUser().toString() + "\n" +
                            "Função: " + acesso + "\n" +
                            "Usuário: " + uDTO.getUserUser().toString() + "\n" +
                            "Senha: " + uDTO.getSenhaUser().toString() + "\n");

                    dialog.show();
                }
            });

            lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    ID_User = arrayUser.get(position).getIdUser().toString();
                    //associa o menu ao evento "onItemLongClick"
                    registerForContextMenu(lvUser);


                    return false;
                }
            });




        } catch (Exception ex) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(ex.toString());
            dialog.show();

        }


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        menu.add(0, 0, 0, "ALTERAR INFORMAÇÕES");
        menu.add(0, 1, 1, "DELETAR USUÁRIO");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            Intent it = new Intent(UserActivity.this, addUser_Activity.class);
            it.putExtra("Verifica", 1);
            it.putExtra("ID_USUARIO",ID_User);
            startActivity(it);

        } else if (item.getItemId() == 1) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Apagar");
            dialog.setMessage("Deseja mesmo deletar o Usuário?");
            dialog.setNegativeButton("Não", null);
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
            try {

                sBLL.delUser(ID_User);
                Toast.makeText(UserActivity.this, "Usuário Apagado", Toast.LENGTH_SHORT).show();
                carregaLVUser();

            }catch (Exception ex) {
                AlertDialog.Builder erro = new AlertDialog.Builder(UserActivity.this);
                erro.setMessage(ex.toString());
                erro.show();
            }
                }
            });
            dialog.show();




        }


        return super.onContextItemSelected(item);

    }
        public void carregaLVUser(){
            arrayUser = sBLL.listarNomeUser();
            ArrayAdapter adapter = new ArrayAdapter(UserActivity.this, android.R.layout.simple_list_item_1, arrayUser);
            lvUser.setAdapter(adapter);
        }
    @Override
    protected void onResume() {
        arrayUser = sBLL.listarNomeUser();
        ArrayAdapter adapter = new ArrayAdapter(UserActivity.this, android.R.layout.simple_list_item_1, arrayUser);
        lvUser.setAdapter(adapter);
        super.onResume();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_addUser) {
            Intent it = new Intent(this, addUser_Activity.class);
            it.putExtra("Verifica", 666);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }


}
