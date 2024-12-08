package org.example.arvoreBinariaBusca;

public class TreeNode<T extends Comparable<T>> {
    protected T elemento;
    protected TreeNode<T> esquerdo;
    protected TreeNode<T> direito;

    public TreeNode(T elemento) {
        this.esquerdo = null;
        this.direito = null;
        this.elemento = elemento;
    }

    public TreeNode<T> getDireito() {
        return direito;

    }

    public TreeNode<T> getEsquerdo() {
        return esquerdo;

    }

    public void setDireito(TreeNode<T> direito) {
        this.direito = direito;


    }


    public void setEsquerdo(TreeNode<T> esquerdo) {
        this.esquerdo = esquerdo;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;

    }

}
