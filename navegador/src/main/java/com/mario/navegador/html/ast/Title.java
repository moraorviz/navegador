package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Title implements AstHtml {

    public String text;

    public Title(String text) {
        this.text = text;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    public String toString() {
        return "title: this.text";
    }
}