package com.mario.navegador.css.visitor;

import com.mario.navegador.css.ast.Definicion;
import com.mario.navegador.css.ast.Program;
import com.mario.navegador.css.ast.Regla;

public class PrintCssAstVisitor implements Visitor {

    String sp = "   ";

    @Override
    public Object visit(Program p, Object param) {
        String sd = "", sr;
        for (Regla r: p.reglas) {
            sd = sd + (String) r.accept(this, sp);
        }
        sr = "(CSS declarations\n" + sd + ")";
        return sr;
    }

    @Override
    public Object visit(Regla r, Object param) {
        String spar = "", sr;
        for (Definicion d: r.definiciones) {
            spar = spar + (String) d.accept(this, (String) param + sp);
        }
        sr = (String) param + "(" + r.ident + "\n" + spar + (String) param + ")\n"; 
        return sr;
    }

    @Override
    public Object visit(Definicion d, Object param) {
        return (String) param + d.varconf + " --> " + d.value + "\n";
    }


}