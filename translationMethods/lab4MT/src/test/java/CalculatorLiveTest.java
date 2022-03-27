import example.calculator.CalculatorLexer;
import example.calculator.CalculatorParser;

import java.util.Scanner;

public class CalculatorLiveTest {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            while (true) {
                String line = in.nextLine();
                CalculatorLexer lexer = new CalculatorLexer(line);
                CalculatorParser parser = new CalculatorParser(lexer);
                System.out.println("Result is " + parser.getValue() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
