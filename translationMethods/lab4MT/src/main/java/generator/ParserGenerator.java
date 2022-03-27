package generator;

import generator.helpers.CustomRule;
import generator.helpers.CustomState;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class ParserGenerator {
    private Grammar grammar;
    private String grammarUpper;

    public ParserGenerator(Grammar grammar) {
        this.grammar = grammar;
    }

    void generate() {
        StringBuilder dummy = new StringBuilder(grammar.name);
        dummy.setCharAt(0, Character.toUpperCase(grammar.name.charAt(0)));
        this.grammarUpper = dummy.toString();

        Path dir = Paths.get(System.getProperty("user.dir")).resolve("example").resolve(grammarUpper);
        String parserName = grammarUpper + "Parser.java";

        try {
            printToFile(dir, parserName, generateParserCode());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void printToFile(Path dir, String fileName, StringBuilder code) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(dir.toString(), fileName));
        writer.write(code.toString());
        writer.close();
    }

    private StringBuilder generateParserCode() {
        StringBuilder code = new StringBuilder();

        code.append("package " + grammar.name + ";").append("\n\n")
            .append("import java.util.ArrayList;").append("\n")
            .append("import java.util.List;").append("\n\n")
            .append("public class " + grammarUpper + "Parser {").append("\n");


        code.append(tabN(1) + "public " + grammarUpper + "Lexer lexer;").append("\n")
            .append(tabN(1) + "public Node tree;").append("\n\n")
            .append(tabN(1) + "public " + grammarUpper + "Parser(" + grammarUpper + "Lexer lexer) throws Exception {").append("\n")
            .append(tabN(2) + "this.lexer = lexer;").append("\n")
            .append(tabN(2) + "buildTree();").append("\n")
            .append(tabN(1) + "}").append("\n\n");

        code.append(tabN(1) + "public void buildTree() throws Exception {").append("\n")
            .append(tabN(2) + "tree = build_" + grammar.startStateName + "();").append("\n")
            .append(tabN(2) + "if (lexer.getCurrToken() != " + grammarUpper + "Tokens.$) {").append("\n")
            .append(tabN(3) + "throw new Exception(\"The end of the input. Expected $, but get \"" + " + lexer.getCurrToken());").append("\n")
            .append(tabN(2) + "}").append("\n").append(tabN(1) + "}").append("\n\n");

        for (String returnValue : grammar.nameToState.get(grammar.startStateName).returnValues) {
            String[] splitted = returnValue.split("[ ]+");
            String type = splitted[0];
            String returnName = splitted[1];

            StringBuilder nameForCamelCase = new StringBuilder(returnName);
            nameForCamelCase.setCharAt(0, Character.toUpperCase(returnName.charAt(0)));

            code.append(tabN(1) + "public " + type + " get" + nameForCamelCase + "() throws Exception {").append("\n")
                .append(tabN(2) + "return ((Node_" + grammar.startStateName + ")tree)." + returnName + ";").append("\n")
                .append(tabN(1) + "}").append("\n\n");
        }


        code.append(tabN(1) + "private class Node {").append("\n")
            .append(tabN(2) + "public String name;").append("\n")
            .append(tabN(2) + "public List<Node> children = new ArrayList<>();").append("\n\n");

        code.append(tabN(2) + "public Node(String name) {").append("\n")
            .append(tabN(3) + "this.name = name;").append("\n")
            .append(tabN(2) + "}").append("\n\n");

        code.append(tabN(2) + "public void addChild(Node node) {").append("\n")
            .append(tabN(3) + "children.add(node);").append("\n")
            .append(tabN(2) + "}").append("\n");

        code.append(tabN(1) + "}").append("\n\n");


        for (CustomState state : grammar.nameToState.values()) {
            code.append(tabN(1) + "private class Node_" + state.name + " extends Node {").append("\n");

            for (String returnValue : state.returnValues) {
                code.append(tabN(2) + "public " + returnValue + ";").append("\n");
            }

            code.append(tabN(2) + "public Node_" + state.name + "() {").append("\n")
                .append(tabN(3) + "super(\"" + state.name + "\");").append("\n")
                .append(tabN(2) + "}").append("\n")
                .append(tabN(1) + "}").append("\n\n");
        }


        for (CustomState state : grammar.nameToState.values()) {
            code.append(tabN(1) + "private Node_" + state.name + " build_" + state.name +
                    "(" + buildParameters(state) + ") throws Exception {").append("\n")
                .append(tabN(2) + "Node_" + state.name + " res = new Node_" + state.name + "();").append("\n")
                .append(tabN(2) + "switch (lexer.getCurrToken()) {").append("\n");

            for (CustomRule rule : state.rules) {
                code.append(buildSwitch(state, rule));
            }

            code.append(tabN(3) + "default: {").append("\n")
                .append(tabN(4) + "throw new Exception(\"Unexpected token in state " + state.name + "\");").append("\n")
                .append(tabN(3) + "}").append("\n").append(tabN(2)+ "}").append("\n")
                .append(tabN(1) + "}").append("\n\n");
        }


        code.append("}");

        return code;
    }


    private String buildParameters(CustomState state) {
        StringBuilder code = new StringBuilder();

        for (String parameter : state.parameters) {
            code.append(parameter + ",");
        }
        if (code.length() > 0) {
            code.delete(code.length() - 1, code.length());
        }

        return code.toString();
    }

    private String buildByFollow(CustomState state, String codeLine) {
        StringBuilder code = new StringBuilder();

        for (String token : state.follow) {
            code.append(tabN(3) + "case " + token + ":").append("\n");
        }
        if (code.length() == 0) {
            return code.toString();
        }

        code.delete(code.length() - 1, code.length());
        code.append(" {").append("\n");

        if (!codeLine.isEmpty()) {
            code.append(tabN(4) + codeLine).append("\n");
        }
        code.append(tabN(4) + "return res;").append("\n")
            .append(tabN(3) + "}").append("\n");

        return code.toString();
    }

    private String buildSwitch(CustomState state, CustomRule rule) {
        StringBuilder code = new StringBuilder();
        HashSet<String> firstForRule = grammar.constructFirstForRule(rule);

        for (String token : firstForRule) {
            if (token.equals("EPS")) {
                code.append(buildByFollow(state, rule.codeLines.get(0)));
            } else {
                code.append(tabN(3) + "case " + token + ":").append("\n");
            }
        }
        if (code.length() == 0 || firstForRule.contains("EPS")) {
            return code.toString();
        }

        code.delete(code.length() - 1, code.length());
        code.append(" {").append("\n");

        int nodeId = 0;
        for (int i = 0; i < rule.items.size(); i++) {
            String item = rule.items.get(i);
            String parameters = rule.parameters.get(i);
            String codeLine = rule.codeLines.get(i);

            if (grammar.nameToToken.containsKey(item)) {
                code.append(tabN(4) + "if (" + grammarUpper + "Tokens." + item + " != lexer.getCurrToken()) {").append("\n")
                    .append(tabN(5) + "throw new Exception" +
                            "(\"Expected " + item + " token, but get \" + lexer.getCurrToken());").append("\n")
                    .append(tabN(4) + "}").append("\n")
                    .append(tabN(4) + "res.addChild(new Node(\"" + item +"\"));").append("\n");

                if (!codeLine.isEmpty()) {
                    code.append(tabN(4) + codeLine).append("\n");
                }
                code.append(tabN(4) + "lexer.toNextToken();").append("\n\n");
            } else if (grammar.nameToState.containsKey(item)) {
                code.append(tabN(4) + "Node_" + item + " node" + nodeId +
                        " = build_" + item + "(" + parameters + ");").append("\n")
                    .append(tabN(4) + "res.addChild(node" + nodeId + ");").append("\n");

                if (!codeLine.isEmpty()) {
                    code.append(tabN(4) + codeLine).append("\n");
                }
                code.append("\n");

                nodeId++;
            } else {
                System.err.println("Cannot find token or state " + item);
                System.exit(-1);
            }
        }

        code.append(tabN(4) + "return res;").append("\n")
            .append(tabN(3) + "}").append("\n");

        return code.toString();
    }

    private String tabN(int n) {
        return "\t".repeat(Math.max(0, n));
    }
}
