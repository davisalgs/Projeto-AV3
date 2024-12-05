package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            //ler o texto e construir o índice remissivo
            String texto = LeitorArquivos.lerTexto(); // Ler o conteúdo do arquivo texto.txt
            IndiceRemissivo indiceRemissivo = new IndiceRemissivo(texto);

            //exibir o índice remissivo formatado
            System.out.println("Índice Remissivo:");
            indiceRemissivo.imprimirFormatado();


            //ler palavras-chave para pesquisa
            String argumentosDeBusca = LeitorArquivos.lerArgumentos(); // Ler palavras-chave do arquivo

            //buscar palavras no índice
            String resultadoBusca = indiceRemissivo.buscaNoIndice(argumentosDeBusca);
            System.out.println("Resultado da Busca:\n" + resultadoBusca);
            Saida.saida(resultadoBusca);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
