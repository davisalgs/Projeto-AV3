package org.example.TabelaHash;

import java.lang.reflect.Array;

public class TabelaHash<K extends Comparable<K>, V> implements Map<K, V> {

    private int tamanho;
    private ListaDuplamenteEncadeada<Entrada<K, V>>[] tabela;

    @SuppressWarnings("unchecked")
    public TabelaHash(int capacidade) {

        this.tabela = (ListaDuplamenteEncadeada<Entrada<K, V>>[]) Array.newInstance(ListaDuplamenteEncadeada.class,
                capacidade);
        this.tamanho = 0;
    }

    public TabelaHash() {
        this(37);
    }

    @Override
    public void insere(K chave, V valor) {
        int indice = hash(chave);
        Entrada<K, V> entrada = new Entrada<K, V>(chave, valor);
        if (tabela[indice] == null) {
            ListaDuplamenteEncadeada<Entrada<K, V>> listaAuxiliar = new ListaDuplamenteEncadeada<Entrada<K, V>>();
            listaAuxiliar.adicionarFinal(entrada);
            tabela[indice] = listaAuxiliar;
        } else {
            tabela[indice].adicionarFinal(entrada);
        }
        this.tamanho++;
    }

    @Override
    public Entrada<K, V> busca(K chave) {
        int indice = hash(chave);
        if(tabela[indice]==null){
            return null;
        }

        Nodo<Entrada<K,V>> atual = tabela[indice].primeiro;
        while (atual!=null) {
            if(atual.getElemento().getChave().compareTo(chave)==0){
                //as chaves foram iguais
                return atual.getElemento();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    @Override
    public Entrada<K, V> remove(K chave) {
        int indice = hash(chave);
        if(tabela[indice]==null){
            return null;
        }
        ListaDuplamenteEncadeada<Entrada<K,V>> lista = tabela[indice];
        Nodo<Entrada<K,V>> atual = lista.primeiro;
        Entrada<K,V> removido = null;
        while (atual!=null) {
            if(atual.getElemento().getChave().compareTo(chave)==0){

                if(atual==lista.primeiro){
                    removido = atual.getElemento();
                    lista.removerComeco();
                    return removido;
                }

                if(atual==lista.ultimo){
                    removido = atual.getElemento();
                    lista.removerFinal();
                    return removido;
                }

                removido = atual.getElemento();
                atual.getAnterior().setProximo(atual.getProximo());
                atual.getProximo().setAnterior(atual.getAnterior());
                lista.tamanho--;
                return removido;
            }
            atual = atual.getProximo();
        }
        return removido;
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public boolean estaVazia() {
        return this.tabela.length == 0;
    }

    public int hash(K chave) {
        if (chave instanceof Integer) {
            return (Integer) chave % this.tabela.length;
        }
        throw new IllegalArgumentException("chave Invalida");
    }

    @Override
    public String toString() {
        int cap = 2 + tamanho() + (2 * (tamanho() - 1));

        StringBuilder sb = new StringBuilder(cap);
        sb.append("[");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {

                if (i == tamanho() - 1) {
                    sb.append(tabela[i].toString());
                } else {
                    sb.append(tabela[i].toString());
                    sb.append(tabela[i].toString());
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

}

