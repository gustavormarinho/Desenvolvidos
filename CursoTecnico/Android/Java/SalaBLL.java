package com.example.a3ainfo.tcc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Created by 3ainfo on 29/05/2017.
 */

public class SalaBLL extends SQLiteOpenHelper {

    public final String SALA = "TB_SALA";
    public final String RESERVA = "TB_RESERVA";
    public final String USUARIO = "TB_USER";
    public final String STATUS_RESERVADO = "RESERVADA";


    public SalaBLL(Context c) {
        super(c, "BD_TCC", null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb1, tb2, tb3;
        tb1 = "CREATE TABLE TB_SALA(" +
                "ID INTEGER PRIMARY KEY," +
                "NOME TEXT," +
                "MIDIA TEXT," +
                "TAMANHO TEXT," +
                "MESA TEXT," +
                "REFRIGERA TEXT)";

        tb2 = "CREATE TABLE TB_RESERVA(" +
                "ID INTEGER PRIMARY KEY," +
                "DIA TEXT," +
                "HORA TEXT," +
                "STATUS TEXT," +
                "ID_SALA INTEGER," +
                "ID_USER INTEGER)";

        tb3 = "CREATE TABLE TB_USER(" +
                "ID_USER INTEGER PRIMARY KEY," +
                "NOME TEXT," +
                "EMAIL TEXT," +
                "TELEFONE TEXT," +
                "USUARIO TEXT," +
                "SENHA TEXT," +
                "NV_ACESSO INTEGER)";

        db.execSQL(tb1);
        db.execSQL(tb2);
        db.execSQL(tb3);
    }


    /**
     * INÍCIO cadSalaActivity
     */

    public void inserirSala(SalaDTO dto) {
        ContentValues valor = new ContentValues();
        valor.put("NOME", dto.getNome());
        valor.put("MIDIA", dto.getMidia());
        valor.put("TAMANHO", dto.getTamanho());
        valor.put("MESA", dto.getMesa());
        valor.put("REFRIGERA", dto.getRefrigera());

        getWritableDatabase().insert(SALA, null, valor);
    }

    public void atualizarSala(SalaDTO dto, String ID) {
        ContentValues valor = new ContentValues();
        valor.put("NOME", dto.getNome());
        valor.put("MIDIA", dto.getMidia());
        valor.put("TAMANHO", dto.getTamanho());
        valor.put("MESA", dto.getMesa());
        valor.put("REFRIGERA", dto.getRefrigera());

        String[] args = {ID};

        getWritableDatabase().update(SALA, valor, "id = ?", args);
    }

    /**
     * FIM cadSalaActivity
     */


    ////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * INÍCIO SalaActivity
     */


    public ArrayList listarDetalhesSala(String ID) {

        //ARRAYLIST DO TIPO "SalaDTO"
        ArrayList<SalaDTO> arraySala = new ArrayList<>();
        String[] args = {ID};


        //COMANDO SQL "CRU"
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + SALA + " WHERE id = ?", args);

        //ENQUANTO HOUVER LINHAS NO CURSOR
        while (cursor.moveToNext()) {
            SalaDTO sDTO = new SalaDTO();
            sDTO.setId(Integer.parseInt(cursor.getString(0)));
            sDTO.setNome(cursor.getString(1));
            sDTO.setMidia(cursor.getString(2));
            sDTO.setTamanho(cursor.getString(3));
            sDTO.setMesa(cursor.getString(4));
            sDTO.setRefrigera(cursor.getString(5));

            //ENVIA AS INFORMAÇÕES DO CURSOR PARA O ARRAY
            arraySala.add(sDTO);

        }

        return arraySala;


    }

    public ArrayList verificaDiaReserva(String ID) {

        ArrayList arrayDia = new ArrayList();
        String[] args = {ID};

        Cursor cursor = getWritableDatabase().rawQuery("SELECT DIA FROM " + RESERVA + " WHERE ID_SALA = ?", args);

        //ENQUANTO HOUVER LINHAS NO CURSOR
        while (cursor.moveToNext()) {
            arrayDia.add(cursor.getString(0));
        }

        return arrayDia;

    }

