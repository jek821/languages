package com.jemanuel.jek;

class Token {
    final TokenType type;


    Token(TokenType type, String lexeme, Object literal, int line){
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

}