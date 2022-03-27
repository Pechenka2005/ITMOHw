import java.io.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        final String FILE = "res.txt";
        final String test = "lambda n, m: n + 2 ** 4";
        InputStream is = new ByteArrayInputStream(test.getBytes());
        try {
            Parser parser = new Parser();
            Tree result = parser.parse(is);
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE)))) {
                writer.write(dfs(result));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String dfs(Tree t) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        sb.append(t.toStr().toString());
        sb.append("}");
        return sb.toString();
    }
}
