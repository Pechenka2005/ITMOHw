package generator;

import generator.helpers.CustomToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LexerGenerator {
    private Grammar grammar;
    private String grammarUpper;

    public LexerGenerator(Grammar grammar) {
        this.grammar = grammar;
    }

    void generate() {
        StringBuilder dummy = new StringBuilder(grammar.name);
        dummy.setCharAt(0, Character.toUpperCase(grammar.name.charAt(0)));
        this.grammarUpper = dummy.toString();

        Path dir = Paths.get(System.getProperty("user.dir")).resolve("example").resolve(grammarUpper);
        String lexerName = grammarUpper + "Lexer.java";
        String tokensName = grammarUpper + "Tokens.java";

        try {
            printToFile(dir, lexerName, generateLexerCode());
            printToFile(dir, tokensName, generateTokensCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printToFile(Path dir, String fileName, StringBuilder code) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(dir.toString(), fileName));
        writer.write(code.toString());
        writer.close();
    }

    private StringBuilder generateLexerCode() {
        StringBuilder code = new StringBuilder();

        code.append("package ").append(grammar.name).append(";").append("\n\n")
                .append("import java.util.ArrayList;").append("\n")
                .append("import java.util.List;").append("\n")
                .append("import java.util.regex.Matcher;").append("\n")
                .append("import java.util.regex.Pattern;").append("\n\n").append("public class ").append(grammarUpper).append("Lexer {").append("\n");

        code.append(tabN(1)).append("public StringBuilder input;").append("\n")
            .append(tabN(1) + "private int currPosition = 0;").append("\n")
            .append(tabN(1) + "public List<CompiledToken> compiledTokens = new ArrayList<>();").append("\n")
            .append(tabN(1) + "public List<CompiledToken> compiledSkipTokens = new ArrayList<>();").append("\n")
            .append(tabN(1) + "public List<" + grammarUpper + "Tokens> usedTokens = new ArrayList<>();").append("\n")
            .append(tabN(1) + "public List<String> parsedTokens = new ArrayList<>();").append("\n\n");


        code.append(tabN(1) + "public " + grammarUpper + "Lexer(String input) throws Exception {").append("\n")
            .append(tabN(2) + "this.input = new StringBuilder(input);").append("\n\n");
        for (CustomToken token : grammar.tokens) {
            code.append(tabN(2) + "compiledTokens.add(new CompiledToken(" + grammarUpper + "Tokens." + token.name +
                    ", " + token.regExp + "));").append("\n");
        }
        code.append("\n");
        for (CustomToken token : grammar.tokensToSkip) {
            code.append(tabN(2) + "compiledSkipTokens.add(new CompiledToken(" + grammarUpper + "Tokens." + token.name +
                    ", " + token.regExp + "));").append("\n");
        }
        code.append("\n");
        code.append(tabN(2) + "constructTokens();").append("\n").append(tabN(1) + "}").append("\n\n");


        code.append(tabN(1) + "public " + grammarUpper + "Tokens getCurrToken() {").append("\n")
            .append(tabN(2) + "return usedTokens.get(currPosition);").append("\n")
            .append(tabN(1) + "}").append("\n\n");

        code.append(tabN(1) + "public void toNextToken() {").append("\n")
            .append(tabN(2) + "currPosition++;").append("\n")
            .append(tabN(1) + "}").append("\n\n");

        code.append(tabN(1) + "public String getCurrTokenStringRepresentation() {").append("\n")
            .append(tabN(2) + "return parsedTokens.get(currPosition);").append("\n")
            .append(tabN(1) + "}").append("\n\n");


        code.append(tabN(1) + "private void constructTokens() throws Exception {").append("\n")
            .append(tabN(2) + "while (input.length() > 0) {").append("\n")
            .append(tabN(3) + grammarUpper + "Tokens currToken = findToken();").append("\n")
            .append(tabN(3) + "if (currToken != null) {").append("\n")
            .append(tabN(4) + "usedTokens.add(currToken);").append("\n")
            .append(tabN(3) + "} else if (!findSkipToken()) {").append("\n")
            .append(tabN(4) + "throw new Exception(\"Cannot match tokens\");").append("\n")
            .append(tabN(3) + "}").append("\n").append(tabN(2) + "}").append("\n")
            .append(tabN(2) + "usedTokens.add(" + grammarUpper + "Tokens.$);").append("\n")
            .append(tabN(1) + "}").append("\n\n");


        code.append(tabN(1) + "private " + grammarUpper + "Tokens findToken() {").append("\n")
            .append(tabN(2) + "for (CompiledToken compiled : compiledTokens) {").append("\n")
            .append(tabN(3) + "Matcher matcher = compiled.pattern.matcher(input.toString());").append("\n")
            .append(tabN(3) + "if (matcher.lookingAt()) {").append("\n")
            .append(tabN(4) + "parsedTokens.add(input.substring(0, matcher.end()));").append("\n")
            .append(tabN(4) + "input.delete(0, matcher.end());").append("\n")
            .append(tabN(4) + "return compiled.token;").append("\n")
            .append(tabN(3) + "}").append("\n").append(tabN(2) + "}").append("\n")
            .append(tabN(2) + "return null;").append("\n")
            .append(tabN(1) + "}").append("\n\n");


        code.append(tabN(1) + "private boolean findSkipToken() {").append("\n")
            .append(tabN(2) + "for (CompiledToken compiled : compiledSkipTokens) {").append("\n")
            .append(tabN(3) + "Matcher matcher = compiled.pattern.matcher(input.toString());").append("\n")
            .append(tabN(3) + "if (matcher.lookingAt()) {").append("\n")
            .append(tabN(4) + "input.delete(0, matcher.end());").append("\n")
            .append(tabN(4) + "return true;").append("\n")
            .append(tabN(3) + "}").append("\n").append(tabN(2) + "}").append("\n")
            .append(tabN(2) + "return false;").append("\n")
            .append(tabN(1) + "}").append("\n\n");


        code.append(tabN(1) + "private static class CompiledToken {").append("\n")
            .append(tabN(2) + "public " + grammarUpper + "Tokens token;").append("\n")
            .append(tabN(2) + "public Pattern pattern;").append("\n\n")
            .append(tabN(2) + "public CompiledToken(" + grammarUpper + "Tokens token, String pattern) {").append("\n")
            .append(tabN(3) + "this.token = token;").append("\n")
            .append(tabN(3) + "this.pattern = Pattern.compile(pattern);").append("\n")
            .append(tabN(2) + "}").append("\n").append(tabN(1) + "}").append("\n");

        code.append("}");

        return code;
    }

    private StringBuilder generateTokensCode() {
        StringBuilder code = new StringBuilder();

        code.append("package " + grammar.name + ";").append("\n\n")
            .append("public enum " + grammarUpper + "Tokens {").append("\n");

        for (String token : grammar.nameToToken.keySet()) {
            code.append(tabN(1) + token + ",").append("\n");
        }
        code.append(tabN(1) + "$").append("\n").append("}");

        return code;
    }

    private String tabN(int n) {
        return "\t".repeat(Math.max(0, n));
    }
}
