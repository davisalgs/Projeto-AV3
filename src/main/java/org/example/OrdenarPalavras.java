package org.example;


public class OrdenarPalavras {


    public static void ordenarPalavras(String[] palavras) {
        // definindo o índice inicial (0) e final (tamanho do array - 1)
        int inicio = 0;
        int fim = palavras.length - 1;

        // chama o método recursivo para ordenar as palavras no intervalo [inicio, fim]
        _ordenarPalavras(palavras, inicio, fim);
    }

    // método recursivo privado que aplica o Quicksort em um intervalo do array
    private static void _ordenarPalavras(String[] palavras, int inicio, int fim) {
        // se a sublista tem 0 ou 1 elementos, não há necessidade de ordenar
        if (fim <= inicio) {
            return;
        }

        // divide a lista e encontra a posição do pivô após o particionamento
        int pivo = _partition(palavras, inicio, fim);

        // chama recursivamente o Quicksort para a sublista à direita do pivô
        _ordenarPalavras(palavras, pivo + 1, fim);

        // chama recursivamente o Quicksort para a sublista à esquerda do pivô
        _ordenarPalavras(palavras, inicio, pivo - 1);
    }

    // método privado que realiza o particionamento do array em torno de um pivô
    private static int _partition(String[] palavras, int inicio, int fim) {
        // O pivô é o último elemento da sublista
        String pivo = palavras[fim];

        // i indica a posição do último elemento menor que o pivô
        int i = inicio - 1;

        // percorre a sublista [inicio, fim-1] e reorganiza os elementos
        for (int j = inicio; j <= fim - 1; j++) {
            // Compara a palavra na posição j com o pivô
            if (palavras[j].compareTo(pivo) < 0) {
                // se a palavra na posição j for menor que o pivô, incrementa i
                i++;

                // Troca palavras[j] com palavras[i]
                String auxiliar = palavras[j];
                palavras[j] = palavras[i];
                palavras[i] = auxiliar;
            }
        }

        // coloca o pivô na posição correta, trocando com o elemento na posição i + 1
        i++;
        String aux = palavras[i];
        palavras[i] = palavras[fim];
        palavras[fim] = aux;

        // retorna a posição do pivô após a troca
        return i;
    }
}
