package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public interface AstHtml {
    Object accept(Visitor v, Object p);
}