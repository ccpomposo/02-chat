/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Cliente;
import model.Mensaje;
import model.Servidor;

/**
 *
 * @author Usuario
 */
public class Controlador implements Runnable{
    //private Servidor servidor;
    private Cliente cliente;
    private Thread hilo;
    private Mensaje mensaje;
    
    public Controlador(Cliente cliente) {
        //this.servidor = Servidor.getInstance();
        this.cliente = cliente;
    }
    
    public void enviarMensaje(Mensaje mensaje) {
        this.cliente.enviarMensaje(mensaje);
    }

    @Override
    public void run() {
        while(true) {
            if(this.cliente.hasMensajeRecibido()) {
                this.mensaje = (this.cliente.getMensaje());
            } else {
                this.setMensaje(null);
            }
        }
    }
    
    public synchronized Mensaje getMensaje() {
        return mensaje;
    }

    public synchronized void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }
    
    
}


