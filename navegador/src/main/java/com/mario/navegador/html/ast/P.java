package com.mario.navegador.html.ast;

import java.util.List;

import com.mario.navegador.html.visitor.Visitor;

public class P implements AstHtml, Parrafo {

    public String text;
    public List<Bloque> bloques;

    public P(String text, List<Bloque> bloques) {
        this.text = text;
        this.bloques = bloques;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    @Override
    public String toString() {
        return "p: " + text;
    }
    
}