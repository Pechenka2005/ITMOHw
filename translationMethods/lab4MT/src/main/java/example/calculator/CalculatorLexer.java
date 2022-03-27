package example.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorLexer {
	public StringBuilder input;
	private int currPosition = 0;
	public List<CompiledToken> compiledTokens = new ArrayList<>();
	public List<CompiledToken> compiledSkipTokens = new ArrayList<>();
	public List<CalculatorTokens> usedTokens = new ArrayList<>();
	public List<String> parsedTokens = new ArrayList<>();

	public CalculatorLexer(String input) throws Exception {
		this.input = new StringBuilder(input);

		compiledTokens.add(new CompiledToken(CalculatorTokens.ADD, "\\+"));
		compiledTokens.add(new CompiledToken(CalculatorTokens.MINUS, "\\-"));
		compiledTokens.add(new CompiledToken(CalculatorTokens.POW, "\\^"));
		compiledTokens.add(new CompiledToken(CalculatorTokens.SQR, "sqr"));
		compiledTokens.add(new CompiledToken(CalculatorTokens.MUL, "\\*"));
		compiledTokens.add(new CompiledToken(CalculatorTokens.DIV, "\\/"));
		compiledTokens.add(new CompiledToken(CalculatorTokens.LB, "\\("));
		compiledTokens.add(new CompiledToken(CalculatorTokens.RB, "\\)"));
		compiledTokens.add(new CompiledToken(CalculatorTokens.NUM, "[1-9][0-9]*|0"));

		compiledSkipTokens.add(new CompiledToken(CalculatorTokens.SPACE, "[ \n\t\r]+"));

		constructTokens();
	}

	public CalculatorTokens getCurrToken() {
		return usedTokens.get(currPosition);
	}

	public void toNextToken() {
		currPosition++;
	}

	public String getCurrTokenStringRepresentation() {
		return parsedTokens.get(currPosition);
	}

	private void constructTokens() throws Exception {
		while (input.length() > 0) {
			CalculatorTokens currToken = findToken();
			if (currToken != null) {
				usedTokens.add(currToken);
			} else if (!findSkipToken()) {
				throw new Exception("Cannot match tokens");
			}
		}
		usedTokens.add(CalculatorTokens.$);
	}

	private CalculatorTokens findToken() {
		for (CompiledToken compiled : compiledTokens) {
			Matcher matcher = compiled.pattern.matcher(input.toString());
			if (matcher.lookingAt()) {
				parsedTokens.add(input.substring(0, matcher.end()));
				input.delete(0, matcher.end());
				return compiled.token;
			}
		}
		return null;
	}

	private boolean findSkipToken() {
		for (CompiledToken compiled : compiledSkipTokens) {
			Matcher matcher = compiled.pattern.matcher(input.toString());
			if (matcher.lookingAt()) {
				input.delete(0, matcher.end());
				return true;
			}
		}
		return false;
	}

	private static class CompiledToken {
		public CalculatorTokens token;
		public Pattern pattern;

		public CompiledToken(CalculatorTokens token, String pattern) {
			this.token = token;
			this.pattern = Pattern.compile(pattern);
		}
	}
}