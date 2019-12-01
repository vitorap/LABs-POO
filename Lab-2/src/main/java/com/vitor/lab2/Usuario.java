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
public class Usuario implements IElemento {
    <IElemento> Usuario(String s){
    this.nome = s;
    retorna();
    }
    private IElemento retorna(){
        return this;
    }
    private String nome;
    
    
    @Override
    public boolean equals(IElemento elem){
    Usuario user = (Usuario) elem;
    
    if(user.nome == this.nome){
        return true;
    
    }else{
        return false;
    }
    }
        

    
    public void setNome(String s){
        this.nome = s;
    }

    
    public String getElemento() {
        return this.nome;
    }
    
    @Override 
    public String toString(){
        if(this.nome == null){
        return "null";
        }else{
        return this.nome;
    }
  
    }
    
    
    
}
