/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Cliente implements Runnable{
   private Socket socket;
   private Thread hilo;
   //private ObjectInputStream iStream;
   private ObjectOutputStream oStream;
   private ThreadCliente cliente;
   private Cola<Mensaje> mensajesEnviados;
   private Cola<Mensaje> mensajesRecibidos;
   
   public Cliente() {
       try {
           this.socket = new Socket("127.0.0.1", 3000);
       } catch (IOException ex) {
           Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
       }
       this.mensajesEnviados = new Cola();
       this.mensajesRecibidos = new Cola();
   }

    @Override
    public void run() {
        while (this.hilo != null) {
            if (this.hasMensajeEnviado()){
                System.out.println("-----  x");
                try {
                    oStream.writeObject(this.mensajesEnviados.quitar());
                    System.out.println("[Cliente] Mensaje enviado");
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void recibir(Mensaje mensaje) {
        this.mensajesRecibidos.insertar(mensaje);
    }
    
    public void iniciar() throws IOException {
        this.oStream = new ObjectOutputStream(this.socket.getOutputStream());
        if(this.hilo == null){
            this.cliente = new ThreadCliente(this,this.socket);
            this.hilo = new Thread(this);
            this.hilo.start();
        }
    }
    
    public void detener() {
        if(this.hilo != null) {
            this.hilo.interrupt();
            this.hilo = null;
        }
        if(this.oStream != null) {
            try {
                this.oStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.cliente.cerrar();
        this.cliente.stop();
    }
    
    public void enviarMensaje(Mensaje mensaje) {
        this.mensajesEnviados.insertar(mensaje);
        System.out.println("[Cliente] Mensaje insertado");
    }
    
    public synchronized boolean hasMensajeEnviado() {
        return !this.mensajesEnviados.isVacia();
    }
    
    public synchronized boolean hasMensajeRecibido() {
        return !this.mensajesRecibidos.isVacia();
    }
    public Mensaje getMensaje(){
        return this.mensajesRecibidos.quitar();
    }
}
