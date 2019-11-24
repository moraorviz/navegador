package com.mario.navegador.css.parser;

import java.util.List;
import java.util.ArrayList;

import com.mario.navegador.css.ast.*;

public class Parser {

    Lexicon lex;
    boolean errorSint = false;

    public Parser (Lexicon lex) {
        this.lex = lex;
    }

    public AstCss parse () {
        AstCss ast = null;
        ast = parseProgram();
        return ast;
    }

    // Parser recursivo descendente
    Program parseProgram() {

        Program prog = null;
        List<Regla> reglas = new ArrayList<Regla>();
        Token token = lex.getToken();

        while(token.getToken() == TokensId.IDENTIFICADOR) {

            lex.returnLastToken();
            String ident = lex.getToken().getLexeme();
            Regla regla = parseRegla(ident);
            token = lex.getToken();

            if ((regla != null) && (!errorSint)) {
                reglas.add(regla);
            }
        }

        prog = new Program(reglas) ;
        return prog;
    }

    Regla parseRegla(String ident) {
        Regla regla = null;
        List<Definicion> definiciones = new ArrayList<Definicion>();
        Token token = lex.getToken();

        while(token.getToken() != TokensId.IDENTIFICADOR) {
            lex.returnLastToken();
            Definicion definicion = parseDefinicion();

            if ((definicion != null) && (!errorSint)) {
                definiciones.add(definicion);
            }

            if (token.getToken() != TokensId.EOF)
                token = lex.getToken();
            else
                break;
        }

        lex.returnLastToken();
        regla = new Regla(ident, definiciones);
        return regla;
    }

    Definicion parseDefinicion() {
        Token token = lex.getToken();

        switch(token.getToken()) {
            case COLOR:
                if (lex.getToken().getToken() == TokensId.DOSPUNTOS)
                    return new Definicion("color", parseColors());
                errorSintactico("Error", token.getLine());
                return null;
            case ESTILO:
                if (lex.getToken().getToken() == TokensId.DOSPUNTOS)
                    return new Definicion("font-style", parseStyles());
                errorSintactico("Error", token.getLine());
                return null;
            case ALINEADO:
                if (lex.getToken().getToken() == TokensId.DOSPUNTOS)
                    return new Definicion("text-align", parseAlineado());
                errorSintactico("Error", token.getLine());
                return null;
            case TAMANO:
                if (lex.getToken().getToken() == TokensId.DOSPUNTOS)
                    return new Definicion("font-size", parseSize());
            case ABRIR: 
                return null;
            case CERRAR:
                return null;
            case PUNTOYCOMA:
                return null;
            default:
                errorSintactico("Encontrado " + token.getLexeme() + 
                ". Se esperaba una definicion del tipo...",
                token.getLine());
                return null;
        }
    }

    String parseStyles() {
        Token token = lex.getToken();

        switch(token.getToken()) {
            case NORMAL:
                return("normal");
            case ITALIC:
                return("italic");
            case BOLD:
                return("bold");
            case UNDERLINED:
                return("underlined");
            default:
                errorSintactico("Encontrado " + token.getLexeme() +
                    ". Se esperaba un estilo de texto: normal, underlined, italic o bold",
                    token.getLine());
                return null;
        }
    }

    String parseColors() {
        Token token = lex.getToken();

        switch(token.getToken()) {
            case BLACK:
                return("black");
            case BLUE:
                return("blue");
            case GREEN:
                return("green");
            case RED:
                return("red");
            default:
                errorSintactico("encontrado " + token.getLexeme() +
                    ". Se esperaba un color: black, blue, green o red",
                    token.getLine());
                return null;
        }
    }

    String parseAlineado() {
        Token token = lex.getToken();

        switch(token.getToken()) {
            case CENTER:
                return("center");
            case LEFT:
                return("left");
            case RIGHT:
                return("right");
            default:
                errorSintactico("encontrado " + token.getLexeme() +
                ". Se esperaba un alineado: center, left o right",
                token.getLine());
                return null;
        }
    }

    String parseSize() {
        Token token = lex.getToken();
        String size = token.getLexeme();
        return size;
    }

    //Gestion de errores del Sintactico
    void errorSintactico(String e, int line) {
        errorSint = true;
        System.out.println("Error Sintactico: " + e + " en la linea " + line);
    }
}