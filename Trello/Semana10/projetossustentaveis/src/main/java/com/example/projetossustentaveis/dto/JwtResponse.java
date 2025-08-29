package com.example.projetossustentaveis.dto;

public class JwtResponse {

    private String token;
    private String tipo = "Bearer";
    private String username;
    private String perfil;

    public JwtResponse() {}

    public JwtResponse(String token, String username, String perfil) {
        this.token = token;
        this.username = username;
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}