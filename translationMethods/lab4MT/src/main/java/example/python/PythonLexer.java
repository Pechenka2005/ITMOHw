package example.python;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PythonLexer {
	public StringBuilder input;
	private int currPosition = 0;
	public List<CompiledToken> compiledTokens = new ArrayList<>();
	public List<CompiledToken> compiledSkipTokens = new ArrayList<>();
	public List<PythonTokens> usedTokens = new ArrayList<>();
	public List<String> parsedTokens = new ArrayList<>();

	public PythonLexer(String input) throws Exception {
		this.input = new StringBuilder(input);

		compiledTokens.add(new CompiledToken(PythonTokens.LAMBDA, "lambda"));
		compiledTokens.add(new CompiledToken(PythonTokens.COLON, ":"));
		compiledTokens.add(new CompiledToken(PythonTokens.COMMA, ","));
		compiledTokens.add(new CompiledToken(PythonTokens.ADD, "\\+"));
		compiledTokens.add(new CompiledToken(PythonTokens.SUB, "\\-"));
		compiledTokens.add(new CompiledToken(PythonTokens.MUL, "\\*"));
		compiledTokens.add(new CompiledToken(PythonTokens.DIV, "\\/"));
		compiledTokens.add(new CompiledToken(PythonTokens.LBRACKET, "\\("));
		compiledTokens.add(new CompiledToken(PythonTokens.RBRACKET, "\\)"));
		compiledTokens.add(new CompiledToken(PythonTokens.VAR, "[a-z]"));
		compiledTokens.add(new CompiledToken(PythonTokens.NUM, "[1-9][0-9]*|0"));

		compiledSkipTokens.add(new CompiledToken(PythonTokens.SPACE, "[ \n\t\r]+"));

		constructTokens();
	}

	public PythonTokens getCurrToken() {
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
			PythonTokens currToken = findToken();
			if (currToken != null) {
				usedTokens.add(currToken);
			} else if (!findSkipToken()) {
				throw new Exception("Cannot match tokens");
			}
		}
		usedTokens.add(PythonTokens.$);
	}

	private PythonTokens findToken() {
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
		public PythonTokens token;
		public Pattern pattern;

		public CompiledToken(PythonTokens token, String pattern) {
			this.token = token;
			this.pattern = Pattern.compile(pattern);
		}
	}
}