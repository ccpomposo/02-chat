/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Mensaje implements Serializable{
    
    private String mensaje;
    private ThreadServidor origen;
    private ThreadServidor destino;
    
    public Mensaje(ThreadServidor origen, String mensaje){
        this.origen = origen;
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ThreadServidor getOrigen() {
        return origen;
    }

    public void setOrigen(ThreadServidor origen) {
        this.origen = origen;
    }

    public ThreadServidor getDestino() {
        return destino;
    }

    public void setDestino(ThreadServidor destino) {
        this.destino = destino;
    }
    
    
}
