/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Servidor implements Runnable{
    private ListaDoble<ThreadServidor> clientes;
    private ServerSocket socket;
    private Thread hilo;
    private static Servidor instance;
    
    private Servidor() {
        this.clientes = new ListaDoble();
        try {
            this.socket = new ServerSocket(3000);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Servidor getInstance() {
        if (instance == null){
            instance = new Servidor();
        }
        return instance;
    }

    @Override
    public void run() {
        ThreadServidor nCliente;
        while(hilo != null){
            try {
                nCliente = new ThreadServidor(this,this.socket.accept());
                this.clientes.insertarFinal(nCliente);
                nCliente.abrir();
                nCliente.start();
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void iniciar() {
        if (this.hilo == null) {
            this.hilo = new Thread(this);
            this.hilo.start();
        }
    }
    
    public void detener() {
        if (this.hilo != null) {
            this.hilo.interrupt();
            this.hilo = null;
        }
    }
    
    public synchronized void recibir(Mensaje entrada) {
        for (Iterator<ThreadServidor> it = clientes.iterator(); it.hasNext();) {
            ThreadServidor cliente = it.next();
            cliente.enviar(entrada);
            System.out.println("[Servidor] Mensaje enviado");
        }
    }
    
    public static void main(String[] args) {
        Servidor servidor = Servidor.getInstance();
        servidor.iniciar();
        System.out.println("Servidor iniciado");
    }
    
}
