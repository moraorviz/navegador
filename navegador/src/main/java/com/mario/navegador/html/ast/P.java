package com.mario.navegador.html.ast;

import java.util.ArrayList;
import java.util.List;

import com.mario.navegador.html.visitor.Visitor;

public class P implements AstHtml, Parrafo {

    public List<Bloque> bloques = new ArrayList<Bloque>();

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    @Override
    public String toString() {
        return "p: " + bloques.toString();
    }
}