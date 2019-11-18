package com.mario.navegador.css.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import com.mario.navegador.css.ast.AstCss;
import com.mario.navegador.css.parser.*;
import com.mario.navegador.css.visitor.BuscaParamEnCssVisitor;
import com.mario.navegador.css.visitor.PrintCssAstVisitor;

public class Main 
{

    public static void main( String[] args ) 
        throws IOException
    {
        Main app = new Main();
        File file = app.getFileFromResources("EX1.css");
        FileReader fileReader = new FileReader(file);
        Lexicon lex = new Lexicon(fileReader);
        //listaTokens(lex);
        Parser parser = new Parser(lex);
        AstCss astCss = parser.parse();
        System.out.println(astCss);

        if (astCss != null) {
            System.out.println("\n========== buscando parametros ============\n");
            BuscaParamEnCssVisitor buscaParam = new BuscaParamEnCssVisitor();
            String h1Color = buscaParam.search("h1", "color", astCss);
            System.out.println(h1Color);
        }

        System.out.println("\n========== imprimiendo arbol ============\n");
        PrintCssAstVisitor printCss = new PrintCssAstVisitor();
        String arbol = (String) astCss.accept(printCss, null);
        System.out.println(arbol);
    }

    static void listaTokens(Lexicon lex) {
        Token t = lex.getToken();
        while (t.getToken() != TokensId.EOF) {
            System.out.println(t.toString());
            t = lex.getToken();
        }
        System.out.println("\nFin de fichero. \n" + t.toString());
    }

    File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}