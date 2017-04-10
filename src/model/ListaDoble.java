/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class ListaDoble<T> implements Serializable, Iterable {
    private Nodo<T> inicio;
    private Nodo<T> fin;
    private Integer lenght;
    
    public ListaDoble () {
        this.inicio = null;
        this.fin = null;
        this.lenght = 0;
    }
    
    public void insertarFinal(T e) {
        Nodo<T> nuevo = new Nodo(e);
        if(inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            this.lenght++;
        } else {
            Nodo<T> aux = inicio;
            while(aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
            nuevo.setAnterior(aux);
            fin = nuevo;
            this.lenght++;
        }
    }
    
    public void insertarInicio(T e) {
        Nodo<T> nuevo = new Nodo(e);
        if(inicio == null) {
            inicio = nuevo;
            fin = nuevo;
            this.lenght++;
        } else {
            Nodo<T> aux = inicio;
            while(aux.getAnterior() != null) {
                aux = aux.getAnterior();
            }
            aux.setAnterior(nuevo);
            nuevo.setSiguiente(aux);
            inicio = nuevo;
            this.lenght++;
        }
    }
    
    public T obtenerFinal() {
        T r = fin.getDato();
        Nodo<T> aux = fin.getAnterior();
        aux.setSiguiente(null);
        fin.setAnterior(null);
        fin = aux;
        return r;
    }
    
    public T obtenerInicio() {
        T r = inicio.getDato();
        Nodo<T> aux = inicio.getSiguiente();
        if(aux != null){
            aux.setAnterior(null);
            inicio.setSiguiente(null);   
        }
        inicio = aux;
        return r;
    }
    
    public void recorrer() {
        Nodo<T> aux = inicio;
        while(aux != null) {
            System.out.print(aux.getDato());
            aux = aux.getSiguiente();
        }
        System.out.println("");
    }
    
//    public Integer comparar(ListaDoble<T> num) {
//        if(this.lenght > num.lenght) {
//            return 1;
//        } else if (num.lenght > this.lenght) {
//            return 2;
//        } else {
//            Nodo<T> aux1 = this.inicio;
//            Nodo<T> aux2 = num.inicio;
//            while(aux1 != null && aux1.getDato() == aux2.getDato()) {
//                aux1 = aux1.getSiguiente();
//                aux2 = aux2.getSiguiente();
//            }
//            if(aux1.getDato().compareTo(aux2.getDato())>0) {
//                return 1;
//            } else {
//                return 2;
//            }
//        }
//    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public Nodo<T> getFin() {
        return fin;
    }

    public Integer getLenght() {
        return lenght;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            Nodo<T> siguiente = inicio;
            @Override
            public boolean hasNext() {
                return siguiente != null;
            }

            @Override
            public T next() {
                T dato = siguiente.getDato();
                siguiente = siguiente.getSiguiente();
                return dato;              
            }
        };
    }
}

