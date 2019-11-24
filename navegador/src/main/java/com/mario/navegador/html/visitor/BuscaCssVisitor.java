package com.mario.navegador.html.visitor;

import com.mario.navegador.html.ast.Bloque;
import com.mario.navegador.html.ast.Body;
import com.mario.navegador.html.ast.H1;
import com.mario.navegador.html.ast.H2;
import com.mario.navegador.html.ast.Head;
import com.mario.navegador.html.ast.Link;
import com.mario.navegador.html.ast.P;
import com.mario.navegador.html.ast.Parrafo;
import com.mario.navegador.html.ast.Programa;
import com.mario.navegador.html.ast.Title;

public class BuscaCssVisitor implements Visitor {

    @Override
    public Object visit(Parrafo p, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Head h, Object param) {

        Link link = h.link;
        String result = (String) link.href;

        return result;
    }

    @Override
    public Object visit(H1 h1, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(H2 h2, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Title t, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(P p, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Body b, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Link l, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Programa p, Object param) {

        Head head = p.head;
        String file = (String) head.accept(this, null);

        return file;
    }

    @Override
    public Object visit(Bloque b, Object param) {
        // TODO Auto-generated method stub
        return null;
    }
}