package com.mario.navegador.html.ast;

import com.mario.navegador.html.visitor.Visitor;

public class Underlined implements Bloque {

    private String texto;
    private String tipo = "subrayado";

    public Underlined(String texto) {
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