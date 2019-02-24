package hu.elte.patternmatching.pattern_matcher_with_reflexive_parser;

import hu.elte.patternmatching.pattern_matcher_with_reflexive_parser.Lexer.TokenType;

public class Token {
    public TokenType type;
    public String data;

    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("(%s %s)", type.name(), data);
    }
}
