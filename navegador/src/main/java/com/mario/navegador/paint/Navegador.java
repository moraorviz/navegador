package com.mario.navegador.paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.font.TextAttribute;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mario.navegador.render.Linea;
import com.mario.navegador.render.Pagina;

public class Navegador extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Pagina pagina;

    public Navegador(int numlineas, Pagina pagina) {

        super(new GridLayout(numlineas, 1));
        this.pagina = pagina;
        List<Linea> lineas = pagina.lineas;

        Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

        for (Linea l: lineas) {
            String alineado = l.getAtributos().get("text-align");
            String color = l.getAtributos().get("color");
            String tipo = l.getTipo();
            JLabel label = new JLabel(l.getTexto());
            Font f = new Font("Courier", Font.PLAIN, 16);

            if (tipo == "h1") {
                f = new Font("Courier", Font.PLAIN, 26);
                label.setFont(f);
            } else if (tipo == "h2") {
                f = new Font("Courier", Font.PLAIN, 20);
                label.setFont(f);
            }

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

            switch(tipo) {
                case "normal":
                    break;
                case "negrita":
                    label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
                    break;
                case "italica":
                    label.setFont(f.deriveFont(f.getStyle() | Font.ITALIC));
                    break;
                case "subrayado":
                    Font under = new Font("Serif",Font.PLAIN, 16).deriveFont(fontAttributes);
                    label.setFont(under);
            }

            add(label);
        }
    }
}