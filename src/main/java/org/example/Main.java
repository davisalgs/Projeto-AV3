package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Ler o texto e construir o índice remissivo
            String texto = LeitorArquivos.lerTexto(); // Ler o conteúdo do arquivo texto.txt
            IndiceRemissivo indiceRemissivo = new IndiceRemissivo(texto);

            // Exibir o índice remissivo formatado
            System.out.println("Índice Remissivo:");
            indiceRemissivo.imprimirFormatado();

            // Ler palavras-chave para pesquisa
            String argumentosDeBusca = LeitorArquivos.lerArgumentos(); // Ler palavras-chave do arquivo

            // Buscar palavras no índice
            String resultadoBusca = indiceRemissivo.buscaNoIndice(argumentosDeBusca);
            System.out.println("Resultado da Busca:\n" + resultadoBusca);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
