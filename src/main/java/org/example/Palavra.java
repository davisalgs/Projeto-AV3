package org.example;


import org.example.TabelaHash.ListaDuplamenteEncadeada;


// implementada a interface Comparable para permitir comparações entre objetos do tipo Palavra.
public class Palavra implements Comparable<Palavra> {


    private String palavra;


    private ListaDuplamenteEncadeada<Integer> linhas;

    // inicializa a palavra e cria uma nova lista vazia para os números das linhas.
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


    // compara esta palavra com outra, baseando-se na ordem alfabética (usando o método compareTo de String).
    @Override
    public int compareTo(Palavra outraPalavra) {
        return this.getPalavra().compareTo(outraPalavra.getPalavra());
    }


    // retorna os elementos da lista formatados.
    @Override
    public String toString() {
        return getPalavra() + " " + linhas.toString();
    }
}
