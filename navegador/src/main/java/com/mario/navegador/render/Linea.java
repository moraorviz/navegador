package com.mario.navegador.render;

import java.util.HashMap;
import java.util.Map;

public class Linea {

    String tipo;
    String texto;
    Map<String, String> atributos = new HashMap<>();

    public Linea(String tipo, String texto,
        Map<String, String> atributos) {
            this.tipo = tipo;
            this.texto = texto;
            this.atributos = atributos;
        }

    public String getTexto() {
        return this.texto;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Map<String, String> getAtributos() {
        return this.atributos;
    }

    @Override
    public String toString() {
        return "tipo: " + tipo + " texto: " + texto +
            " atributos: " + atributos.toString();
    }
}