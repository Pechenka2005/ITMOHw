package translator;

public class Expr {
    private final String sign;
    private final Expr leftExpr;
    private final Expr rightExpr;
    private final Expr thirdExpr;

    public Expr(String sign, Expr leftExpr, Expr rightExpr) {
        this(sign, leftExpr, rightExpr, null);
    }
    public Expr(String sign, Expr leftExpr, Expr rightExpr, Expr thirdExpr) {
        this.sign = sign;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.thirdExpr = thirdExpr;
    }

    public Expr(String sign) {
        this(sign, null, null);
    }

    @Override
    public String toString() {
        if (thirdExpr != null) {
            return sign + "(" + leftExpr.toString() + ", " + rightExpr.toString() + ", " +thirdExpr.toString() + ")";
        }
        if (leftExpr == null && rightExpr == null) {
            return sign;
        }
        return "(" + leftExpr.toString() + " " + sign + " " + rightExpr.toString() + ")";
    }
}
