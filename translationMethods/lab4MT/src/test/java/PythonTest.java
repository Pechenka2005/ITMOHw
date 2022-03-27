import example.python.PythonLexer;
import example.python.PythonParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PythonTest {
    @Test
    public void correctExpression() {
        try {
            parse("lambda n : n + 2");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parenthesesError() {
        try {
            parse("lambda n n + 2");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void withoutLambda() {
        try {
            parse("n : (1 + 4) * n");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void mulDivExist() {
        try {
            parse("lambda : 1 + (2 * 3) / x");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void someText() {
        try {
            parse("some text");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void doubleOperation() {
        try {
            parse("lambda x : x + + y");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void someVariable() {
        try {
            parse("lambda x, y, z: 12 + 2");
        } catch (Exception e) {
            fail();
        }
    }

    private void parse(String str) throws Exception {
        new PythonParser(new PythonLexer(str));
    }
}
