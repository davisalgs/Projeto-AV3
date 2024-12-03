package org.example.arvoreBinariaBusca;

public class ArvoreBinariaBusca<T extends Comparable<T>> {
    private TreeNode<T> raiz;

    private int tamanho;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.tamanho = 0;
    }

    public int tamanho() {
        return this.tamanho;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

    public void insere(T elemento) {
        this.raiz = insere(raiz, elemento);
    }
    public T busca(T elemento){
        return busca(raiz, elemento);
    }
    private TreeNode<T> insere(TreeNode<T> noArvore, T elemento) {
        if (noArvore == null) {
            noArvore = new TreeNode<T>(elemento);
            return noArvore;
        }

        if (elemento.compareTo(noArvore.getElemento()) > 0) {

            noArvore.direito = insere(noArvore.direito, elemento);
        } else if (elemento.compareTo(noArvore.getElemento()) < 0) {

            noArvore.esquerdo = insere(noArvore.esquerdo, elemento);
        }
        return noArvore;
    }

    private T busca(TreeNode<T> noArvore, T elemento) {
        if (noArvore == null) {
            return null;
        }

        if (noArvore.getElemento().compareTo(elemento) == 0) {
            return noArvore.getElemento();
        }
        if (elemento.compareTo(noArvore.getElemento()) < 0) {
            return busca(noArvore.esquerdo, elemento);
        }else{

            return busca(noArvore.direito, elemento);
        }
    }
    public void imprimirEmOrdem(){
        imprimirEmOrdem(raiz);
    }
    private void imprimirEmOrdem(TreeNode<T> noArvore){
        if(noArvore==null){
            return;
        }
        this.imprimirEmOrdem(noArvore.esquerdo);
        System.out.println(noArvore.getElemento().toString());
        this.imprimirEmOrdem(noArvore.direito);
    }

}

