package translator;

public class Cond {
    public String condition;
    public Expr leftExpr;
    public Expr rightExpr;
    public Env leftEnv;
    public Env rightEnv;


    public Cond(String condition, Expr leftExpr, Expr rightExpr, Env leftEnv, Env rightEnv) {
        this.condition = condition;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
        this.leftEnv = leftEnv;
        this.rightEnv = rightEnv;
    }


    public String toString(int countTabs) {
        String tabs = Utils.getNTabs(countTabs);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tabs)
                .append("if (")
                .append(leftExpr.toString()).append(" ").append(condition).append(" ").append(rightExpr.toString()).append(") { ")
                .append("\n")
                .append(leftEnv.toString(countTabs + 1))
                .append("\n")
                .append(tabs).append("}");
        if (rightEnv != null) {
            stringBuilder.append(" ").append("else {").append("\n")
                    .append(rightEnv.toString(countTabs + 1)).append("\n")
                    .append(tabs).append("}");
        }
        return stringBuilder.toString();
    }
}
