package translator;


public class Utils {
    public static String result;

    public static void st(Env env) {
        result = "#include <iostream>\n\n\n" +
                "using namespace std;\n"
                + "int main() {\n" +
                env.toString(1) +
                "\n}";
    }

    public static String getNTabs(int n) {
        return "\t".repeat(Math.max(0, n));
    }
}
