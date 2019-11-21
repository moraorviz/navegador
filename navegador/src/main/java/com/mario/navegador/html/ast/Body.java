package com.mario.navegador.html.ast;

import java.util.List;

import com.mario.navegador.html.visitor.Visitor;

public class Body implements AstHtml {

    List<Parrafo> parrafos;

    public Body(List<Parrafo> parrafos) {
        this.parrafos = parrafos;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    public String toString() {
        return "parrafos: " + parrafos.toString();
    }

}