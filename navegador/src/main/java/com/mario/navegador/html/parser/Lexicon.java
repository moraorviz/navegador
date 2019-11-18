package com.mario.navegador.html.parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lexicon {

    List<Token> tokens = new ArrayList<Token>();
    int i = 0; //ultimo token
    FileReader fileReader;
    boolean charBuffUsed = false;
    char charBuff;
    int line = 1;

    HashSet<Character> charText = new HashSet<Character>();

    public Lexicon (FileReader f) {

        fileReader = f;
        String lex;

        try {
            char valor = (char) 0;
            
        }
        case '<':
            valor
    }


}