package com.mario.navegador.paint;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mario.navegador.render.Linea;
import com.mario.navegador.render.Pagina;

public class Navegador extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Color Color = null;
    Pagina pagina;

    public Navegador(int numlineas, Pagina pagina) {
        super(new GridLayout(numlineas, 1));
        this.pagina = pagina;
        List<Linea> lineas = pagina.lineas;

        for (Linea l: lineas) {
            String alineado = l.getAtributos().get("text-align");
            String color = l.getAtributos().get("color");
            JLabel label = new JLabel(l.getTexto());

            switch(alineado) {
                case "center":
                    label.setHorizontalAlignment(JLabel.CENTER);
                    break;
                case "left":
                    label.setHorizontalAlignment(JLabel.LEFT);
                    break;
                case "right": 
                    label.setHorizontalAlignment(JLabel.RIGHT);
                    break;
            }

            switch(color) {
                case "black":
                    label.setForeground(Color.black);
                    break;
                case "blue":
                    label.setForeground(Color.blue);
                    break;
                case "green":
                    label.setForeground(Color.green);
                    break;
            }

            add(label);
        }
    }
}