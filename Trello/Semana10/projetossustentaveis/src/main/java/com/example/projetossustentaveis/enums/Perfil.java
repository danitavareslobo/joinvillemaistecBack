package com.example.projetossustentaveis.enums;

public enum Perfil {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    Perfil(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}