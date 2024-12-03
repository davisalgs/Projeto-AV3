package org.example;
import java.io.FileWriter;
import java.io.IOException;

public class Saida {
    public static void saida(String indiceRemissivo) throws IOException{
    FileWriter fileWrite = new FileWriter("C:\\Users\\salga\\Arquivos-AV3\\indice-remissivo.txt");
    fileWrite.write(indiceRemissivo);
    fileWrite.close();

  }
}
