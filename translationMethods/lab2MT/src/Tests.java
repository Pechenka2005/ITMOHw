import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;

public class Tests {

    Parser parser = new Parser();

    private final String LAMBDA = "lambda";
    private final String S = "S";
    private final String V = "V";
    private final String VAR = "var";
    private final String E = "E";
    private final String EMPTY = "";
    private final String ADD = "+";
    private final String SUB = "-";
    private final String MUL = "*";
    private final String DIV = "/";
    private final String COLON = ":";
    private final String T = "T";
    private final String F = "F";
    private final String M = "M";
    private final String NUM = "number";
    private final String E_i = "E'";
    private final String X = "X";
    private final String COMMA = ",";
    private final String OB = "(";
    private final String CB = ")";



    @Test
    public void sample() throws ParseException {

        String testCase = "lambda n: n + 2";
        InputStream is = getInputStream(testCase);
        Tree expectedTree = Tree.of(
                S,
                Tree.of(LAMBDA),
                Tree.of(V,
                        Tree.of(VAR),
                        Tree.of(EMPTY)),
                Tree.of(COLON),
                Tree.of(E,
                        Tree.of(T,
                                Tree.of(F,
                                        Tree.of(VAR)),
                                Tree.of(EMPTY)),
                        Tree.of(E_i,
                                Tree.of(ADD),
                                Tree.of(T,
                                        Tree.of(F,
                                                Tree.of(NUM)),
                                        Tree.of(EMPTY)),
                                Tree.of(EMPTY)))
        );
        Tree actualTree = parser.parse(is);
        Assert.assertEquals(expectedTree, actualTree);
    }

    @Test
    public void hardTest() throws ParseException {

        String testCase = "lambda n, a, x: ((n + 2) + (a * 4)) + 2 / 3";
        InputStream is = getInputStream(testCase);
        Tree expectedTree = Tree.of(
                S,
                Tree.of(LAMBDA),
                Tree.of(V,
                        Tree.of(VAR),
                        Tree.of(X,
                                Tree.of(COMMA),
                                Tree.of(VAR),
                                Tree.of(X,
                                        Tree.of(COMMA),
                                        Tree.of(VAR),
                                        Tree.of(EMPTY)))),
                Tree.of(COLON),
                Tree.of(E,
                        Tree.of(T,
                                Tree.of(F,
                                        Tree.of(OB),
                                        Tree.of(E,
                                                Tree.of(T,
                                                        Tree.of(F,
                                                                Tree.of(OB),
                                                                Tree.of(E,
                                                                        Tree.of(T,
                                                                                Tree.of(F,
                                                                                        Tree.of(VAR)),
                                                                                Tree.of(EMPTY)),
                                                                        Tree.of(E_i,
                                                                                Tree.of(ADD),
                                                                                Tree.of(T,
                                                                                        Tree.of(F,
                                                                                                Tree.of(NUM)),
                                                                                        Tree.of(EMPTY)),
                                                                                Tree.of(EMPTY))),
                                                                Tree.of(CB)),
                                                        Tree.of(EMPTY)),
                                                Tree.of(E_i,
                                                        Tree.of(ADD),
                                                        Tree.of(T,
                                                                Tree.of(F,
                                                                        Tree.of(OB),
                                                                        Tree.of(E,
                                                                                Tree.of(T,
                                                                                        Tree.of(F, Tree.of(VAR)),
                                                                                        Tree.of(M,
                                                                                                Tree.of(MUL),
                                                                                                Tree.of(F,Tree.of(NUM)),
                                                                                                Tree.of(EMPTY))),
                                                                                Tree.of(EMPTY)),
                                                                        Tree.of(CB)),
                                                                Tree.of(EMPTY)),
                                                        Tree.of(EMPTY))),
                                        Tree.of(CB)),
                                Tree.of(EMPTY)),
                        Tree.of(E_i,
                                Tree.of(ADD),
                                Tree.of(T,
                                        Tree.of(F,
                                                Tree.of(NUM)),
                                        Tree.of(M,
                                                Tree.of(DIV),
                                                Tree.of(F, Tree.of(NUM)),
                                                Tree.of(EMPTY))),
                                Tree.of(EMPTY)))
        );
        Tree actualTree = parser.parse(is);
        Assert.assertEquals(expectedTree, actualTree);
    }

    @Test
    public void withOutLambda() { throwTest("n : n + 2");}

    @Test
    public void withOutColon() { throwTest("lambda n + 2");}

    @Test
    public void emptyFunc() { throwTest("lambda n :");}

    @Test
    public void emptyTest() { throwTest("");}

    @Test
    public void wrongExpr() { throwTest("lambda n: (1 + 2) x (3 + 4)");}

    @Test
    public void wrongExpr2() { throwTest("lambda n: (1 + 2)(3 + 4)");}

    @Test
    public void wrongExpr3() { throwTest("lambda n: (1 + 2))");}

    @Test
    public void wrongExpr4() { throwTest("lambda n: ((1 + n)");}

    private void throwTest(String testCase) {
        Assert.assertThrows(ParseException.class, () -> {
            InputStream is = getInputStream(testCase);
            parser.parse(is);
        });
    }



    private InputStream getInputStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }
}
