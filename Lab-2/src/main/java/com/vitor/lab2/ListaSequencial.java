package com.vitor.lab2;
import java.util.*;
/**
 *
 * @author vitor
 */
public class ListaSequencial extends Lista{
    IElemento[] data = new IElemento[100];
    
    ListaSequencial(){
        for(int i = 0; i < this.data.length; i++){
            this.data[i] = null;
        }
        size();
    }
       
    @Override
    public int size() {
        int cont = 0;
        for(IElemento atual: this.data){
            if(atual == null){
                break;
            }else{
                cont++;
                
            }
            
        }
        this.size = cont;
        
        return this.size;
    }

    @Override
    public void insert(IElemento elem) {
        
        for(int i = 0; i <= this.size ;i++){
            
            if(this.data[i] == null){
                this.data[i] = elem;
                break;
            }
        }
        size();
    }

    @Override
    public void remove(IElemento elem) {
        
        int i;
        for(i = 0; i <= this.size ;i++){
            
            if(java.util.Objects.equals(this.data[i], null)){
                this.data[i] = this.data[i+1];
                this.data[i+1] = null;
            }else{
                if(elem.equals(this.data[i])){
                    this.data[i] = this.data[i+1];
                    this.data[i+1] = null;
                }
            }
            
        }
        size();
    }

    @Override
    public void print() {
        IElemento atual;
        for(int i = 0; i < this.size ;i++){
            atual = this.data[i];
            if(atual == null){System.out.println("null" + " ");}else{
            System.out.print(atual.toString()+ " ");
            }
            }
        System.out.print("\n");
        }
    }
   
    
    

