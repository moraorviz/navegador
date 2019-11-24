package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Italic implements Bloque {

    private String texto;
    private String tipo = "italica";

    public Italic(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getTexto() {
        return this.texto;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return null;
    }
}