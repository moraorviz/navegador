package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Parrafo implements AstHtml {

    public H1 h1;
    public H2 h2;
    public P p;

    public Parrafo parrafo(H1 h1, H2 h2, P p) {
        this.h1 = h1;
        this.h2 = h2;
        this.p = p;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        // TODO Auto-generated method stub
        return null;
    }
    
}