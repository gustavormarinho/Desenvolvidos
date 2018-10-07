package com.example.a3ainfo.tcc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int posicao;
    ArrayList<SalaDTO> arraySalas;
    ListView listSala;
    SalaBLL sBLL = new SalaBLL(MainActivity.this);
    SalaDTO sDTO = new SalaDTO();
    String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /************************************************************************************/

        //inicio do listView//
        listSala = (ListView) findViewById(R.id.listSalas);
        carregaNomes();


        listSala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                posicao = position;
                Intent it = new Intent(MainActivity.this, SalaActivity.class);


                ID = arraySalas.get(posicao).getId().toString();
                //Armazena o valor do id no objeto da Intent
                it.putExtra("id", ID);

                startActivity(it);

            }
        });


        listSala.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                posicao = position;
                ID = arraySalas.get(posicao).getId().toString();

                //associa o menu ao evento "onItemLongClick"
                registerForContextMenu(listSala);
                return false;

            }
        });

        if(LoginActivity.nv_acesso == 0) {
            Toast.makeText(this,"Olá, Administrador(a)" , Toast.LENGTH_SHORT).show();
        }else if(LoginActivity.nv_acesso == 1){
            Toast.makeText(this, "Olá, Professor(a)", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Bem vindo, Usuário-Mestre", Toast.LENGTH_SHORT).show();
        }


    }

    public void carregaNomes() {

        try {
            arraySalas = sBLL.listarNomesSala();
            ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arraySalas);
            listSala.setAdapter(adapter);

        } catch (Exception ex) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(ex.toString());
            dialog.show();
        }
    }

    public void carregaMidia() {

        arraySalas = sBLL.mostrarMultimidia();
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arraySalas);
        listSala.setAdapter(adapter);

    }

    public void carregaGrande() {

        arraySalas = sBLL.mostrarGrande();
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arraySalas);
        listSala.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaNomes();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 0, 0, "ALTERAR SALA");
        menu.add(0, 1, 1, "DELETAR SALA");
        menu.add(0, 2, 2, "REMOVER RESERVAS");

        if(LoginActivity.nv_acesso == 1){
            menu.clear();
            menu.add(0,2,0, "REMOVER RESERVAS");
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            //chamar a activity de alteração
            //codAlteracao serve para diferenciar o Update de um Insert, na cadSalaActivity
            Intent it = new Intent(this, cadSalaActivity.class);
            it.putExtra("codigoAlteracao", 0);
            it.putExtra("id", ID);

                startActivity(it);

        } else if (item.getItemId() == 1) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Apagar");
            dialog.setMessage("Deseja mesmo apagar a sala?");
            dialog.setNegativeButton("Não", null);
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    sBLL.apagarSala(ID);
                    Toast.makeText(MainActivity.this, "Sala Apagada", Toast.LENGTH_SHORT).show();
                    carregaNomes();

                }
            });

                dialog.show();



        } else if (item.getItemId() == 2) {
            //VAI PRA UMA TELA COM UM LISTVIEW, MOSTRANDO TODAS SUAS ATUAIS RESERVAS
            try {
                Intent it = new Intent(this, removerReservaActivity.class);
                it.putExtra("id", ID);
                startActivity(it);
            } catch (Exception ex) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage(ex.toString());
                dialog.show();
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("Sair");
            dialog.setMessage("Deseja mesmo fazer logoff?");
            dialog.setNegativeButton("Não", null);
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {
                    // ação a ser executada ao clicar no botão
                    MainActivity.super.onBackPressed();
                }

            });
            dialog.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(LoginActivity.nv_acesso == 1){
            return false;
        }

            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addSala) {
            Intent it = new Intent(MainActivity.this, cadSalaActivity.class);
            //codigoAlteracao 0 significa que a SalaActivity irá cadastrar
            it.putExtra("codigoAlteracao", 1);

            startActivity(it);
            return true;

        } else if (id == R.id.action_user) {
            Intent it = new Intent(MainActivity.this, UserActivity.class);
            startActivity(it);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Multimidia) {
            // Intent it = new Intent(MainActivity.this, addUser_Activity.class);
            // startActivity(it);

            // Mostra as salas multimídias
            carregaMidia();

        } else if (id == R.id.nav_Grandes) {

            //  Intent it = new Intent(MainActivity.this,UserActivity.class);
            //  startActivity(it);

            // Mostra apenas as salas grandes
            carregaGrande();

        } else if (id == R.id.nav_Todas) {

            // Mostra todas as salas
            carregaNomes();


        } else if (id == R.id.nav_Info) {
            Intent it = new Intent(MainActivity.this, infoActivity.class);
            startActivity(it);


        } else if (id == R.id.nav_Logoff) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Sair");
            dialog.setMessage("Deseja mesmo fazer logoff?");
            dialog.setNegativeButton("Não", null);
            dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

            dialog.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
