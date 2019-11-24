package com.mario.navegador.html.visitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mario.navegador.css.ast.AstCss;
import com.mario.navegador.css.parser.Lexicon;
import com.mario.navegador.css.parser.Parser;
import com.mario.navegador.css.visitor.BuscaParamEnCssVisitor;
import com.mario.navegador.html.ast.AstHtml;
import com.mario.navegador.html.ast.Bloque;
import com.mario.navegador.html.ast.Body;
import com.mario.navegador.html.ast.H1;
import com.mario.navegador.html.ast.H2;
import com.mario.navegador.html.ast.Head;
import com.mario.navegador.html.ast.Link;
import com.mario.navegador.html.ast.P;
import com.mario.navegador.html.ast.Parrafo;
import com.mario.navegador.html.ast.Programa;
import com.mario.navegador.html.ast.Title;
import com.mario.navegador.main.Utils;
import com.mario.navegador.render.Linea;
import com.mario.navegador.render.Pagina;

public class RenderVisitor implements Visitor {

    BuscaCssVisitor buscaCss = new BuscaCssVisitor();
    BuscaParamEnCssVisitor buscaParam = new BuscaParamEnCssVisitor();
    AstHtml astHtml;
    AstCss defaultCssAst;
    AstCss userCssAst;
    Utils utils = new Utils();
    List<String> atributos = new ArrayList<String>();
    Pagina pagina = new Pagina();

    public RenderVisitor(AstHtml astHtml) {

        File cssDefault = utils.getFileFromResources("Default.css");
        FileReader fileReader = null;

        atributos.add("color");
        atributos.add("font-size");
        atributos.add("text-align");
        atributos.add("font-style");

        try {
            fileReader = new FileReader(cssDefault);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Lexicon lex = new Lexicon(fileReader);
        Parser parser = new Parser(lex);
        String cssUserPath = (String) astHtml.accept(buscaCss, null);
        File cssUser = utils.getFileFromResources(cssUserPath);

        defaultCssAst = parser.parse();

        try {
            fileReader = new FileReader(cssUser);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        lex = new Lexicon(fileReader);
        parser = new Parser(lex);
        userCssAst = parser.parse();
    }

    @Override
    public Object visit(Parrafo p, Object param) {
        return null;
    }

    @Override
    public Object visit(Head h, Object param) {
        return null;
    }

    @Override
    public Object visit(H1 h1, Object param) {

        String texto = h1.text;
        Map<String, String> atributos = new HashMap<>();

        for (String a: this.atributos) {
            String v = buscaParam.search("h1", a, userCssAst);

            if (v == null) {
                v = buscaParam.search("h1", a, defaultCssAst);
            }

            atributos.put(a, v);
        }

        Linea linea = new Linea("h1", texto, atributos);
        pagina.lineas.add(linea);

        return null;
    }

    @Override
        public Object visit(H2 h2, Object param) {

        String texto = h2.text;
        Map<String, String> atributos = new HashMap<>();

        for (String a: this.atributos) {
            String v = buscaParam.search("h2", a, userCssAst);

            if (v == null) {
                v = buscaParam.search("h2", a, defaultCssAst);
            }

            atributos.put(a ,v);
        }

        Linea linea = new Linea("h2", texto, atributos);
        pagina.lineas.add(linea);

        return null;
    }

    @Override
    public Object visit(Title t, Object param) {
        return null;
    }

    @Override
    public Object visit(P p, Object param) {

        List<Bloque> bloques = p.bloques;
        Map<String, String> atributos = new HashMap<>();
        Linea linea = null;

        for (String a: this.atributos) {
            String v = buscaParam.search("p", a, userCssAst);

            if (v == null) {
                v = buscaParam.search("p", a, defaultCssAst);
            }

            atributos.put(a ,v);
        }

        for (Bloque b: bloques) {
            linea = new Linea(b.getTipo(), b.getTexto(), atributos);
            pagina.lineas.add(linea);
        }

        return null;
    }

    @Override
    public Object visit(Body b, Object param) {

        List<Parrafo> parrafos = b.parrafos;

        for (Parrafo p: parrafos) {
            p.accept(this, null);
        }

        return pagina;
    }

    @Override
    public Object visit(Link l, Object param) {
        return null;
    }

    @Override
    public Object visit(Programa p, Object param) {

        Head head = p.head;
        Body body = p.body;
        Pagina pagina = new Pagina();

        head.accept(this, null);
        pagina = (Pagina) body.accept(this, null);

        return pagina;
    }

    @Override
    public Object visit(Bloque b, Object param) {
        return null;
    }
}