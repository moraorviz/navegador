package com.mario.navegador.html.parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lexicon {

    List<Token> tokens = new ArrayList<Token>();
    int i = 0; //ultimo token recogido
    FileReader filereader;
    boolean charBuffUsed = false;
    char charBuff;
    int line = 1;

    HashSet<Character> charText = new HashSet<Character>();

    public Lexicon (FileReader f) {

        filereader = f;
        String lex;

        try {
            char valor = (char) 0;
            while (valor != (char) -1) {
                valor = nextChar();

                switch(valor) {
                    //start tag
                    case '<':
                        valor = nextChar();
                        //caso se abre etiqueta
                        if (valor != '/') {
                            String contEtiq = getTextoEtiqueta(valor + "");
                            //creacion de tokens
                            switch(contEtiq) {
                                case "html":
                                    tokens.add(new Token(TokensId.HTML, "<html>", line));
                                    break;
                                case "head":
                                    tokens.add(new Token(TokensId.HEAD, "<head>", line));
                                    break;
                                case "title":
                                    tokens.add(new Token(TokensId.TITLE, "<title>", line));

                                    char cerrarLlave = nextChar();
                                    if (cerrarLlave == '>') {
                                        char primeraLetra = nextChar();
                                        String textoInterior = getTextoInterior(primeraLetra);
                                        tokens.add(new Token(TokensId.TEXT, textoInterior, line));
                                    } else {
                                        errorLexico(valor + "");
                                    }

                                    break;
                                case "link":
                                    tokens.add(new Token(TokensId.LINK, "<link>", line));
                                    break;
                                case "body":
                                    tokens.add(new Token(TokensId.BODY, "<body>", line));
                                    break;
                                case "h1":
                                    tokens.add(new Token(TokensId.H1, "<h1>", line));
                                    break;
                                case "h2":
                                    tokens.add(new Token(TokensId.H2, "<h2>", line));
                                    break;
                                case "p":
                                    tokens.add(new Token(TokensId.P, "<p>", line));
                                    break;
                                case "b":
                                    tokens.add(new Token(TokensId.BOLD, "<b>", line));
                                    break;
                                case "i":
                                    tokens.add(new Token(TokensId.ITALIC, "<i>", line));
                                    break;
                                case "u":
                                    tokens.add(new Token(TokensId.UNDERL, "<u>", line));
                                    break;
                            }
                        //caso se cierra etiqueta
                        } else if (valor == '/') {
                            String contEtiq = getTextoEtiqueta(valor + "");
                            switch(contEtiq) {
                                case "html":
                                    tokens.add(new Token(TokensId.HTMLCLOSE, "</html>", line));
                                    break;
                                case "head":
                                    tokens.add(new Token(TokensId.HEADC, "</head>", line));
                                    break;
                                case "title":
                                    tokens.add(new Token(TokensId.TITLEC, "</title>", line));
                                    break;
                                case "body":
                                    tokens.add(new Token(TokensId.BODYC, "</body>", line));
                                    break;
                                case "h1":
                                    tokens.add(new Token(TokensId.H1C, "</h1>", line));
                                    break;
                                case "h2":
                                    tokens.add(new Token(TokensId.H2C, "</h2>", line));
                                    break;
                                case "p":
                                    tokens.add(new Token(TokensId.PC, "</p>", line));
                                    break;
                                case "b":
                                    tokens.add(new Token(TokensId.BOLDC, "</b>", line));
                                    break;
                                case "i":
                                    tokens.add(new Token(TokensId.ITALICC, "</i>", line));
                                    break;
                                case "u":
                                    tokens.add(new Token(TokensId.UNDERLC, "</u>", line));
                                    break;
                            }
                        }
                    //caso se cierra etiqueta
                    case '>':
                        break;
                    //si hay un salto de linea se consume el caracter y
                    //se aumenta el numero de linea
                    case '\n':
                        line++;
                        break;
                    case '\r':
                        break;
                    case '\t':
                        break;
                    //espacios en blanco
                    case ' ':
                        break;
                    //caso fin de fichero
                    case (char) -1:
                        break;
                    // opcion por defecto
                    // no es ninguno de los anteriores
                    default:
                        //System.out.println("Entrando en opcion por defecto");
                        //String textoInterior = getTextoInterior(valor + "");
                        //System.out.println(textoInterior);

                }
            }
            
        } catch(IOException e) {
            System.out.println("Error E/S: " + e);
        }
    }

    //Metodo getToken
    public Token getToken() {
        if (i < tokens.size()) {
            return tokens.get(i++);
        }
        //el ultimo token siempre es el de fin de linea
        return new Token(TokensId.EOF, "EOF", line);
    }

    //devolver el ultimo token
    public void returnLastToken() {
        i--;
    }

    //privados
    
    //Devuelve el siguiente caracter. Si se ha devuelto previamente
    //uno lo devuelve del buffer, sino lo devuelve del fichero
    char nextChar() throws IOException {
        if (charBuffUsed) {
            charBuffUsed = false;
            return charBuff;
        } else {
            int valor = filereader.read();
            return ((char) valor);
        }
    }

    //Devuelve un caracter al buffer.
    void returnChar(char r) {
        charBuffUsed = true;
        charBuff = r;
    }

    //Leer texto interior
    String getTextoInterior(char primerCaracter) throws IOException {

        //list vacia de palabras
        List<String> listaPalabras = new ArrayList<String>();

        //ir formando palabras
        String palabra = primerCaracter + "";
        //se coge el siguiente caracter
        char caracterSiguiente = nextChar();
        //se itera mientras sea valido (numero o letra)
        while (Character.isDigit(caracterSiguiente)
            || Character.isAlphabetic(caracterSiguiente)
            || caracterSiguiente == ' '
            || caracterSiguiente == '<') { 

            if (caracterSiguiente == ' '
                || caracterSiguiente == '<') {
                listaPalabras.add(palabra);
                palabra = "";
            } else {
                palabra += caracterSiguiente + "";
            }

            caracterSiguiente = nextChar();
        }
        //si el ultimo no coincide nose coge, con lo cual se devuelve
        returnChar(caracterSiguiente);

        //formar la frase de salida
        String texto = "";
        for (String s: listaPalabras) {
            texto = texto.concat(" " + s);
        }

        return texto.trim();
    }

    /**
     * Devolver el contenido de la etiqueta hasta el 
     * simbolo de cierre '>'. Se devuelve como cadena
     * de caracteres (String)
     * @param letraInicio La primera letra despues del simbolo
     * de apertura de etiqueta '<'
     * @return String contenidoEtiqueta El contenido de la etiqueta
    */
    String getTextoEtiqueta(String letraInicio) throws IOException {
        // TODO: probar el metodo con tests unitarios en el paquete
        // dedicado a JUnit.
        // inicializar la variable contenido de la etiqueta que se
        // ira llenando de caracteres. Se inicializa con la primera
        // letra detectada.

        String contenidoEtiqueta = "";

        // Condicional por si la etiqueta es de cierre
        // emitir la barra inclinada inicial y pasar directamente
        // al siguiente caracter
        if (!letraInicio.equals("/")) {
            contenidoEtiqueta = letraInicio;
        } else {
            contenidoEtiqueta = nextChar() + "";
        }
        // se coge el siguiente caracter
        char siguienteCaracter = nextChar();
        // se cogen los sucesivos caracteres mientras sea una letra o
        // un numero
        while(Character.isDigit(siguienteCaracter)
            || Character.isAlphabetic(siguienteCaracter)) {
                // sumar el caracter obtenido al resultado final
                contenidoEtiqueta += siguienteCaracter;
                // coger el siguiente caracter
                siguienteCaracter = nextChar();
        }

        // devuelve el ultimo caracter al buffer (el de cierre) si no es
        // ni digito ni alfabetico
        returnChar(siguienteCaracter);
        return contenidoEtiqueta;
    }

    // Metodo para indicar que ha ocurrido un error lexico
    void errorLexico(String e) {
        System.out.println("Error lexico en: " + e);
    }
}