package com.mario.navegador.main;

import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.mario.navegador.paint.Paint;
import com.mario.navegador.render.Pagina;

public class Main 
{
    public static void main( String[] args ) 
        throws IOException
    {
        //Modo texto
        GeneraPagina gp = new GeneraPagina("EX4complejo.html");
        Pagina pagina = gp.generarPagina();
        System.out.println(pagina.toString());

        //Modo grafico
        Paint paint = new Paint(pagina);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI. (Tutoriales de Oracle)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                paint.mostrarPagina();
            }
        });
    }
}