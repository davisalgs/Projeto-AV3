package org.example.TabelaHash;

public class Nodo<T>{
    private Nodo<T> anterior;
    private Nodo<T> proximo;
    private T elemento;

    public Nodo(T elemento){
        this.anterior = null;
        this.proximo = null;
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }
    public void setAnterior(Nodo<T> anterior) {
        this.anterior = anterior;
    }
    public void setProximo(Nodo<T> proximo) {
        this.proximo = proximo;
    }
    public Nodo<T> getAnterior() {
        return anterior;
    }
    public Nodo<T> getProximo() {
        return proximo;
    }
}

