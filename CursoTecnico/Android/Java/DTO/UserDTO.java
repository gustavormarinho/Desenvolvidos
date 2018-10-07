package com.example.a3ainfo.tcc;

/**
 * Created by 3ainfo on 21/06/2017.
 */

public class UserDTO {

    private Integer idUser;
    private String nomeUser;
    private String emailUser;
    private String telUser;
    private String userUser;
    private String senhaUser;
    private Integer acessoUser;

    @Override
    public String toString() {
        return nomeUser.toUpperCase();
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getTelUser() {
        return telUser;
    }

    public void setTelUser(String telUser) {
        this.telUser = telUser;
    }

    public String getUserUser() {
        return userUser;
    }

    public void setUserUser(String userUser) {
        this.userUser = userUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }

    public Integer getAcessoUser() {
        return acessoUser;
    }

    public void setAcessoUser(Integer acessoUser) {
        this.acessoUser = acessoUser;
    }



}
