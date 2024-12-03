package org.example.TabelaHash;

interface Map<K, V> {
    void insere(K chave, V valor);

    Entrada<K, V> busca(K chave);

    Entrada<K, V> remove(K chave);

    int tamanho();

    boolean estaVazia();
}
