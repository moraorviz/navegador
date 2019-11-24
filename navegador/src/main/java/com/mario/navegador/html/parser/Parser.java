package com.mario.navegador.html.parser;

import java.util.ArrayList;
import java.util.List;

import com.mario.navegador.html.ast.AstHtml;
import com.mario.navegador.html.ast.Body;
import com.mario.navegador.html.ast.H1;
import com.mario.navegador.html.ast.H2;
import com.mario.navegador.html.ast.Head;
import com.mario.navegador.html.ast.Link;
import com.mario.navegador.html.ast.P;
import com.mario.navegador.html.ast.Parrafo;
import com.mario.navegador.html.ast.Programa;
import com.mario.navegador.html.ast.Title;

public class Parser {
    
    Lexicon lex;
    boolean errorSint = false;

    public Parser (Lexicon lex) {
        this.lex = lex;
    }

    public AstHtml parse() {
        AstHtml astHtml = null;
        astHtml = parseProgram();
        return astHtml;
    }

    Programa parseProgram() {

        Programa prog = null;
        Head head = null; 
        Body body = null;
        Token token = lex.getToken();    

        if (token.getToken() == TokensId.HTML) {
            head = parseHead();
            body = parseBody();
        } else {

        }

        if ((head != null) && (body !=null)
            && (!errorSint)) {
                prog = new Programa(head, body);
            }

        return prog;
    }

    Head parseHead() {

        Head head = null;
        Title title = null;
        Link link = null;

        Token token = lex.getToken();

        if (token.getToken() == TokensId.HEAD) {

            token = lex.getToken();
            while(token.getToken() != TokensId.HEADC) {

                if (token.getToken() == TokensId.TITLE) {
                    title = parseTitle();
                } else if (token.getToken() == TokensId.LINK) {
                    link = parseLink();
                }

                token = lex.getToken();
            }
        }

        head = new Head(title, link);
        return head;
    }

    Title parseTitle() {

        Title title = null;
        String text = "";
        Token token = lex.getToken();

        while(token.getToken() != TokensId.TITLEC) {
            text = text.concat(token.getLexeme());
            token = lex.getToken();
        }

        title = new Title(text);
        return title;
    }

    Link parseLink() {

        Link link = null;
        String href = "";
        String rel = "";
        String type = "";
        Token token = lex.getToken();

        while(token.getToken() != TokensId.HEADC) {

            if (token.getToken() == TokensId.HREF) {
                href = token.getLexeme();
            } else if (token.getToken() == TokensId.REL) {
                rel = token.getLexeme();
            } else if (token.getToken() == TokensId.TYPE) {
                type = token.getLexeme();
            }

            token = lex.getToken();
         }

        lex.returnLastToken();
        link = new Link(href, rel, type);
        return link;
    }

    Body parseBody() {

        Body body = null;
        H1 h1 = null;
        H2 h2 = null;
        P p = null;
        List<Parrafo> parrafos = new ArrayList<Parrafo>();
        Token token = lex.getToken();

        while(token.getToken() != TokensId.BODYC) {

            if (token.getToken() == TokensId.H1) {
                h1 = getParrafoH1();
                parrafos.add(h1);
            } else if (token.getToken() == TokensId.H2) {
                h2 = getParrafoH2();
                parrafos.add(h2);
            } else if (token.getToken() == TokensId.P) {
                p = getParrafo();
                parrafos.add(p);
            }

            token = lex.getToken();
        }

        body = new Body(parrafos);
        return body;
    }

    H1 getParrafoH1() {

        H1 h1 = null;
        String texto = "";
        Token token = lex.getToken();

        while(token.getToken() != TokensId.H1C) {
            texto = texto.concat(token.getLexeme());
            token = lex.getToken();
        }

        h1 = new H1(texto);
        return h1;
    }

    H2 getParrafoH2() {

        H2 h2 = null;
        String texto = "";
        Token token = lex.getToken();

        while(token.getToken() != TokensId.H2C) {
            texto = texto.concat(token.getLexeme());
            token = lex.getToken();
        }

        h2 = new H2(texto);
        return h2;
    }

    P getParrafo() {

        P p = null;
        String texto = "";
        Token token = lex.getToken();

        while(token.getToken() != TokensId.PC) {

            if (token.getToken() == TokensId.TEXT) {
                texto = texto.concat(token.getLexeme());
            }

            token = lex.getToken();
        }

        p = new P(texto, null);
        return p;
    }

    void errorSint(String e, int line) {
        errorSint = true;
        System.out.println("Error Sintactico: " + e + " en la linea " + line);
    }
}