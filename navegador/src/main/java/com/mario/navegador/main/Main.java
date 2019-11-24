package com.mario.navegador.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.mario.navegador.html.parser.Lexicon;
import com.mario.navegador.html.parser.Parser;
import com.mario.navegador.html.visitor.RenderVisitor;
import com.mario.navegador.render.Pagina;
import com.mario.navegador.html.ast.AstHtml;

public class Main 
{

    public static void main( String[] args ) 
        throws IOException
    {
        //Lectura del fichero fuente
        Utils utils = new Utils();
        File file = utils.getFileFromResources("EX4simple.html");
        FileReader fileReader = new FileReader(file);

        //Lexico y sintactico
        Lexicon lex = new Lexicon(fileReader);
        Parser parser = new Parser(lex);
        AstHtml astHtml = parser.parse();

        //Renderizar la pagina en modo texto
        RenderVisitor rv = new RenderVisitor(astHtml);
        Pagina pagina = (Pagina) astHtml.accept(rv, null);
        System.out.println(pagina.toString());
    }
}