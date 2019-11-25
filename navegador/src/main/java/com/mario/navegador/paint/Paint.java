package com.mario.navegador.paint;

import javax.swing.JFrame;

import com.mario.navegador.render.Pagina;

public class Paint {

    int nlineas;
    Pagina pagina;
    JFrame frame = new JFrame("Navegador");

    public Paint(Pagina pagina) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pagina = pagina;
        this.nlineas = pagina.lineas.size();
    }

    public void mostrarPagina() {
        frame.add(new Navegador(this.nlineas, this.pagina));
        frame.pack();
        frame.setVisible(true);
    }

}