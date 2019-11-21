package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Programa implements AstHtml {

    public Head head; 
    public Body body;

    public Programa(Head head, Body body) {
        this.head = head;
        this.body = body;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    public String toString() {
        return "programa: " + this.head.toString()
            + this.body.toString();
    }
}