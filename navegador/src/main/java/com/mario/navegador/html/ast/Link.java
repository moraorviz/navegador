package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Link implements AstHtml {

    public String href;
    public String rel;
    public String type;

    public Link(String href, String rel, String type) {
        this.href = href;
        this.rel = rel;
        this.type = type;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return v.visit(this, p);
    }

    @Override
    public String toString() {
        return "href: " + this.href +
            "rel: " + this.rel +
            "type: " + this.type + " ";
    }
    
}