package com.mario.navegador.css.parser;

import java.io.FileReader;
import java.io.*;
import java.util.*;

public class Lexicon {

    List<Token> tokens = new ArrayList<Token>();
    int i = 0; //Ultimo token entregado en getToken()
    FileReader filereader;
    boolean charBuffUsed = false;
    char charBuff;
    int line = 1; //Linea del fichero fuente

    HashSet<Character> charText = new HashSet<Character>();

    public Lexicon (FileReader f) {
        filereader = f;
        String lex;

        try {
                char valor = (char) 0;
                while(valor != (char) -1) {
                    valor = nextChar();

                    switch(valor) {
                        case '{':
                            tokens.add(new Token(TokensId.ABRIR, "{", line));
                             break;
                        case ':': 
                            tokens.add(new Token(TokensId.DOSPUNTOS, ":", line));
                             break;
                        case ';':
                            tokens.add(new Token(TokensId.PUNTOYCOMA, ";", line));
                             break;
                        case '}':
                            tokens.add(new Token(TokensId.CERRAR, "}", line));
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
                        // Identificadores, palabras reservadas y el tamano
                        default:
                            if (Character.isDigit(valor)) {
                                lex = getSize(valor + "");
                                tokens.add(new Token(TokensId.SIZE, lex, line));
                            } else {
                                // se llama al metodo getText. Se suma un String vacio al char
                                // para pasar el argumento de char a String
                                lex = getText(valor + "");
                                switch (lex) {
                                    case "h1":
                                        tokens.add(new Token(TokensId.IDENTIFICADOR, "h1", line)) ;
                                        break;
                                    case "h2":
                                        tokens.add(new Token(TokensId.IDENTIFICADOR, "h2", line));
                                        break;
                                    case "p":
                                        tokens.add(new Token(TokensId.IDENTIFICADOR, "p", line));
                                        break;
                                    case "color":
                                        tokens.add(new Token(TokensId.COLOR, "color", line));
                                        break;
                                    case "font-size":
                                        tokens.add(new Token(TokensId.TAMANO, "font-size", line));
                                        break;
                                    case "font-style":
                                        tokens.add(new Token(TokensId.ESTILO, "font-style", line));
                                        break;
                                    case "text-align":
                                        tokens.add(new Token(TokensId.ALINEADO, "text-align", line));
                                        break;
                                    case "green":
                                        tokens.add(new Token(TokensId.GREEN, "green", line));
                                        break;
                                    case "black":
                                        tokens.add(new Token(TokensId.BLACK, "black", line));
                                        break;
                                    case "red":
                                        tokens.add(new Token(TokensId.RED, "red", line));
                                        break;
                                    case "blue":
                                        tokens.add(new Token(TokensId.BLUE, "blue", line));
                                        break;
                                    case "normal":
                                        tokens.add(new Token(TokensId.NORMAL, "normal", line));
                                        break;
                                    case "italic":
                                        tokens.add(new Token(TokensId.ITALIC, "italic", line));
                                        break;
                                    case "bold":
                                        tokens.add(new Token(TokensId.BOLD, "bold", line));
                                        break;
                                    case "underlined":
                                        tokens.add(new Token(TokensId.UNDERLINED, "underlined", line));
                                        break;
                                    case "left":
                                        tokens.add(new Token(TokensId.LEFT, "left", line));
                                        break;
                                    case "right":
                                        tokens.add(new Token(TokensId.RIGHT, "right", line));
                                        break;
                                    case "center":
                                        tokens.add(new Token(TokensId.CENTER, "center", line));
                                        break;
                                }
                            }
                    }
                }
        } catch(IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    /**
     * Operaciones para el Sintactico
     */

    //Devolver el ultimo token
    public void returnLastToken() {
        i--;
    }

    //Get Token
    public Token getToken() {
        if (i < tokens.size()) {
            return tokens.get(i++);
        }
        return new Token(TokensId.EOF, "EOF", line);
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

    // Privadas
    String getSize(String lexStart) throws IOException {
        String lexReturned = lexStart;
        char valor;
        do {
            valor = nextChar();
            lexReturned = lexReturned + (valor);
        } while ((valor != 'p') && (valor != -1));

        if (valor =='p') {
            valor = nextChar();
            if (valor =='x') {
                lexReturned = lexReturned + (valor);
            } else {
                errorLexico("Encontrado " + lexReturned + ". Se esperaba un token SIZE.");
            }
        }
        return lexReturned;
    }

    String getText(String lexStart) throws IOException {
        String lexReturned = lexStart;
        char valor = nextChar();
        while (Character.isDigit(valor) || Character.isAlphabetic(valor) || (valor == '-')) {
            lexReturned = lexReturned + (valor);
            valor = nextChar();
        }
        returnChar(valor);
        return lexReturned;
    }

    void returnChar(char r) {
        charBuffUsed = true;
        charBuff = r;
    }

    void errorLexico(String e) {
        System.out.println("Error lexico en: " + e);
    }
}