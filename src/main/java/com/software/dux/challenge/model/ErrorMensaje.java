package com.software.dux.challenge.model;

public class ErrorMensaje {
    
    private String mensaje;
    private int codigo;

    public ErrorMensaje() {
    }

    public ErrorMensaje(String mensaje, int codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
        
}
