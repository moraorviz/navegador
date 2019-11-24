package com.mario.navegador.html.visitor;

import com.mario.navegador.html.ast.Bloque;
import com.mario.navegador.html.ast.Body;
import com.mario.navegador.html.ast.H1;
import com.mario.navegador.html.ast.H2;
import com.mario.navegador.html.ast.Head;
import com.mario.navegador.html.ast.Link;
import com.mario.navegador.html.ast.P;
import com.mario.navegador.html.ast.Parrafo;
import com.mario.navegador.html.ast.Programa;
import com.mario.navegador.html.ast.Title;

public class PrintASTVisitor implements Visitor {

    @Override
    public Object visit(Parrafo p, Object param) {
        return null;
    }

    @Override
    public Object visit(Head h, Object param) {

        Title title = h.title;
        Link link = h.link;
        String titleString = (String) title.accept(this, null);
        String linkRepr = (String) link.accept(this, null);

        String result= "title= " + titleString + "\n"
            + "link= " + linkRepr;

        return result;
    }

    @Override
    public Object visit(H1 h1, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(H2 h2, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Title t, Object param) {

        String text = t.text;
        String resultString = "Title:" + text;

        return resultString;
    }

    @Override
    public Object visit(P p, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Body b, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(Link l, Object param) {

        String href = l.href;
        String rel = l.rel;
        String type = l.type;

        String result = "Link: href= " + href +
        " rel=" + rel + " type=" + type;
        
        return result;
    }

    @Override
    public Object visit(Programa p, Object param) {

        Head head = p.head;
        Body body = p.body;
        String headRepr = (String) head.accept(this, null);
        String bodyRepr = (String) body.accept(this, null);

        String result = "head= " + headRepr + "\n" +
            "body=" + bodyRepr;

        return result;
    }

    @Override
    public Object visit(Bloque b, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    
}