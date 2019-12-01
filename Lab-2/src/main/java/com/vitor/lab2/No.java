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
public class No extends ListaEncadeada{
    private IElemento elemento; 
    No next;
    No(){
        this.next = (No)null;
        this.elemento = (IElemento)null;
    }
    public IElemento getELemento(){
        
    return this.elemento;
        }
    
    public void setElemento(IElemento elem){
        this.elemento = elem;
    
    }
    
   
    
    
}
