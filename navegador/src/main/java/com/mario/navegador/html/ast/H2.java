package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class H2 implements AstHtml, Parrafo {

    public String text;
    
    public H2(String text) {
        this.text = text;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    @Override
    public String toString() {
        return "h2: " + this.text;
    }
}
