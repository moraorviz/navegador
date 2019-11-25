package com.mario.navegador.html.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lexicon {

    List<Token> tokens = new ArrayList<Token>();
    int i = 0; //ultimo token recogido
    FileReader filereader;
    boolean charBuffUsed = false;
    char charBuff;
    int line = 1;
    HashSet<Character> charText = new HashSet<Character>();

    public Lexicon (FileReader f) {

        filereader = f;
        String lex;

        try {

            char valor = (char) 0;
            char cerrarLlave;

            while (valor != (char) -1) {
                valor = nextChar();

                switch(valor) {
                    case '<':
                        valor = nextChar();

                        if (valor != '/') {

                            String contEtiq = getTextoEtiqueta(valor + "");
                            lex = "<" + contEtiq + ">";

                            switch(contEtiq) {
                                case "html":
                                    tokens.add(new Token(TokensId.HTML, lex, line));
                                    break;
                                case "head":
                                    tokens.add(new Token(TokensId.HEAD, lex, line));
                                    break;
                                case "title":
                                    tokens.add(new Token(TokensId.TITLE, lex, line));
                                    cerrarLlave = nextChar();

                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();
                                        String textoInterior = getTextoInterior(primeraLetra);
                                        tokens.add(new Token(TokensId.TEXT, textoInterior, line));
                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                                case "link":
                                    tokens.add(new Token(TokensId.LINK, lex, line));
                                    break;
                                case "body":
                                    tokens.add(new Token(TokensId.BODY, lex, line));
                                    break;
                                case "h1":
                                    tokens.add(new Token(TokensId.H1, lex, line));
                                    cerrarLlave = nextChar();

                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();
                                        String textoInterior = getTextoInterior(primeraLetra);
                                        tokens.add(new Token(TokensId.TEXT, textoInterior, line));
                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                                case "h2":
                                    tokens.add(new Token(TokensId.H2, lex, line));
                                    cerrarLlave = nextChar();

                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();
                                        String textoInterior = getTextoInterior(primeraLetra);
                                        lex = textoInterior;
                                        tokens.add(new Token(TokensId.TEXT, lex, line));
                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                                case "p":
                                    tokens.add(new Token(TokensId.P, lex, line));
                                    cerrarLlave = nextChar();

                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();

                                        if (Character.isAlphabetic(primeraLetra)) {
                                            String textoInterior = getTextoInterior(primeraLetra);
                                            lex = textoInterior;
                                            tokens.add(new Token(TokensId.TEXT, lex, line));
                                        } else 
                                            returnChar(primeraLetra);
                                            break;

                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                                case "b":
                                    tokens.add(new Token(TokensId.BOLD, lex, line));
                                    cerrarLlave = nextChar();

                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();
                                        String textoInterior = getTextoInterior(primeraLetra);
                                        lex = textoInterior;
                                        tokens.add(new Token(TokensId.TEXT, lex, line));
                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                                case "i":
                                    tokens.add(new Token(TokensId.ITALIC, lex, line));
                                    cerrarLlave = nextChar();

                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();
                                        String textoInterior = getTextoInterior(primeraLetra);
                                        lex = textoInterior;
                                        tokens.add(new Token(TokensId.TEXT, lex, line));
                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                                case "u":
                                    tokens.add(new Token(TokensId.UNDERL, lex, line));
                                    cerrarLlave = nextChar();

                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();
                                        String textoInterior = getTextoInterior(primeraLetra);
                                        lex = textoInterior;
                                        tokens.add(new Token(TokensId.TEXT, lex, line));
                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                            }
                        } else if (valor == '/') {

                            String contEtiq = getTextoEtiqueta(valor + "");
                            lex = "</" + contEtiq + ">";

                            switch(contEtiq) {
                                case "html":
                                    tokens.add(new Token(TokensId.HTMLCLOSE, lex, line));
                                    break;
                                case "head":
                                    tokens.add(new Token(TokensId.HEADC, lex, line));
                                    break;
                                case "title":
                                    tokens.add(new Token(TokensId.TITLEC, lex, line));
                                    break;
                                case "body":
                                    tokens.add(new Token(TokensId.BODYC, lex, line));
                                    break;
                                case "h1":
                                    tokens.add(new Token(TokensId.H1C, lex, line));
                                    break;
                                case "h2":
                                    tokens.add(new Token(TokensId.H2C, lex, line));
                                    break;
                                case "p":
                                    tokens.add(new Token(TokensId.PC, lex, line));
                                    break;
                                case "b":
                                    tokens.add(new Token(TokensId.BOLDC, lex, line));
                                    break;
                                case "i":
                                    tokens.add(new Token(TokensId.ITALICC, lex, line));
                                    break;
                                case "u":
                                    tokens.add(new Token(TokensId.UNDERLC, lex, line));
                                    break;
                            }
                        }
                    case '>':
                        break;
                    case '\n':
                        line++;
                        break;
                    case '\r':
                        break;
                    case '\t':
                        break;
                    case ' ':
                        break;
                    case (char) -1:
                        break;
                    default:

                        if (Character.isAlphabetic(valor)
                            || ".".equals(valor + "")) {

                            String palabra = "";

                            while(Character.isAlphabetic(valor)
                                || Character.isDigit(valor)
                                || ".".equals(valor + "")
                                || "=".equals(valor + "")
                                || "/".equals(valor + "")
                                || "\"".equals(valor + "")) {

                                palabra = palabra.concat(valor + "");
                                valor = nextChar();
                            }

                            if (palabra.contains("=")) {

                                String token = "";
                                String lexema = "";

                                int posicionIgual = palabra.indexOf("=");
                                token = palabra.substring(0, posicionIgual);
                                lexema = palabra.substring(posicionIgual + 2, palabra.length() - 1);

                                switch(token) {
                                    case "href":
                                        tokens.add(new Token(TokensId.HREF, lexema, line));
                                        break;
                                    case "rel":
                                        tokens.add(new Token(TokensId.REL, lexema, line));
                                        break;
                                    case "type":
                                        tokens.add(new Token(TokensId.TYPE, lexema, line));
                                        break;
                                }

                            } else {
                                returnChar(valor);
                                tokens.add(new Token(TokensId.TEXT, palabra, line));
                            }
                        }
                }
            }
            
        } catch(IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    public Token getToken() {

        if (i < tokens.size()) {
            return tokens.get(i++);
        }

        return new Token(TokensId.EOF, "EOF", line);
    }

    public void returnLastToken() {
        i--;
    }

    char nextChar() throws IOException {

        if (charBuffUsed) {
            charBuffUsed = false;
            return charBuff;
        } else {
            int valor = filereader.read();
            return ((char) valor);
        }
    }

    void returnChar(char r) {
        charBuffUsed = true;
        charBuff = r;
    }

    String getTextoInterior(char primerCaracter) throws IOException {

        List<String> listaPalabras = new ArrayList<String>();
        String puntuacion = ".,:;";
        String palabra = primerCaracter + "";
        char caracterSiguiente = nextChar();
        String texto = "";

        while (Character.isDigit(caracterSiguiente)
            || Character.isAlphabetic(caracterSiguiente)
            || puntuacion.contains(caracterSiguiente + "") 
            || caracterSiguiente == ' '
            || caracterSiguiente == '<') { 

            if (caracterSiguiente == ' '
                || caracterSiguiente == '<') {
                listaPalabras.add(palabra);
                palabra = "";
            } else {
                palabra += caracterSiguiente + "";
            }

            if (caracterSiguiente == '<')
                break;

            caracterSiguiente = nextChar();
        }

        returnChar(caracterSiguiente);

        for (String s: listaPalabras) {
            texto = texto.concat(" " + s);
        }

        return texto.trim();
    }

    /**
     * Devolver el contenido de la etiqueta hasta el 
     * simbolo de cierre '>'. Se devuelve como cadena
     * de caracteres (String)
     * @param letraInicio La primera letra despues del simbolo
     * de apertura de etiqueta '<'
     * @return String contenidoEtiqueta El contenido de la etiqueta
    */
    String getTextoEtiqueta(String letraInicio) throws IOException {

        String contenidoEtiqueta = "";
        char siguienteCaracter = '\0';

        if (!letraInicio.equals("/")) {
            contenidoEtiqueta = letraInicio;
        } else {
            contenidoEtiqueta = nextChar() + "";
        }

        siguienteCaracter = nextChar();

        while(Character.isDigit(siguienteCaracter)
            || Character.isAlphabetic(siguienteCaracter)) {
                contenidoEtiqueta += siguienteCaracter;
                siguienteCaracter = nextChar();
        }

        returnChar(siguienteCaracter);
        return contenidoEtiqueta;
    }

    public void reset() {
        this.i = 0;
    }

    void errorLexico(String e) {
        System.out.println("Error lexico en: " + e);
    }
}