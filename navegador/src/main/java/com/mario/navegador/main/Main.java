package com.mario.navegador.main;

import java.io.IOException;

import com.mario.navegador.render.Pagina;

public class Main 
{
    public static void main( String[] args ) 
        throws IOException
    {
        GeneraPagina gp = new GeneraPagina("EX4complejo.html");
        Pagina pagina = gp.generarPagina();
        System.out.println(pagina.toString());
    }
}