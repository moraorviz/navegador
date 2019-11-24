package com.mario.navegador.render;

import java.util.ArrayList;
import java.util.List;

public class Pagina {

    public List<Linea> lineas = new ArrayList<Linea>();

    @Override
    public String toString() {

        String s = "";
        int i = 0;

        for (Linea l: lineas) {
            s = s.concat("Linea " + i + ": " + l.toString() + "\n");
            i++;
        }

        return s;
    }
}