package hu.elte.patternmatcher;

import java.util.HashMap;
import java.util.Map;

import hu.elte.patternmatcher.lexer.Lexer;

public class Main {
    private static Map<String,String> identifierValueMap;
    
    public static void main(String[] args) {
        Lexer lexer = new Lexer("./resources/Input.txt");
        identifierValueMap = new HashMap<>();
        System.out.println("Lexical Analysis");
        System.out.println("-----------------");
        while (!lexer.isFinished()) {
            System.out.printf("%-18s :  %s \n", lexer.currentLexema(), lexer.currentToken());
            lexer.moveAhead();
        }

        if (lexer.isSuccessful()) {
            System.out.println("Success!");
        } else {
            System.out.println(lexer.errorMessage());
        }

    }
}
