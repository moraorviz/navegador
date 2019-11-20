package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Programa implements AstHtml {

    public Head head; 
    public Body body;

    @Override
    public Object accept(Visitor v, Object p) {
        // TODO Auto-generated method stub
        return null;
    }

}