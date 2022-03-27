import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import translator.ToImpLexer;
import translator.ToImpParser;
import translator.Utils;

public class Main {
    public static void main(String[] args) {
        ToImpLexer lexer = new ToImpLexer(CharStreams.fromString("if == 4 4 print max 1 2 3 if > max 4 5 6 6 print 1 print 2"));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        ToImpParser parser = new ToImpParser(tokenStream);
        parser.st();
        System.out.println(Utils.result);
    }
}
