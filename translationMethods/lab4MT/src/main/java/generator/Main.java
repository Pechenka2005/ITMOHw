package generator;

import gen.GrammarLexer;
import gen.GrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Integer, String> PATHS = Map.of(
            1, "src/main/java/example/calculator/calculator.txt",
            2, "src/main/java/example/python/python.txt",
            3, "src/main/java/example/notLL/notLL.txt"
    );

    private static String getGrammarName() {
        System.out.println("1 - calculator    2 - python     3 - notLL");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        while (!PATHS.containsKey(id)) {
            System.out.println("Wrong input. Try again");
            id = in.nextInt();
        }
        return PATHS.get(id);
    }

    public static void main(String[] args) throws IOException {
        String fileName = getGrammarName();
        GrammarLexer lexer = new GrammarLexer(CharStreams.fromFileName(fileName));
        TokenStream tokenStream = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(tokenStream);

        Grammar grammar = parser.start().grammar;
        try {
            grammar.constructFirst();
            grammar.constructFollow();
            grammar.checkLL1();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        LexerGenerator lexerGenerator = new LexerGenerator(grammar);
        ParserGenerator parserGenerator = new ParserGenerator(grammar);

        lexerGenerator.generate();
        parserGenerator.generate();
    }
}
