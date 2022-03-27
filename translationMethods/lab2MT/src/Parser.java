import java.io.InputStream;
import java.text.ParseException;
/*
S = LAMBDA V COLON E
V = eps
V = VAR X
X = eps
X = COMMA VAR X
E = T E'
E' = PLUS T E'
E' = SUB T E'
E' = eps
T = F M
M = MUL F M
M = DIV F M
M = eps
F = P Q
Q = EXP P Q
Q = eps
P = VAR
P = NUM
P = OB E CB
 */
public class Parser {
    private int position = 0;
    private Lexer lexer;

    private int getPos() {
        return position++;
    }
    private Tree S() throws ParseException {
        if (lexer.getCurToken() != Token.LAMBDA) {
            throw getException(Token.LAMBDA);
        }
        lexer.nextToken();
        Tree v = V();
        if (lexer.getCurToken() != Token.COLON) {
            throw getException(Token.COLON);
        }
        lexer.nextToken();
        Tree e = E();
        if (lexer.getCurToken() != Token.END) {
            throw getException(Token.END);
        }
        return new Tree(getPos(), Status.S.getName(),
                new Tree(getPos(), Token.LAMBDA.getValue()),
                v,
                new Tree(getPos(), Token.COLON.getValue()),
                e);
    }

    private Tree E() throws ParseException {
        Tree t = T();
        Tree e = E_SUB();
        return new Tree(getPos(), Status.E.getName(), t, e);
    }

    private Tree E_SUB() throws ParseException {
        Token cur = lexer.getCurToken();
        if (cur == Token.ADD || cur == Token.SUB) {
            lexer.nextToken();
            Tree t = T();
            Tree eSub = E_SUB();
            return new Tree(getPos(),
                    Status.E_SUB.getName(),
                    new Tree(getPos(), cur.getValue()),
                    t,
                    eSub);
        }
        return new Tree(getPos(), "");
    }

    private Tree F() throws ParseException {
        Tree p = P();
        Tree q = Q();
        return new Tree(getPos(), Status.F.getName(), p, q);
    }

    private Tree Q() throws ParseException {
        Token cur = lexer.getCurToken();
        if (cur == Token.EXP) {
            lexer.nextToken();
            Tree p = P();
            Tree q = Q();
            return new Tree(getPos(), Status.Q.getName(),
                    new Tree(getPos(), cur.getValue()),
                    p,
                    q);
        }
        return new Tree(getPos(), "");
    }

    private Tree T() throws ParseException {
        Tree f = F();
        Tree m = M();
        return new Tree(getPos(), Status.T.getName(), f, m);
    }

    private Tree M() throws ParseException {
        Token cur = lexer.getCurToken();
        if (cur == Token.MUL || cur == Token.DIV) {
            lexer.nextToken();
            Tree f = F();
            Tree m = M();
            return new Tree(getPos(), Status.M.getName(),
                    new Tree(getPos(), cur.getValue()),
                    f,
                    m);
        }
        return new Tree(getPos(), "");
    }

    private Tree P() throws ParseException {
        Token cur = lexer.getCurToken();
        if (cur == Token.VAR) {
            lexer.nextToken();
            return new Tree(getPos(), Status.P.getName(), new Tree(getPos(), Token.VAR.getValue()));
        }
        if (cur == Token.NUM) {
            lexer.nextToken();
            return new Tree(getPos(), Status.P.getName(), new Tree(getPos(), Token.NUM.getValue()));
        }
        if (cur == Token.OB) {
            lexer.nextToken();
            Tree e = E();
            if (lexer.getCurToken() == Token.CB) {
                lexer.nextToken();
                return new Tree(getPos(), Status.P.getName(),
                        new Tree(getPos(), Token.OB.getValue()),
                        e,
                        new Tree(getPos(), Token.CB.getValue()));
            }
            throw getException(")");
        }
        throw getException("expr");
    }



    private Tree V() throws ParseException {
        if (lexer.getCurToken() == Token.VAR) {
            lexer.nextToken();
            Tree x = X();
            return new Tree(getPos(), Status.V.getName(),
                    new Tree(getPos(), Token.VAR.getValue()),
                    x);
        }
        return new Tree(getPos(), "");
    }

    private Tree X() throws ParseException {
        if (lexer.getCurToken() == Token.COMMA) {
            lexer.nextToken();
            if (lexer.getCurToken() == Token.VAR) {
                lexer.nextToken();
                Tree x = X();
                return new Tree(getPos(), Status.X.getName(),
                        new Tree(getPos(), Token.COMMA.getValue()),
                        new Tree(getPos(), Token.VAR.getValue()),
                        x);
            } else {
               throw getException(Token.VAR);
            }
        }
        return new Tree(getPos(), "");
    }


    public Tree parse(InputStream is) throws ParseException {
        this.lexer = new Lexer(is);
        lexer.nextToken();
        return S();

    }

    private ParseException getException(String token) {
        return new ParseException("expected token " + token, lexer.getCurPos());
    }

    private ParseException getException(Token token) {
        return getException(token.getValue());
    }

}
