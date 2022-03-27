import example.calculator.CalculatorLexer;
import example.calculator.CalculatorParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTest {

    @Test
    public void testAdd() {
        try {
            CalculatorLexer lexer = new CalculatorLexer("2 + 2");
            CalculatorParser parser = new CalculatorParser(lexer);
            assertEquals(4, parser.getValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testSub() {
        try {
            CalculatorLexer lexer = new CalculatorLexer("4 - 2");
            CalculatorParser parser = new CalculatorParser(lexer);
            assertEquals(2, parser.getValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testMul() {
        try {
            CalculatorLexer lexer = new CalculatorLexer("2 * 2");
            CalculatorParser parser = new CalculatorParser(lexer);
            assertEquals(4, parser.getValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testDiv() {
        try {
            CalculatorLexer lexer = new CalculatorLexer("4 / 2");
            CalculatorParser parser = new CalculatorParser(lexer);
            assertEquals(2, parser.getValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testMinus() {
        try {
            CalculatorLexer lexer = new CalculatorLexer("-2 + 4");
            CalculatorParser parser = new CalculatorParser(lexer);
            assertEquals(2, parser.getValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testBrackets() {
        try {
            CalculatorLexer lexer = new CalculatorLexer("(2 + 2) * 2");
            CalculatorParser parser = new CalculatorParser(lexer);
            assertEquals(8, parser.getValue());
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    private boolean exceptionHelper(String context) {
        try {
            CalculatorLexer lexer = new CalculatorLexer(context);
            CalculatorParser parser = new CalculatorParser(lexer);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    @Test
    public void exceptionOne() {
        assertTrue(exceptionHelper("string * 2"));
    }

    @Test
    public void exceptionTwo() {
        assertTrue(exceptionHelper("2 + + 2"));
    }

    @Test
    public void exceptionThree() {
        assertTrue(exceptionHelper("2 + "));
    }
}
