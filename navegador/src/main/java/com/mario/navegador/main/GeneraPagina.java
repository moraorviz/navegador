package com.mario.navegador.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.mario.navegador.html.ast.AstHtml;
import com.mario.navegador.html.parser.Lexicon;
import com.mario.navegador.html.parser.Parser;
import com.mario.navegador.html.visitor.RenderVisitor;
import com.mario.navegador.render.Pagina;

public class GeneraPagina {

    Utils utils = new Utils();
    String html;

    public GeneraPagina(String html) throws FileNotFoundException {
        this.html = html;
    };

    public Pagina generarPagina() {

        File file = utils.getFileFromResources(html);
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Lexicon lex = new Lexicon(fileReader);
        Parser parser = new Parser(lex);
        AstHtml astHtml = parser.parse();
        RenderVisitor rv = new RenderVisitor(astHtml);
        Pagina pagina = (Pagina) astHtml.accept(rv, null);

        return pagina;
    }
}