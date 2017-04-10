/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ThreadCliente extends Thread{
    private Socket socket;
    private Cliente cliente;
    private ObjectInputStream iStream;
    
    public ThreadCliente(Cliente cliente, Socket socket) {
        this.cliente = cliente;
        this.socket = socket;
        this.abrir();
        this.start();
    }
    
    private void abrir() {
        try {
            this.iStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrar(){
        if(this.iStream != null) {
            try {
                this.iStream.close();
            } catch (IOException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.cliente.recibir((Mensaje)this.iStream.readObject());
                System.out.println("[ThreadCliente] Mensaje enviado");
            } catch (IOException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
