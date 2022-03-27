import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.regex.Pattern;

public class Lexer {
    private final InputStream is;
    private int curChar;
    private int curPos;
    private Token curToken;

    private final char COLON = ':';
    private final char OB = '(';
    private final char CB = ')';
    private final char COMMA = ',';
    private final char ADD = '+';
    private final char SUB = '-';
    private final char MUL = '*';
    private final char DIV = '/';
    private final int END = -1;
    private final String LAMBDA = "lambda";
    private final Pattern OPERATION_ADD_SUB = Pattern.compile("[+\\-]");
    private final Pattern OPERATION_MUL_DIV = Pattern.compile("[*/]");

    private final Pattern NUM = Pattern.compile("[1-9][0-9]*");

    public Lexer(InputStream is) throws ParseException {
        this.is = is;
        curPos = 0;
        nextChar();
    }

    private boolean isBlank(int c) {
        return Character.isWhitespace(c);
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), curPos);
        }
    }

    private void setAndNext(Token token) throws ParseException {
        curToken = token;
        nextChar();
    }


    public void nextToken() throws ParseException {
        while (isBlank(curChar)) {
            nextChar();
        }
        switch (curChar) {
            case COLON -> setAndNext(Token.COLON);
            case OB -> setAndNext(Token.OB);
            case CB -> setAndNext(Token.CB);
            case COMMA -> setAndNext(Token.COMMA);
            case END -> setAndNext(Token.END);
            case ADD -> setAndNext(Token.ADD);
            case SUB -> setAndNext(Token.SUB);
            case MUL -> mullCase();
            case DIV -> setAndNext(Token.DIV);
            default -> defaultCase();
        }
    }

    private void mullCase() throws ParseException {
        nextChar();
        if (curChar == MUL) {
            setAndNext(Token.EXP);
        } else {
            curToken = Token.MUL;
        }
    }


    private void defaultCase() throws ParseException {
        StringBuilder builder = new StringBuilder();
        while (!(curChar == END ||
                curChar == COLON ||
                curChar == OB ||
                curChar == CB ||
                curChar == ADD ||
                curChar == SUB ||
                curChar == DIV ||
                curChar == MUL ||
                curChar == COMMA ||
                isBlank(curChar))) {
            builder.append((char) curChar);
            nextChar();
        }
        final String curString = builder.toString();
        if (curString.equals(LAMBDA)) {
            curToken = Token.LAMBDA;
        } else if (NUM.matcher(curString).matches()) {
            curToken = Token.NUM;
        } else {
            curToken = Token.VAR;
        }

    }

    public Token getCurToken() {
        return curToken;
    }

    public int getCurPos() {
        return curPos;
    }






}
