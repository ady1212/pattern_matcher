package hu.elte.patternmatching.pattern_matcher_with_reflexive_parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    public static enum TokenType {
        BINARYOP("[*|/|+|-]"),
        WHITESPACE("[ \t\f\r\n]+"),
        BLOCKCOMMENT("[/\\*.*?\\*/]"),
        LINECOMMENT("[//(.*?)[\r$]?\n]"),
        OPENBRACE("[\\(]"),
        CLOSEBRACE("[\\)]"),
        SEMICOLON("[;]"),
        COMMA("[,]"),
        OPENINGCURLYBRACE("[\\{]"),
        CLOSINGCURLYBRACE("[\\}]"),
        DOUBLENUMBER("[0-9]+\\.[0-99]+"),
        INTNUMBER("-?[0-9]+"),
        VOID("\\bvoid\\b"),
        INT("\\bint\\b"),
        DOUBLE("\\bint|double\\b"),
        PUBLIC("\\bpublic\\b"),
        PRIVATE("\\bprivate\\b"),
        TRUE("\\btrue\\b"),
        FALSE("\\bfalse\\b"),
        NULL("\\bnull\\b"),
        RETURN("\\breturn\\b"),
        NEW("\\bnew\\b"),
        CLASS("\\bclass\\b"),
        IF("\\bif\\b"),
        ELSE("\\belse\\b"),
        WHILE("\\bwhile\\b"),
        FOR("\\bfor\\b"),
        STATIC("\\bstatic\\b"),
        POINT("\\."),
        EQUALEQUAL("=="),
        EQUAL("="),
        GREATER(">"),
        LESS("<"),
        IDENTIFIER("\\b([a-zA-Z]{1}[0-9a-zA-Z_]{0,31})\\b");

        public final String pattern;

        private TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static ArrayList<Token> lex(String input) {
        ArrayList<Token> tokens = new ArrayList<Token>();

        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if (matcher.group(TokenType.INTNUMBER.name()) != null) {
                tokens.add(new Token(TokenType.INTNUMBER, matcher.group(TokenType.INTNUMBER.name())));
                continue;
            } else if (matcher.group(TokenType.BINARYOP.name()) != null) {
                tokens.add(new Token(TokenType.BINARYOP, matcher.group(TokenType.BINARYOP.name())));
                continue;
            } else if (matcher.group(TokenType.BLOCKCOMMENT.name()) != null) {
                tokens.add(new Token(TokenType.BLOCKCOMMENT, matcher.group(TokenType.BLOCKCOMMENT.name())));
                continue;
            } else if (matcher.group(TokenType.LINECOMMENT.name()) != null) {
                tokens.add(new Token(TokenType.LINECOMMENT, matcher.group(TokenType.LINECOMMENT.name())));
                continue;
            }else if (matcher.group(TokenType.OPENBRACE.name()) != null) {
                tokens.add(new Token(TokenType.OPENBRACE, matcher.group(TokenType.OPENBRACE.name())));
                continue;
            }else if (matcher.group(TokenType.CLOSEBRACE.name()) != null) {
                tokens.add(new Token(TokenType.CLOSEBRACE, matcher.group(TokenType.CLOSEBRACE.name())));
                continue;
            }else if (matcher.group(TokenType.SEMICOLON.name()) != null) {
                tokens.add(new Token(TokenType.SEMICOLON, matcher.group(TokenType.SEMICOLON.name())));
                continue;
            }else if (matcher.group(TokenType.COMMA.name()) != null) {
                tokens.add(new Token(TokenType.COMMA, matcher.group(TokenType.COMMA.name())));
                continue;
            }else if (matcher.group(TokenType.OPENINGCURLYBRACE.name()) != null) {
                tokens.add(new Token(TokenType.OPENINGCURLYBRACE, matcher.group(TokenType.OPENINGCURLYBRACE.name())));
                continue;
            }else if (matcher.group(TokenType.CLOSINGCURLYBRACE.name()) != null) {
                tokens.add(new Token(TokenType.CLOSINGCURLYBRACE, matcher.group(TokenType.CLOSINGCURLYBRACE.name())));
                continue;
            }else if (matcher.group(TokenType.DOUBLENUMBER.name()) != null) {
                tokens.add(new Token(TokenType.DOUBLENUMBER, matcher.group(TokenType.DOUBLENUMBER.name())));
                continue;
            }else if (matcher.group(TokenType.VOID.name()) != null) {
                tokens.add(new Token(TokenType.VOID, matcher.group(TokenType.VOID.name())));
                continue;
            }else if (matcher.group(TokenType.INT.name()) != null) {
                tokens.add(new Token(TokenType.INT, matcher.group(TokenType.INT.name())));
                continue;
            }else if (matcher.group(TokenType.DOUBLE.name()) != null) {
                tokens.add(new Token(TokenType.DOUBLE, matcher.group(TokenType.DOUBLE.name())));
                continue;
            }else if (matcher.group(TokenType.PUBLIC.name()) != null) {
                tokens.add(new Token(TokenType.PUBLIC, matcher.group(TokenType.PUBLIC.name())));
                continue;
            }else if (matcher.group(TokenType.PRIVATE.name()) != null) {
                tokens.add(new Token(TokenType.PRIVATE, matcher.group(TokenType.PRIVATE.name())));
                continue;
            }else if (matcher.group(TokenType.TRUE.name()) != null) {
                tokens.add(new Token(TokenType.TRUE, matcher.group(TokenType.TRUE.name())));
                continue;
            }else if (matcher.group(TokenType.FALSE.name()) != null) {
                tokens.add(new Token(TokenType.FALSE, matcher.group(TokenType.FALSE.name())));
                continue;
            }else if (matcher.group(TokenType.NULL.name()) != null) {
                tokens.add(new Token(TokenType.NULL, matcher.group(TokenType.NULL.name())));
                continue;
            }else if (matcher.group(TokenType.RETURN.name()) != null) {
                tokens.add(new Token(TokenType.RETURN, matcher.group(TokenType.RETURN.name())));
                continue;
            }else if (matcher.group(TokenType.NEW.name()) != null) {
                tokens.add(new Token(TokenType.NEW, matcher.group(TokenType.NEW.name())));
                continue;
            }else if (matcher.group(TokenType.CLASS.name()) != null) {
                tokens.add(new Token(TokenType.CLASS, matcher.group(TokenType.CLASS.name())));
                continue;
            }else if (matcher.group(TokenType.IF.name()) != null) {
                tokens.add(new Token(TokenType.IF, matcher.group(TokenType.IF.name())));
                continue;
            }else if (matcher.group(TokenType.ELSE.name()) != null) {
                tokens.add(new Token(TokenType.ELSE, matcher.group(TokenType.ELSE.name())));
                continue;
            }else if (matcher.group(TokenType.WHILE.name()) != null) {
                tokens.add(new Token(TokenType.WHILE, matcher.group(TokenType.WHILE.name())));
                continue;
            }else if (matcher.group(TokenType.FOR.name()) != null) {
                tokens.add(new Token(TokenType.FOR, matcher.group(TokenType.FOR.name())));
                continue;
            }else if (matcher.group(TokenType.STATIC.name()) != null) {
                tokens.add(new Token(TokenType.STATIC, matcher.group(TokenType.STATIC.name())));
                continue;
            }else if (matcher.group(TokenType.POINT.name()) != null) {
                tokens.add(new Token(TokenType.POINT, matcher.group(TokenType.POINT.name())));
                continue;
            }else if (matcher.group(TokenType.EQUALEQUAL.name()) != null) {
                tokens.add(new Token(TokenType.EQUALEQUAL, matcher.group(TokenType.EQUALEQUAL.name())));
                continue;
            }else if (matcher.group(TokenType.EQUAL.name()) != null) {
                tokens.add(new Token(TokenType.EQUAL, matcher.group(TokenType.EQUAL.name())));
                continue;
            }else if (matcher.group(TokenType.GREATER.name()) != null) {
                tokens.add(new Token(TokenType.GREATER, matcher.group(TokenType.GREATER.name())));
                continue;
            }else if (matcher.group(TokenType.LESS.name()) != null) {
                tokens.add(new Token(TokenType.LESS, matcher.group(TokenType.LESS.name())));
                continue;
            } else if (matcher.group(TokenType.WHITESPACE.name()) != null)
                continue;
            
        }

        return tokens;
    }

}
