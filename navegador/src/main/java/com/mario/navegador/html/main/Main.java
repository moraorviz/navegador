package com.mario.navegador.html.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import com.mario.navegador.html.ast.AstHtml;
import com.mario.navegador.html.ast.Programa;
import com.mario.navegador.html.parser.Lexicon;
import com.mario.navegador.html.parser.Parser;
import com.mario.navegador.html.parser.Token;
import com.mario.navegador.html.parser.TokensId;

public class Main {

    public static void main(String [] args) throws IOException {
        System.out.println("Ejecutando Main del html\n");
        // Leer del ficher fuente
        Main app = new Main();
        File file = app.getFileFromResources("EX4simple.html");
        FileReader fileReader = new FileReader(file);

        // Analisis lexico
        Lexicon lex = new Lexicon(fileReader);
        // Imprimir los tokens
        // Comprobar la salida con el fichero de ejemplo EX4.html para ver
        // que se devuelven todos correctamente
        //listaTokens(lex);
        Parser parser = new Parser(lex);
        AstHtml astHtml = parser.parse();
        System.out.println(astHtml.toString());
    }

    // Metodo auxiliar para imprimir los tokens
    static void listaTokens(Lexicon lex) {
        Token t = lex.getToken();
        while (t.getToken() != TokensId.EOF) {
            System.out.println(t.toString());
            t = lex.getToken();
        }
        // Devuelve token fin de fichero si ya no quedan mas
        System.out.println("\nFin de fichero. \n" + t.toString());
    }

    // Metodo auxiliar para coger el fichero de la carpeta
    // resources
    File getFileFromResources(String fileName) {

        // TODO: revisar la manera de recuperar los ficheros del sistema de archivos
        // Fuente: (mykong.com)

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

}