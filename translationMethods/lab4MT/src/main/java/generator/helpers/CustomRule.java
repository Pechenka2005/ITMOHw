package generator.helpers;

import java.util.ArrayList;
import java.util.List;

public class CustomRule {
    public List<String> items = new ArrayList<>();
    public List<String> parameters = new ArrayList<>();
    public List<String> codeLines = new ArrayList<>();

    public void addRule(String item, String currParameters, String currCodeLines) {
        items.add(item);
        parameters.add(currParameters);
        codeLines.add(currCodeLines);
    }
}
