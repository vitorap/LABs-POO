package com.vitor.lab2;
import java.util.*;
/**
 *
 * @author vitor
 */
public class ListaEncadeada extends Lista{
    No root;
    No top;
    ListaEncadeada(){
    this.top = (No)null;
    this.root = (No)null;
    }
    @Override
    public int size() {
        No no = this.root;
        int cont = 0;
        int i = 0;
        while(!no.equals(null)){
        cont++;
        no = no.next;
        }
        this.size = cont;
        return this.size;
    }

    @Override
    public void insert(IElemento elem) {
        if(this.root == (No)null){
            No no = new No();
            no.setElemento(elem);
            this.root = no;
            this.top = no;
        }else{
        No no = new No();
        no.setElemento(elem);
        this.top.next = no;
        this.top = no;
        }
    }

    @Override
    public void remove(IElemento elem) {
       No atual = this.root;
       No ant = atual;
       while(this.top != atual){
           if(elem.equals(atual.getELemento())){
               ant.next = atual.next;
               break;
            }
           ant = atual;
           atual = atual.next;
       }
    }

    @Override
    public void print() {
        No no = this.root;
        if(this.root == null){return;}else{
            while(!(no == null)){
             System.out.print(no.getELemento() + " ");
             no = no.next;
            }
            System.out.println();     
        }
     }
}
