package com.mario.navegador.css.ast;

import java.util.List;

import com.mario.navegador.css.visitor.Visitor;

public class Program implements AstCss {

    public List<Regla> reglas;

    public Program(List<Regla> reglas) {
        this.reglas = reglas;
    }

    @Override
    public Object accept(Visitor v, Object param) {
        return v.visit(this, param);
    }

    @Override
    public String toString() {
        return "program: " + reglas.toString();
    }
}