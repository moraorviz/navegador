package com.mario.navegador.css.visitor;

import com.mario.navegador.css.ast.*;

public interface Visitor {

    public Object visit(Program p, Object param);
    public Object visit(Regla r, Object param);
    public Object visit(Definicion d, Object param);

}