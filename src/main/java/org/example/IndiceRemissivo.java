package org.example;

import org.example.TabelaHash.TabelaHash;
import org.example.arvoreBinariaBusca.ArvoreBinariaBusca;

public class IndiceRemissivo {

    private String textoOriginal;  // texto original que será indexado
    TabelaHash<Integer, ArvoreBinariaBusca<Palavra>> tabela; // tabela hash para indexar ds palavras, usando a letra inicial da palavra como chave.

    //  inicializa a tabela de hash e insere árvores binárias para cada letra do alfabeto.
    public IndiceRemissivo(String texto) {
        this.textoOriginal = texto;


        tabela = new TabelaHash<>();


        //tabela.insere((int) 'á', new ArvoreBinariaBusca<>() );
        //tabela.insere((int) 'Á', new ArvoreBinariaBusca<>() );

        // insere árvores binárias para as letras de 'a' a 'z'
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            tabela.insere(i, new ArvoreBinariaBusca<>());
        }

        // constrói o índice remissivo para o texto
        construirIndiceRemissivo(texto);
    }


    public String getTextoOriginal() {
        return textoOriginal;
    }

    // converte para minúsculas e separa as palavras por espaços
    public static String[] sanitizarTexto(String texto) {
        // preserva caracteres especiais e apenas separa por espaços.
        return texto.toLowerCase().strip().split("\\s+");
    }

    // imprime o índice remissivo de em ordem alfabética
    public void imprimirFormatado() {
        // para cada letra do alfabeto (a-z), imprime as ocorrências na árvore
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            ArvoreBinariaBusca<Palavra> arvore = tabela.busca(i).getValor();
            if (arvore != null) {
                arvore.imprimirEmOrdem();
            }
        }
    }

    // realiza uma busca no índice remissivo
    public String buscaNoIndice(String argumentosDeBusca) {
        StringBuilder sb = new StringBuilder();

        // converte para minúsculas e divide em palavras
        String[] argumentosDeBuscaEspacados = argumentosDeBusca.strip().toLowerCase().split("\\s+");

        // para cada argumento de busca (palavra), tenta encontrar no índice
        for (String argumento : argumentosDeBuscaEspacados) {
            int codigoDoCaractere = argumento.charAt(0);  // Usa a primeira letra para buscar na tabela hash
            ArvoreBinariaBusca<Palavra> arvoreDoCaractere = tabela.busca(codigoDoCaractere).getValor();
            if (arvoreDoCaractere != null) {
                Palavra ocorrenciaTeste = new Palavra(argumento); // Cria um objeto Palavra com o argumento
                Palavra buscado = arvoreDoCaractere.busca(ocorrenciaTeste);  // Busca a palavra na árvore binária
                if (buscado != null) {
                    sb.append(buscado.toString()).append("\n");  // Adiciona o resultado da busca ao StringBuilder
                }
            }
        }

        // se algum resultado foi encontrado, retorna. se não, retorna uma mensagem padrão.
        String conteudo = sb.toString();
        if (conteudo.isEmpty()) {
            return "nenhum argumento encontrado!";

        } else {
            return conteudo;

        }
    }

    // construir o índice remissivo a partir do texto
    private void construirIndiceRemissivo(String texto) {

        if (texto.isEmpty()) {
            throw new IllegalArgumentException("O texto não pode estar vazio.");
        }


        String[] textoSeparadoPorLinhas = texto.split("\n");

        // para cada linha, processa palavra por palavra
        for (int i = 0; i < textoSeparadoPorLinhas.length; i++) {
            // separa as palavras da linha
            String[] linhaEmPalavras = sanitizarTexto(textoSeparadoPorLinhas[i]);

            // para cada palavra da linha
            for (String palavra : linhaEmPalavras) {
                // obtem a primeira letra da palavra (convertida para minúscula)
                Character inicial = palavra.toLowerCase().charAt(0);
                int codigoDoCaractere = (int) inicial;

                // busca a árvore binária correspondente à primeira letra da palavra
                if (tabela.busca(codigoDoCaractere) != null) {
                    ArvoreBinariaBusca<Palavra> arvoreDaInicial = tabela.busca(codigoDoCaractere).getValor();
                    Palavra novaOcorrencia = new Palavra(palavra);  // Cria uma nova instância de Palavra com a palavra atual
                    Palavra buscada = arvoreDaInicial.busca(novaOcorrencia);  // Verifica se a palavra já existe na árvore

                    int nLinha = i + 1;  // armazena o número da linha (começando de 1)

                    if (buscada == null) {
                        // se a palavra não foi encontrada, adiciona o número da linha e insere a palavra na árvore
                        novaOcorrencia.adicionarNumeroDaLinha(nLinha);
                        arvoreDaInicial.insere(novaOcorrencia);

                    } else {
                        // se a palavra já existe, apenas adiciona o número da linha se não for repetido
                        if (buscada.getLinhas().ultimo.getElemento() != nLinha) {
                            buscada.adicionarNumeroDaLinha(nLinha);
                        }
                    }
                }
            }
        }
    }
}
