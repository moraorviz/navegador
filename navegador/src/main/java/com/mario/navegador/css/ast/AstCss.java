package com.mario.navegador.css.ast;

import com.mario.navegador.css.visitor.*;

public interface AstCss {
    Object accept(Visitor v, Object p);
}