package com.mario.navegador.css.ast;

import java.util.List;

import com.mario.navegador.css.visitor.Visitor;

public class Regla implements AstCss {

    public String ident;
    public List<Definicion> definiciones;

    public Regla(String ident, List<Definicion> definiciones) {
        this.ident = ident;
        this.definiciones = definiciones;
    }

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    public String toString() {
        return "ident: " + ident + ", definiciones: " + definiciones.toString() + "\n";
    }
}