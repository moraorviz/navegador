package com.mario.navegador.main;

import java.io.File;
import java.net.URL;

import com.mario.navegador.html.parser.Lexicon;
import com.mario.navegador.html.parser.Token;
import com.mario.navegador.html.parser.TokensId;

public class Utils {

    static void listaTokensHtml(Lexicon lex) {
        Token t = lex.getToken();

        while (t.getToken() != TokensId.EOF) {
            System.out.println(t.toString());
            t = lex.getToken();
        }

        System.out.println("\nFin de fichero. \n" + t.toString());
    }

    static void listaTokensCss(com.mario.navegador.css.parser.Lexicon lex) {

        com.mario.navegador.css.parser.Token t = lex.getToken();

        while (t.getToken() != com.mario.navegador.css.parser.TokensId.EOF) {
            System.out.println(t.toString());
            t = lex.getToken();
        }
    }

    public File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}