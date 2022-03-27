public enum Token {
    LAMBDA("lambda"),
    COLON(":"),
    VAR("var"),
    OB("("),
    CB(")"),
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/"),
    NUM("number"),
    COMMA(","),
    END(""),
    EXP("**");

    private final String value;

    Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }


}
