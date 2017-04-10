/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ThreadServidor extends Thread implements Serializable{
    
    private Servidor servidor;
    private Socket socket;
    private ObjectInputStream iStream;
    private ObjectOutputStream oStream;
    
    public ThreadServidor(Servidor servidor, Socket socket){
        this.servidor = servidor;
        this.socket = socket;
    }
    
    public void enviar(Mensaje mensaje) {
        try {
            this.oStream.writeObject(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.servidor.recibir((Mensaje)iStream.readObject());
                System.out.println("[ThreadServidor] Mensaje enviado");
            } catch (IOException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void abrir() throws IOException {
        this.iStream = new ObjectInputStream(this.socket.getInputStream());
        this.oStream = new ObjectOutputStream(this.socket.getOutputStream());
    }
    
    public void cerrar() throws IOException {
        if (this.socket != null) {
            socket.close();
        }
        if (this.iStream != null) {
            this.iStream.close();
        }
        if (this.oStream != null) {
            this.oStream.close();
        }
    }
}