    public ArrayList verificaHoraReserva(String ID, String Dia) {

        ArrayList arrayHora = new ArrayList();
        String[] args = {ID, Dia, STATUS_RESERVADO};

        Cursor cursor = getWritableDatabase().rawQuery("SELECT HORA FROM " + RESERVA + " WHERE ID_SALA = ? AND DIA = ? AND STATUS = ? ", args);

        //ENQUANTO HOUVER LINHAS NO CURSOR
        while (cursor.moveToNext()) {
            arrayHora.add(cursor.getString(0));
        }

        return arrayHora;

    }

    public void reservarSala(String ID, SalaDTO dto) {

        ContentValues valoresReserva = new ContentValues();

        valoresReserva.put("Status", "RESERVADA");
        valoresReserva.put("DIA", dto.getDia());
        valoresReserva.put("HORA", dto.getHora());
        valoresReserva.put("ID_SALA", ID);


        getWritableDatabase().insert(RESERVA, null, valoresReserva);

    }

    /**
     * FIM SalaActivity
     */


    ////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * INÍCIO MainActivity
     */

    public ArrayList<SalaDTO> listarNomesSala() {

        //ARRAYLIST DO TIPO "SalaDTO"
        ArrayList<SalaDTO> arraySala = new ArrayList<>();


        //COMANDO SQL "CRU"
        Cursor cursor = getWritableDatabase().rawQuery("SELECT ID, NOME FROM " + SALA, null);

        //ENQUANTO HOUVER LINHAS NO CURSOR
        while (cursor.moveToNext()) {
            SalaDTO sDTO = new SalaDTO();
            sDTO.setId(Integer.parseInt(cursor.getString(0)));
            sDTO.setNome(cursor.getString(1));


            //ENVIA AS INFORMAÇÕES DO CURSOR PARA O ARRAY
            arraySala.add(sDTO);

        }
        return arraySala;


    }

    public void apagarSala(String ID) {

        String[] args = {ID};

        getWritableDatabase().delete(SALA, "id = ?", args);

    }

    /**
     * FIM MainActivity
     */


    ////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * INÍCIO removerReservaActivity
     */


    public ArrayList<String> ReservaDia(String ID) {

        TreeSet<String> diaReservado = new TreeSet<>();
        String[] args = {ID, STATUS_RESERVADO};
        ArrayList<String> arrayDia = new ArrayList<>();

        Cursor cursor = getWritableDatabase().rawQuery("SELECT DIA FROM " + RESERVA + " WHERE ID_SALA = ? AND STATUS = ?", args);

        while (cursor.moveToNext()) {
            diaReservado.add(cursor.getString(0));
        }
        Iterator<String> iterator = diaReservado.iterator();

        while (iterator.hasNext()) {
            arrayDia.add(iterator.next());
        }

        return arrayDia;
    }

    public ArrayList ReservasHora(String ID, String Dia) {

        ArrayList arrayReserva = new ArrayList();
        String[] args = {ID, Dia, STATUS_RESERVADO};

        Cursor cursor = getWritableDatabase().rawQuery("SELECT HORA FROM " + RESERVA + " WHERE ID_SALA = ? AND DIA = ? AND STATUS = ?", args);

        while (cursor.moveToNext()) {
            arrayReserva.add(cursor.getString(0));
        }

        return arrayReserva;


    }

    public void retirarReserva(String id, String dia, String hora) {

        ContentValues valor = new ContentValues();
        valor.put("STATUS", "LIVRE");

        String[] args = {id, dia, hora, STATUS_RESERVADO};

        getWritableDatabase().update(RESERVA, valor, "id_sala = ? AND dia = ? AND hora = ? AND status = ?", args);

    }

    /**
     * FIM removerReservaActivity
     */


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * INÍCIO NAVIGATION DRAWER
     */


    public ArrayList<SalaDTO> mostrarMultimidia() {

        //ARRAYLIST DO TIPO "SalaDTO"
        ArrayList<SalaDTO> arraySala = new ArrayList<>();

        String[] args = {"N/A"};


        //COMANDO SQL "CRU"
        Cursor cursor = getWritableDatabase().rawQuery("SELECT ID, NOME FROM " + SALA + " WHERE MIDIA != ? ", args);

        //ENQUANTO HOUVER LINHAS NO CURSOR
        while (cursor.moveToNext()) {
            SalaDTO sDTO = new SalaDTO();
            sDTO.setId(Integer.parseInt(cursor.getString(0)));
            sDTO.setNome(cursor.getString(1));


            //ENVIA AS INFORMAÇÕES DO CURSOR PARA O ARRAY
            arraySala.add(sDTO);

        }
        return arraySala;

    }

