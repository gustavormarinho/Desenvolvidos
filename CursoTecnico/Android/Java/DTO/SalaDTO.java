package com.example.a3ainfo.tcc;

/**
 * Created by 3ainfo on 29/05/2017.
 */

public class SalaDTO {

    private Integer id;
    private String nome;
    private String status;
    private String midia;
    private String tamanho;
    private String mesa;
    private String refrigera;
    private String dia;
    private String hora;



    @Override
    public String toString() {
        return nome.toUpperCase();
    }








    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }






    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getRefrigera() {
        return refrigera;
    }

    public void setRefrigera(String refrigera) {
        this.refrigera = refrigera;
    }



}
