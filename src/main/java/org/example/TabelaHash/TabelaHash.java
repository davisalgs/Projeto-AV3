package org.example.TabelaHash;

import java.lang.reflect.Array;


public class TabelaHash<K extends Comparable<K>, V> implements Map<K, V> {

    private int tamanho;
    private ListaDuplamenteEncadeada<Entrada<K, V>>[] tabela;


    @SuppressWarnings("unchecked")
    public TabelaHash(int capacidade) {
        // cria um array de listas duplamente encadeadas usando reflexão
        this.tabela = (ListaDuplamenteEncadeada<Entrada<K, V>>[]) Array.newInstance(ListaDuplamenteEncadeada.class, capacidade);
        this.tamanho = 0;
    }

    // construtor padrão com capacidade inicial de 37
    public TabelaHash() {
        this(37);
    }

    @Override
    public void insere(K chave, V valor) {
        // calcula o índice na tabela usando a função hash
        int indice = hash(chave);
        Entrada<K, V> entrada = new Entrada<K, V>(chave, valor); // Cria a nova entrada
        if (tabela[indice] == null) {
            //se a posição está vazia, cria uma nova lista e adiciona a entrada
            ListaDuplamenteEncadeada<Entrada<K, V>> listaAuxiliar = new ListaDuplamenteEncadeada<>();
            listaAuxiliar.adicionarFinal(entrada);
            tabela[indice] = listaAuxiliar;
        } else {
            // se não ele adiciona a entrada ao final da lista existente
            tabela[indice].adicionarFinal(entrada);
        }
        this.tamanho++;
    }

    @Override
    public Entrada<K, V> busca(K chave) {
        int indice = hash(chave); //calcula o índice da chave
        if (tabela[indice] == null) {
            return null; //retorna null se não tiver nenhuma entrada na posição
        }

        //percorre a lista na posição do índice
        Nodo<Entrada<K, V>> atual = tabela[indice].primeiro;
        while (atual != null) {
            // compara as chaves para encontrar a entrada
            if (atual.getElemento().getChave().compareTo(chave) == 0) {
                return atual.getElemento(); //retorna a entrada se encontrada
            }
            atual = atual.getProximo(); //move para o próximo elemento
        }
        return null; // retorna null se não encontrar a chave
    }

    @Override
    public Entrada<K, V> remove(K chave) {
        int indice = hash(chave); // calcula o índice da chave
        if (tabela[indice] == null) {
            return null; // retorna null se a lista na posição for nula
        }
        ListaDuplamenteEncadeada<Entrada<K, V>> lista = tabela[indice];
        Nodo<Entrada<K, V>> atual = lista.primeiro;
        Entrada<K, V> removido = null;

        //percorre a lista para encontrar a entrada a ser removida
        while (atual != null) {
            if (atual.getElemento().getChave().compareTo(chave) == 0) {
                removido = atual.getElemento();
                if (atual == lista.primeiro) {
                    //remove o primeiro elemento
                    lista.removerComeco();
                } else if (atual == lista.ultimo) {
                    //remove o último elemento
                    lista.removerFinal();
                } else {
                    //remove um elemento intermediário
                    atual.getAnterior().setProximo(atual.getProximo());
                    atual.getProximo().setAnterior(atual.getAnterior());
                    lista.tamanho--;
                }
                return removido;
            }
            atual = atual.getProximo(); //move para o próximo elemento
        }
        return removido; //retorna null se a chave não for encontrada
    }

    @Override
    public int tamanho() {
        return this.tamanho; // Retorna o número de elementos na tabela
    }

    @Override
    public boolean estaVazia() {
        return this.tabela.length == 0; //verifica se a tabela está vazia
    }

    public int hash(K chave) {
        //  hash simples que calcula o índice para chaves do tipo Integer
        if (chave instanceof Integer) {
            return (Integer) chave % this.tabela.length;
        }
        throw new IllegalArgumentException("chave Invalida");
    }

    @Override
    public String toString() {
        int cap = 2 + tamanho() + (2 * (tamanho() - 1)); //calcula o tamanho estimado do StringBuilder
        StringBuilder sb = new StringBuilder(cap);
        sb.append("[");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                //concatena a representação das listas
                sb.append(tabela[i].toString());
                if (i != tamanho() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString(); // string representando a tabela
    }

}
