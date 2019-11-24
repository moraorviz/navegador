package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Normal implements Bloque {

    private String texto;
    private String tipo = "normal";

    public Normal(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTexto() {
        return this.texto;
    }

    @Override
    public Object accept(Visitor v, Object p) {
        return null;
    }
}