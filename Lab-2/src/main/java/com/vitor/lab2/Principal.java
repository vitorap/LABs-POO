
package com.vitor.lab2;

/**
 *
 * @author vitor
 */
public class Principal {
    public static void main(String [] args){
    Lista lista;
    Usuario usu = new Usuario("Gustavo");
    //lista = new ListaEncadeada();
    lista = new ListaSequencial();
    lista.insert(new Usuario("Marcelo"));
    lista.insert(new Usuario("Joao"));
    lista.insert(new Usuario("Pedro"));
    lista.insert(new Usuario("Gustavo"));
    lista.insert(new Usuario("Larissa"));
    lista.insert(new Usuario("Flavia"));
    lista.print();
    lista.remove(new Usuario("Gustavo"));
    lista.print();
    
    
    
    
    }
    
    
}
