package translator;

public class Body {
    private final Expr expr;
    private final String var;

    public Body(Expr expr, String var) {
        this.expr = expr;
        this.var = var;
    }

    public Body(Expr expr) {
        this.expr = expr;
        this.var = null;
    }

    public String toString(int countTabs) {
        String tabs = Utils.getNTabs(countTabs);
        if (var == null) {
            return tabs + "cout << " + expr.toString() + ";";
        }
        return tabs + var + " = " + expr + ";";
    }
}
