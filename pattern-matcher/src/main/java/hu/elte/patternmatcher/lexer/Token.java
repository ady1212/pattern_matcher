package hu.elte.patternmatcher.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {
    BINARYOP("[*|/|+|-]"),
    OPENBRACE("[\\(]"),
    CLOSEBRACE("[\\)]"),
    SEMICOLON("[;]"),
    COMMA("[,]"),
    QUOTE("[\"]"),
    OPENINGCURLYBRACE("[\\{]"),
    CLOSINGCURLYBRACE("[\\}]"),
    DOUBLENUMBER("[0-9]+\\.[0-99]+"),
    INTNUMBER("-?[0-9]+"),
    VOID("\\bvoid\\b"),
    INT("\\bint\\b"),
    STRING("\\bString\\b"),
    DOUBLE("\\double\\b"),
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
    BLOCKCOMMENT("[/\\*.*?\\*/]"),
    LINECOMMENT("[//(.*?)[\r$]?\n]"),
    IDENTIFIER("\\b([a-zA-Z]{1}[0-9a-zA-Z_]{0,31})\\b");
    
    private final Pattern pattern;
    
    Token(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    int endOfMatch(String s) {
        Matcher m = pattern.matcher(s);

        if (m.find()) {
            return m.end();
        }
        return -1;
    }
}
