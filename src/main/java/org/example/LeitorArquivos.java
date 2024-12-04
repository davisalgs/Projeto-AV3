package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LeitorArquivos {
    public static String lerTexto() throws IOException {
        List<String> linhas = Files.readAllLines(Paths.get("src","main","java","org","example","texto.txt"), StandardCharsets.UTF_8);

        return String.join("\n", linhas);
    }

    public static String lerArgumentos() throws IOException {
        List<String> linhas = Files.readAllLines(Paths.get("src","main","java","org","example","palavras-chave.txt"), StandardCharsets.UTF_8);
        //List<String> linhas = Files.readAllLines(Paths.get("D:\\Projetos\\Java\\Projeto-AV3-ED\\src\\main\\java\\org\\example\\palavras-chave.txt"), StandardCharsets.UTF_8);


        return String.join("\n", linhas);
    }
}