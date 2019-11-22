package com.mario.navegador.html.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import com.mario.navegador.html.ast.AstHtml;
import com.mario.navegador.html.parser.Lexicon;
import com.mario.navegador.html.parser.Parser;
import com.mario.navegador.html.parser.Token;
import com.mario.navegador.html.parser.TokensId;
import com.mario.navegador.html.visitor.BuscaCss;
import com.mario.navegador.html.visitor.PrintASTVisitor;

public class Main {

    public static void main(String [] args) throws IOException {

        System.out.println("Ejecutando Main del html\n");

        // Leer del fichero fuente
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

        //Imprimir el sintactico con toString
        System.out.println(astHtml.toString());

        //Imprimir AST
        System.out.println("===== Pintando con el visitor =====");
        PrintASTVisitor printer = new PrintASTVisitor();
        String representacionArbol = (String) astHtml.accept(printer, null);
        System.out.println(representacionArbol);

        //BuscaCSS
        System.out.println("==== Probando BuscaCSS ====");
        BuscaCss buscaCss = new BuscaCss();
        String ruta = (String) astHtml.accept(buscaCss, null);
        System.out.println(ruta);

        //Renderiza html
        System.out.println("==== Renderizando HTML ======");
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

    File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}