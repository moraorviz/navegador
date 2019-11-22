package com.mario.navegador.html.visitor;

import com.mario.navegador.html.ast.Parrafo;
import com.mario.navegador.html.ast.Programa;
import com.mario.navegador.html.ast.Head;
import com.mario.navegador.html.ast.Link;
import com.mario.navegador.html.ast.H1;
import com.mario.navegador.html.ast.H2;
import com.mario.navegador.html.ast.Title;
import com.mario.navegador.html.ast.P;
import com.mario.navegador.html.ast.Bloque;
import com.mario.navegador.html.ast.Body;

public interface Visitor {
    
    public Object visit(Parrafo p, Object param);
    public Object visit(Head h, Object param);
    public Object visit(H1 h1, Object param);
    public Object visit(H2 h2, Object param);
    public Object visit(Title t, Object param);
    public Object visit(P p, Object param);
    public Object visit(Body b, Object param);
    public Object visit(Link l, Object param);
    public Object visit(Programa p, Object param);
    public Object visit(Bloque b, Object param);

}