public enum Status {
    S("S"),
    V("V"),
    X("X"),
    E("E"),
    E_SUB("E'"),
    F("F"),
    T("T"),
    M("M"),
    P("P"),
    Q("Q");


    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
