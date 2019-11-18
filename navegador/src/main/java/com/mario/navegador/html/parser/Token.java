package com.mario.navegador.html.parser;

public class Token {

    TokensId token;
    String lexeme;
    int line;

    public Token(final TokensId token, final String lexeme, final int line) {
        this.token = token;
        this.lexeme = lexeme;
        this.line = line;
    }

    public TokensId getToken() {
        return token;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    public String toString() {

        // TODO: poner la salida en columnas

        return "TOKEN: " + token + " - LEXEMA: " + lexeme + " - LINE: " + line;
    }
}