package generator;

import generator.helpers.CustomRule;
import generator.helpers.CustomState;
import generator.helpers.CustomToken;

import java.io.IOException;
import java.util.*;

public class Grammar {
    public String name;
    public String startStateName;

    public List<CustomToken> tokens = new ArrayList<>();
    public List<CustomToken> tokensToSkip = new ArrayList<>();
    public Map<String, CustomToken> nameToToken = new HashMap<>();
    public Map<String, CustomState> nameToState = new HashMap<>();

    private void checkUniqueTokenName(String tokenName) {
        if (nameToToken.containsKey(tokenName)) {
            System.err.println("Token with name " + tokenName + " has already existed");
            System.exit(-1);
        }
    }

    private void checkUniqueStateName(String stateName) {
        if (nameToState.containsKey(stateName)) {
            System.err.println("State with name " + stateName + " has already existed");
            System.exit(-1);
        }
    }

    public void addToken(String tokenName, String regExp, boolean isSkipToken) {
        checkUniqueTokenName(tokenName);

        CustomToken token = new CustomToken(tokenName, regExp);
        if (isSkipToken) {
            tokensToSkip.add(token);
        } else {
            tokens.add(token);
        }
        nameToToken.put(tokenName, token);
    }

    public void addState(CustomState state) {
        checkUniqueStateName(state.name);

        nameToState.put(state.name, state);
    }

    private ArrayList<CustomState> getStates() {
        return new ArrayList<>(nameToState.values());
    }

    public HashSet<String> constructFirstForRule(CustomRule rule) {
        HashSet<String> firstForRule = new HashSet<>();
        for (String item : rule.items) {
            if (item.equals("EPS")) {
                firstForRule.add(item);
            } else if (nameToToken.containsKey(item)) {
                firstForRule.add(item);
                break;
            } else if (nameToState.containsKey(item)) {
                firstForRule.addAll(nameToState.get(item).first);
                if (!firstForRule.contains("EPS")) {
                    break;
                }
            }
        }
        return firstForRule;
    }

    public void constructFirst() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (CustomState state : getStates()) {
                for (CustomRule rule : state.rules) {
                    for (String item : rule.items) {
                        if (item.equals("EPS")) {
                            changed |= state.addToFirst(item);
                        } else if (nameToToken.containsKey(item)) {
                            changed |= state.addToFirst(item);
                            break;
                        } else if (nameToState.containsKey(item)) {
                            CustomState foundedState = nameToState.get(item);
                            for (String itemFromFirst : foundedState.first) {
                                changed |= state.addToFirst(itemFromFirst);
                            }
                            if (!foundedState.hasEpsRule) {
                                break;
                            }
                        } else {
                            System.err.println("Can not find an item " + item + " from a state " + state.name);
                            System.exit(-1);
                        }
                    }
                }
            }
        }
    }

    public void constructFollow() {
        CustomState startState = nameToState.get(startStateName);
        startState.addToFollow("$");
        boolean changed = true;
        while (changed) {
            changed = false;
            for (CustomState state : getStates()) {
                for (CustomRule rule : state.rules) {
                    Set<String> currFirst = new HashSet<>();
                    currFirst.add("$");
                    for (int i = rule.items.size() - 1; i >= 0; i--) {
                        String item = rule.items.get(i);
                        if (item.equals("EPS")) {
                            currFirst.add("EPS");
                        } else if (nameToToken.containsKey(item)) {
                            currFirst.clear();
                            currFirst.add(item);
                        } else if (nameToState.containsKey(item)) {
                            CustomState foundedState = nameToState.get(item);
                            for (String itemFromCurrFirst : currFirst) {
                                if (!itemFromCurrFirst.equals("EPS")) {
                                    changed |= foundedState.addToFollow(itemFromCurrFirst);
                                }
                            }
                            if (currFirst.contains("$")) {
                                for (String itemFromFollow : state.follow) {
                                    changed |= foundedState.addToFollow(itemFromFollow);
                                }
                            }

                            if (!foundedState.hasEpsRule) {
                                currFirst.clear();
                            }
                            currFirst.addAll(foundedState.first);
                        } else {
                            System.err.println("Can not find an item " + item + " from a state " + state.name);
                            System.exit(-1);
                        }
                    }
                }
            }
        }
    }

    public void checkLL1() throws IOException {
        for (CustomState state : getStates()) {
            List<CustomRule> rules = state.rules;
            for (int i = 0; i < rules.size(); i++) {
                for (int j = i + 1; j < rules.size(); j++) {
                    Set<String> first1 = constructFirstForRule(rules.get(i));
                    Set<String> first2 = constructFirstForRule(rules.get(j));
                    Set<String> checker;
                    if (!first1.contains("EPS")) {
                        checker = new HashSet<>(first1);
                        checker.retainAll(first2);
                    } else {
                        checker = new HashSet<>(first2);
                        checker.retainAll(state.follow);
                    }

                    if (!checker.isEmpty()) {
                        throw new IOException("Grammar is not LL1");
                    }
                }
            }
        }
    }
}
