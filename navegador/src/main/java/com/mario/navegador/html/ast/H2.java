package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class H2 implements AstHtml {

    String h2;
    
    public H2(String h2) {
        this.h2 = h2;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
