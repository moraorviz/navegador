package com.mario.navegador.css.ast;

import com.mario.navegador.css.visitor.Visitor;

public class Definicion implements AstCss {

    public String varconf;
    public String value;

    public Definicion(String varconf, String value) {
        this.varconf = varconf;
        this.value = value;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    public String toString() {
        return "varconf: " + varconf + "; value: " + value + "\n";
    }
}