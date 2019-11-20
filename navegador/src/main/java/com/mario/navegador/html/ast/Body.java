package com.mario.navegador.html.ast;

import java.util.List;

import com.mario.navegador.html.visitor.Visitor;

public class Body implements AstHtml {

    List<Parrafo> parrafos;

    @Override
    public Object accept(Visitor v, Object p) {
        // TODO Auto-generated method stub
        return null;
    }

}