    public ArrayList<SalaDTO> mostrarGrande() {

        //ARRAYLIST DO TIPO "SalaDTO"
        ArrayList<SalaDTO> arraySala = new ArrayList<>();

        String[] args = {"Grande"};


        //COMANDO SQL "CRU"
        Cursor cursor = getWritableDatabase().rawQuery("SELECT ID, NOME FROM " + SALA + " WHERE TAMANHO == ? ", args);

        //ENQUANTO HOUVER LINHAS NO CURSOR
        while (cursor.moveToNext()) {
            SalaDTO sDTO = new SalaDTO();
            sDTO.setId(Integer.parseInt(cursor.getString(0)));
            sDTO.setNome(cursor.getString(1));


            //ENVIA AS INFORMAÇÕES DO CURSOR PARA O ARRAY
            arraySala.add(sDTO);

        }
        return arraySala;

    }


    /**
     * FIM NAVIGATION DRAWER
     */


///////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * INÍCIO USER
     */


    public void cadUser(UserDTO dto) {
        ContentValues values = new ContentValues();
        values.put("NOME", dto.getNomeUser());
        values.put("EMAIL", dto.getEmailUser());
        values.put("TELEFONE", dto.getTelUser());
        values.put("USUARIO", dto.getUserUser());
        values.put("SENHA", dto.getSenhaUser());
        values.put("NV_ACESSO", dto.getAcessoUser());


        getWritableDatabase().insert(USUARIO, null, values);
    }

    public void delUser(String ID) {
        String[] args = {ID};
        getWritableDatabase().delete(USUARIO, "ID_USER = ?", args);
    }


    public void altUser(String ID, UserDTO dto) {
        ContentValues values = new ContentValues();
        values.put("NOME", dto.getNomeUser());
        values.put("EMAIL", dto.getEmailUser());
        values.put("TELEFONE", dto.getTelUser());
        values.put("USUARIO", dto.getUserUser());
        values.put("SENHA", dto.getSenhaUser());
        values.put("NV_ACESSO", dto.getAcessoUser());

        String[] args = {ID};
        getWritableDatabase().update(USUARIO, values, "ID_USER=?", args);

    }

    public ArrayList<UserDTO> listarNomeUser() {
        Cursor cursor = getWritableDatabase().rawQuery("SELECT ID_USER, NOME FROM " + USUARIO, null);
        ArrayList<UserDTO> arrayUser = new ArrayList();


        while (cursor.moveToNext()) {
            UserDTO uDTO = new UserDTO();
            uDTO.setIdUser(Integer.parseInt(cursor.getString(0)));
            uDTO.setNomeUser(cursor.getString(1));

            arrayUser.add(uDTO);
        }

        return arrayUser;

    }




    public void listarUser(String ID, UserDTO uDTO) {

        String[] args = {ID};


        //COMANDO SQL "CRU"
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM " + USUARIO + " WHERE ID_USER = ?", args);

        //ENQUANTO HOUVER LINHAS NO CURSOR
        while (cursor.moveToNext()) {

            uDTO.setIdUser(Integer.parseInt(cursor.getString(0)));
            uDTO.setNomeUser(cursor.getString(1));
            uDTO.setEmailUser(cursor.getString(2));
            uDTO.setTelUser(cursor.getString(3));
            uDTO.setUserUser(cursor.getString(4));
            uDTO.setSenhaUser(cursor.getString(5));
            uDTO.setAcessoUser(Integer.parseInt(cursor.getString(6)));


        }


    }
        public Integer login(UserDTO uDTO){
            String[] args = {uDTO.getUserUser(),uDTO.getSenhaUser()};
            Integer nvAcesso = 3;

            Cursor login = getWritableDatabase().rawQuery("SELECT NV_ACESSO FROM " + USUARIO + " WHERE USUARIO = ? AND SENHA = ?", args);

            while (login.moveToNext()){

                nvAcesso = Integer.parseInt(login.getString(0));
            }

            return nvAcesso;
        }



    /**
     * FIM USER
     */

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void APAGAR_BD() {
        getWritableDatabase().delete(RESERVA, null, null);
        getWritableDatabase().delete(SALA, null, null);
        getWritableDatabase().delete(USUARIO,null,null);
    }


}


