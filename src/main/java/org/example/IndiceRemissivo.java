package org.example;

import org.example.TabelaHash.TabelaHash;
import org.example.arvoreBinariaBusca.ArvoreBinariaBusca;

public class IndiceRemissivo {
    private String textoOriginal;
    TabelaHash<Integer, ArvoreBinariaBusca<Palavra>> tabela;

    public IndiceRemissivo(String texto) {
        this.textoOriginal = texto;

        tabela = new TabelaHash<>();
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            tabela.insere(i, new ArvoreBinariaBusca<>());
        }
        construirIndiceRemissivo(texto);
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public static String[] sanitizarTexto(String texto) {
        // Preserva caracteres especiais e apenas separa por espaços.
        return texto.toLowerCase().strip().split("\\s+");
    }

    public void imprimirFormatado() {
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            ArvoreBinariaBusca<Palavra> arvore = tabela.busca(i).getValor();
            if (arvore != null) {
                arvore.imprimirEmOrdem();
            }
        }
    }

    public String buscaNoIndice(String argumentosDeBusca) {
        StringBuilder sb = new StringBuilder();
        String[] argumentosDeBuscaEspacados = argumentosDeBusca.strip().toLowerCase().split("\\s+");

        for (String argumento : argumentosDeBuscaEspacados) {
            int codigoDoCaractere = argumento.charAt(0);
            ArvoreBinariaBusca<Palavra> arvoreDoCaractere = tabela.busca(codigoDoCaractere).getValor();
            if (arvoreDoCaractere != null) {
                Palavra ocorrenciaTeste = new Palavra(argumento);
                Palavra buscado = arvoreDoCaractere.busca(ocorrenciaTeste);
                if (buscado != null) {
                    sb.append(buscado.toString()).append("\n");
                }
            }
        }

        String conteudo = sb.toString();
        if (conteudo.isEmpty()) {
            return "nenhum argumento encontrado!";
        } else {
            return conteudo;
        }
    }

    private void construirIndiceRemissivo(String texto) {
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("O texto não pode estar vazio.");
        }
        String[] textoSeparadoPorLinhas = texto.split("\n");
        for (int i = 0; i < textoSeparadoPorLinhas.length; i++) {
            String[] linhaEmPalavras = sanitizarTexto(textoSeparadoPorLinhas[i]);

            for (String palavra : linhaEmPalavras) {
                Character inicial = palavra.toLowerCase().charAt(0);
                int codigoDoCaractere = (int) inicial;
                if (tabela.busca(codigoDoCaractere) != null) {
                    ArvoreBinariaBusca<Palavra> arvoreDaInicial = tabela.busca(codigoDoCaractere).getValor();
                    Palavra novaOcorrencia = new Palavra(palavra);
                    Palavra buscada = arvoreDaInicial.busca(novaOcorrencia);
                    int nLinha = i + 1;
                    if (buscada == null) {
                        novaOcorrencia.adicionarNumeroDaLinha(nLinha);
                        arvoreDaInicial.insere(novaOcorrencia);
                    } else {
                        if (buscada.getLinhas().ultimo.getElemento() != nLinha) {
                            buscada.adicionarNumeroDaLinha(nLinha);
                        }
                    }
                }
            }
        }
    }
}
