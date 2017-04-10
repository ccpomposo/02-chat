/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controlador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.Cliente;
import model.Mensaje;

/**
 *
 * @author Usuario
 */
public class ClienteFrame extends JFrame {

    private JTextArea txtChat;
    private JTextField txtMensaje;
    private JButton btnEnviar;
    private Controlador controlador;
    private Cliente cliente;

    public ClienteFrame() {
        super("Cliente");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setSize(500, 700);
        this.cliente = new Cliente();
        try {
            this.cliente.iniciar();
        } catch (IOException ex) {
            Logger.getLogger(ClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.controlador = new Controlador(this.cliente);
        super.add(this.crearPnlChat(), BorderLayout.CENTER);
        super.add(this.crearPnlMensaje(), BorderLayout.SOUTH);

        super.setVisible(true);
    }

    private JPanel crearPnlChat() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.txtChat = new JTextArea();
        new Thread(this.controlador).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Mensaje mensaje;
                while (true) {
                    mensaje = controlador.getMensaje();
                    if (mensaje != null) {
                        System.out.println("[Frame] Wa imprimir");
                        txtChat.append(mensaje.getMensaje()+ "\n");
                        System.out.println("Mensaje imprimido");
                    }
                }
            }
        }).start();
        panel.add(this.txtChat);
        return panel;
    }

    private JPanel crearPnlMensaje() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        this.txtMensaje = new JTextField(10);
        this.btnEnviar = new JButton("Enviar");
        this.btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                controlador.enviarMensaje(new Mensaje(null, txtMensaje.getText()));
                System.out.println("Mensaje enviado");
            }
        });
        panel.add(this.txtMensaje);
        panel.add(this.btnEnviar);

        return panel;
    }
}

class Test {

    public static void main(String[] args) {
        new ClienteFrame();
    }
}
