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
public class Nodo<T> implements Serializable{
    private T dato;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;
    
    public Nodo(T dato){
        this.dato = dato;
        siguiente = null;
        anterior = null;
    }
    
    public void print(Nodo head) { // Método HackerRank Print the elements of a linked list
    if(head != null){
         System.out.println(head.getDato());
         print(head.getSiguiente());
        }
    }
    public Nodo insert(Nodo head,int data) { //Método HackerRank Insert a node at the tail of a linked list
        Nodo tail = new Nodo(data);
        if (head == null) {
            head = tail;
        } else {
            Nodo aux = head;
            while(aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(tail);
        }
        return head;
    }
    
    public Nodo Insert(Nodo head,int x) { //Método HackerRank Insert a node at the head of a linked list
        Nodo nuevo = new Nodo(x);
        if (head == null) {
            head = nuevo;
        } else {
            nuevo.setSiguiente(head);
            head = nuevo;
        }
        return head;
    }
    
    public Nodo InsertNth(Nodo head, int data, int position) { //Método HackerRank Insert a node at a specific position in a linked list
        Nodo nuevo = new Nodo(data);
        if (head == null && position == 0){
            head = nuevo;
        } else {
            Nodo aux = head;
            Nodo ant = null;
            int pos = 0;
            while (aux != null && pos != position) {
                ant = aux;
                aux = aux.getSiguiente();
                pos++;
            }
            if(ant == null) {
                nuevo.setSiguiente(head);
                head = nuevo;
            } else {
                nuevo.setSiguiente(aux);
                ant.setSiguiente(nuevo);
            }
        }
        return head;
    }
    
    public Nodo Delete(Nodo head, int position) { //Método HackerRank Delete a node
        Nodo aux = head;
        Nodo ant = null;
        int pos = 0;
        while(aux != null && pos != position) {
            ant = aux;
            aux = aux.getSiguiente();
            pos++;
        }
        if (aux != null) {
            if (ant == null) {
                head = aux.getSiguiente();
                aux.setSiguiente(null);
            } else {
                ant.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(null);
            }
        }
        return head;
    }
    
    public void ReversePrint(Nodo head) { //Método HackerRank Print in reverse
        if (head != null){
            ReversePrint(head.getSiguiente());
            System.out.println(head.getDato());
        }
    }
    
    public Nodo Reverse(Nodo head) { //Método HackerRank Reverse a linked list
        if (head == null || head.getSiguiente() == null){
            return head;
        } else {
            Nodo resto = Reverse(head.getSiguiente());
            head.getSiguiente().setSiguiente(head);
            head.setSiguiente(null);
            return resto;
        }
    }
    
    public int CompareLists(Nodo headA, Nodo headB) { 
        if(headA == null && headB == null){
            return 1;
        } else if (headA == null || headB == null){
            return 0;
        }
        if(headA.getDato()==headB.getDato()){
            return CompareLists(headA.getSiguiente(), headB.getSiguiente());
        } else {
            return 0;
        }
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }

    public Nodo<T> getAnterior() {
        return anterior;
    }
    
}
