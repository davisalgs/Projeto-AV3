package org.example;

import org.example.TabelaHash.TabelaHash;
import org.example.arvoreBinariaBusca.ArvoreBinariaBusca;

public class IndiceRemissivo {
    private String textoOriginal;
    TabelaHash<Integer,ArvoreBinariaBusca<Palavra>> tabela;

    public IndiceRemissivo(String texto){
        this.textoOriginal = texto;

        tabela = new TabelaHash<>();
        for (int i = (int) 'a'; i < (int) 'z'; i++) {
            tabela.insere(i,new ArvoreBinariaBusca<Palavra>());
        }
        construirIndiceRemissivo(texto);
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }
    public static String[] sanitizarTexto(String texto){
        return texto.toLowerCase().replaceAll(",", "").strip().split(" ");
    }
    public void imprimirFormatado(){
        for (int i = (int) 'a'; i < (int) 'z'; i++) {
            ArvoreBinariaBusca<Palavra> arvore = tabela.busca(i).getValor();
            if(arvore!=null){
                arvore.imprimirEmOrdem();
            }

        }
    }
    public String buscaNoIndice(String argumentosDeBusca){
        StringBuilder sb = new StringBuilder();
        String[] argumentosDeBuscaEspacados = argumentosDeBusca.strip().toLowerCase().split(" ");

        OrdenarPalavras.ordenarPalavras(argumentosDeBuscaEspacados);
        for (String argumento : argumentosDeBuscaEspacados) {
            int codigoDoCaractere = argumento.charAt(0);
            ArvoreBinariaBusca<Palavra> arvoreDoCaractere = tabela.busca(codigoDoCaractere).getValor();
            Palavra ocorrenciaTeste = new Palavra(argumento);
            Palavra buscado = arvoreDoCaractere.busca(ocorrenciaTeste);
            if(buscado!=null){
                sb.append(buscado.toString()+"\n");
            }
        }

        String conteudo = sb.toString();
        if(conteudo.length()==0){
            return "NENHUM ARGUMENTO ENCONTRADO!";
        }else{
            return conteudo;
        }
    }
    private void construirIndiceRemissivo(String texto){
        if (texto.length()==0) {
            throw new IllegalArgumentException();
        }
        String[] textoSeparadoPorLinhas = texto.split("\n");
        for (int i = 0; i < textoSeparadoPorLinhas.length; i++) {
            String[] linhaEmPalavras = sanitizarTexto(textoSeparadoPorLinhas[i]);

            for (String palavra : linhaEmPalavras) {

                //pegar inicial de cada palavra

                Character inicial = palavra.toLowerCase().charAt(0);
                int codigoDoCaractere = (int) inicial;
                if(tabela.busca(codigoDoCaractere)!=null){
                    ArvoreBinariaBusca<Palavra> arvoreDaInicial = tabela.busca(codigoDoCaractere).getValor();
                    Palavra novaOcorrencia = new Palavra(palavra);
                    Palavra buscada = arvoreDaInicial.busca(novaOcorrencia);
                    int nLinha = i+1;
                    if(buscada==null){
                        novaOcorrencia.adicionarNumeroDaLinha(nLinha);
                        arvoreDaInicial.insere(novaOcorrencia);
                    }else{
                        if(buscada.getLinhas().ultimo.getElemento()!=nLinha){
                            buscada.adicionarNumeroDaLinha(nLinha);
                        }
                    }
                }
            }
        }
    }
}

