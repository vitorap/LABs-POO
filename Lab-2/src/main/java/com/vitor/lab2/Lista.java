/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitor.lab2;

/**
 *
 * @author vitor
 */
public abstract class Lista implements Imprimivel{
    
    protected int size;
    
    Lista(){
   
    }
    
    public abstract int size();
    
    public abstract void insert(IElemento elem);
    
    public abstract void remove(IElemento elem);
    
}
