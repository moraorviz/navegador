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
        //Modo Texto
        GeneraPagina gp = new GeneraPagina("EX4.html");
        Pagina pagina = gp.generarPagina();
        System.out.println(pagina.toString());

        //Modo Grafico
        Paint paint = new Paint(pagina);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                paint.mostrarPagina();
            }
        });
    }
}