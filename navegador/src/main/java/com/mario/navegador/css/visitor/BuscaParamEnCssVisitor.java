package com.mario.navegador.css.visitor;

import com.mario.navegador.css.ast.AstCss;
import com.mario.navegador.css.ast.Definicion;
import com.mario.navegador.css.ast.Program;
import com.mario.navegador.css.ast.Regla;

public class BuscaParamEnCssVisitor implements Visitor {

    String ident = null;
    String label = null;

    @Override
    public Object visit(Program p, Object param) {
        for (Regla r: p.reglas) {
            if (r.ident.equals(ident))
                return (String) r.accept(this, null);
        }
        return null;
    }

    @Override
    public Object visit(Regla r, Object param) {
        for (Definicion d: r.definiciones) {
            if (d.varconf.equals(label))
                return (String) d.accept(this, null);
        }
        return null;
    }

    @Override
    public Object visit(Definicion d, Object param) {
        return d.value;
    }

    public String search(String ident, String label, AstCss prog) {

        this.ident = ident;
        this.label = label;

        if ((ident == null) || (label==null))
            return null;
        
        return (String) prog.accept(this, null);
    }
}