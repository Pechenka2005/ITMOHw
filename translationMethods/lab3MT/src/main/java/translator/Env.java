package translator;

public class Env {
//    private final int level;
    public Body body;
    public Cond cond;

    public Env(Cond cond, Body body) {
//        this.level = level;
        this.body = body;
        this.cond = cond;
    }

    public String toString(int countTabs) {
        if (body == null) {
            return cond.toString(countTabs);
        }
        return body.toString(countTabs);
    }
}
