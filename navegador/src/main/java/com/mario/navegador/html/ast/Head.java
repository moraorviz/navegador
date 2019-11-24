package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Head implements AstHtml {

    public Title title;
    public Link link;

    public Head(Title title, Link link) {
        this.title = title;
        this.link = link;
    }
    
    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    @Override
    public String toString() {
        return "head: " + this.title.toString() +
            this.link.toString();
    }
}