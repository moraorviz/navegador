package com.mario.navegador.paint;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/*
 * HelloWorldSwing.java requires no other files. 
 */
import javax.swing.*;
import java.awt.FlowLayout;

import com.mario.navegador.main.GeneraPagina;
import com.mario.navegador.render.Linea;
import com.mario.navegador.render.Pagina;

public class HelloWorldSwing {
    /**
     * Create the GUI and show it. For thread safety, this method should be invoked
     * from the event-dispatching thread.
     */
    private static void createAndShowGUI() {

        // Create and set up the window.
        JFrame frame = new JFrame("Pagina Web");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        GeneraPagina gp = null;
        List<JLabel> labels = new ArrayList<JLabel>();

        try {
            gp = new GeneraPagina("EX4.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Pagina pagina = gp.generarPagina();

        for (Linea l: pagina.lineas) {

            String texto = l.getTexto();
            String tipo = l.getTipo();
            String size = l.getAtributos().get("font-size");
            String color = l.getAtributos().get("color");
            String alineado = l.getAtributos().get("text-align");
            String estilo = l.getAtributos().get("font-style");
            JLabel label = new JLabel(texto);

            labels.add(label);
        }

        for (JLabel l: labels) {
            frame.getContentPane().add(l);
        }

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}