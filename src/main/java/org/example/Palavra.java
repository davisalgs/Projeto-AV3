package org.example;

import org.example.TabelaHash.ListaDuplamenteEncadeada;

public class Palavra implements Comparable<Palavra> {

    private String palavra;
    private ListaDuplamenteEncadeada<Integer> linhas;

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.linhas = new ListaDuplamenteEncadeada<Integer>();
    }

    public void adicionarNumeroDaLinha(Integer numeroDaLinha) {
        this.linhas.adicionarFinal(numeroDaLinha);
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getPalavra() {
        return palavra;
    }

    public ListaDuplamenteEncadeada<Integer> getLinhas() {
        return linhas;
    }

    @Override
    public int compareTo(Palavra outraPalavra) {
        return this.getPalavra().compareTo(outraPalavra.getPalavra());
    }

    @Override
    public String toString() {
        return getPalavra() + " " + linhas.toString();
    }
}

