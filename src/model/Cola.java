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
public class Cola<T> implements Serializable{
    private ListaDoble<T> lista;
    
    public Cola() {
        this.lista = new ListaDoble();
    }
    
    public void insertar (T e) {
        this.lista.insertarFinal(e);
    }
    
    public T quitar() {
        if (this.isVacia()){
            return null;
        } else {
            return (T)lista.obtenerInicio();
        }
    }
    
    public boolean isVacia(){
        return this.lista.getInicio()==null;
    }
}
