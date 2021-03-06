package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class H1 implements AstHtml, Parrafo {

    public String text;

    public H1(String text) {
        this.text = text;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    @Override
    public String toString() {
        return "h1: " + this.text;
    }
}