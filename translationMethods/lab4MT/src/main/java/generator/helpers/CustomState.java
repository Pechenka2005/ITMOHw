package generator.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomState {
    public String name;
    public List<CustomRule> rules = new ArrayList<>();
    public List<String> parameters = new ArrayList<>();
    public List<String> returnValues = new ArrayList<>();

    public Set<String> first = new HashSet<>();
    public Set<String> follow = new HashSet<>();
    public boolean hasEpsRule = false;

    public void addParameter(String type, String name) {
        parameters.add(type + " "  + name);
    }

    public void addReturnValue(String type, String returnValue) {
        returnValues.add(type + " " + returnValue);
    }

    public void addRule(CustomRule rule) {
        rules.add(rule);
    }

    public boolean addToFirst(String item) {
        if (item.equals("EPS")) {
            hasEpsRule = true;
        }
        return first.add(item);
    }

    public boolean addToFollow(String item) {
        return follow.add(item);
    }
}
