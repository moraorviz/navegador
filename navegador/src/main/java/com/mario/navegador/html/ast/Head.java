package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Head implements AstHtml {

    public Title title;
    public Link link;
    
    @Override
    public Object accept(Visitor v, Object p) {
        // TODO Auto-generated method stub
        return null;
    }
    
}