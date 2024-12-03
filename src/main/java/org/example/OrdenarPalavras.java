package org.example;

public class OrdenarPalavras {
    public static void ordenarPalavras(String[] palavras) {

        int inicio = 0;
        int fim = palavras.length - 1;
        _ordenarPalavras(palavras, inicio, fim);
    }

    private static void _ordenarPalavras(String[] palavras, int inicio, int fim) {
        if (fim <= inicio) {
            return;
        }
        int pivo = _partition(palavras, inicio, fim);
        _ordenarPalavras(palavras, pivo + 1, fim);
        _ordenarPalavras(palavras, inicio, pivo - 1);

    }

    private static int _partition(String[] palavras, int inicio, int fim) {
        String pivo = palavras[fim];
        int i = inicio - 1;

        for (int j = inicio; j <= fim - 1; j++) {
            if (palavras[j].compareTo(pivo) < 0) {
                i++;
                String auxiliar = palavras[j];
                palavras[j] = palavras[i];
                palavras[i] = auxiliar;
            }
        }

        i++;
        String aux = palavras[i];
        palavras[i] = palavras[fim];
        palavras[fim] = aux;

        return i;
    }


}
