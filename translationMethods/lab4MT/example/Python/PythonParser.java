package python;

import java.util.ArrayList;
import java.util.List;

public class PythonParser {
	public PythonLexer lexer;
	public Node tree;

	public PythonParser(PythonLexer lexer) throws Exception {
		this.lexer = lexer;
		buildTree();
	}

	public void buildTree() throws Exception {
		tree = build_s();
		if (lexer.getCurrToken() != PythonTokens.$) {
			throw new Exception("The end of the input. Expected $, but get " + lexer.getCurrToken());
		}
	}

	private class Node {
		public String name;
		public List<Node> children = new ArrayList<>();

		public Node(String name) {
			this.name = name;
		}

		public void addChild(Node node) {
			children.add(node);
		}
	}

	private class Node_ee extends Node {
		public Node_ee() {
			super("ee");
		}
	}

	private class Node_s extends Node {
		public Node_s() {
			super("s");
		}
	}

	private class Node_t extends Node {
		public Node_t() {
			super("t");
		}
	}

	private class Node_e extends Node {
		public Node_e() {
			super("e");
		}
	}

	private class Node_v extends Node {
		public Node_v() {
			super("v");
		}
	}

	private class Node_f extends Node {
		public Node_f() {
			super("f");
		}
	}

	private class Node_x extends Node {
		public Node_x() {
			super("x");
		}
	}

	private class Node_m extends Node {
		public Node_m() {
			super("m");
		}
	}

	private Node_ee build_ee() throws Exception {
		Node_ee res = new Node_ee();
		switch (lexer.getCurrToken()) {
			case ADD: {
				if (PythonTokens.ADD != lexer.getCurrToken()) {
					throw new Exception("Expected ADD token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("ADD"));
				lexer.toNextToken();

				Node_t node0 = build_t();
				res.addChild(node0);

				Node_ee node1 = build_ee();
				res.addChild(node1);

				return res;
			}
			case SUB: {
				if (PythonTokens.SUB != lexer.getCurrToken()) {
					throw new Exception("Expected SUB token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("SUB"));
				lexer.toNextToken();

				Node_t node0 = build_t();
				res.addChild(node0);

				Node_ee node1 = build_ee();
				res.addChild(node1);

				return res;
			}
			case $:
			case RBRACKET: {
				return res;
			}
			default: {
				throw new Exception("Unexpected token in state ee");
			}
		}
	}

	private Node_s build_s() throws Exception {
		Node_s res = new Node_s();
		switch (lexer.getCurrToken()) {
			case LAMBDA: {
				if (PythonTokens.LAMBDA != lexer.getCurrToken()) {
					throw new Exception("Expected LAMBDA token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("LAMBDA"));
				lexer.toNextToken();

				Node_v node0 = build_v();
				res.addChild(node0);

				if (PythonTokens.COLON != lexer.getCurrToken()) {
					throw new Exception("Expected COLON token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("COLON"));
				lexer.toNextToken();

				Node_e node1 = build_e();
				res.addChild(node1);

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state s");
			}
		}
	}

	private Node_t build_t() throws Exception {
		Node_t res = new Node_t();
		switch (lexer.getCurrToken()) {
			case VAR:
			case NUM:
			case LBRACKET: {
				Node_f node0 = build_f();
				res.addChild(node0);

				Node_m node1 = build_m();
				res.addChild(node1);

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state t");
			}
		}
	}

	private Node_e build_e() throws Exception {
		Node_e res = new Node_e();
		switch (lexer.getCurrToken()) {
			case VAR:
			case NUM:
			case LBRACKET: {
				Node_t node0 = build_t();
				res.addChild(node0);

				Node_ee node1 = build_ee();
				res.addChild(node1);

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state e");
			}
		}
	}

	private Node_v build_v() throws Exception {
		Node_v res = new Node_v();
		switch (lexer.getCurrToken()) {
			case COLON: {
				return res;
			}
			case VAR: {
				if (PythonTokens.VAR != lexer.getCurrToken()) {
					throw new Exception("Expected VAR token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("VAR"));
				lexer.toNextToken();

				Node_x node0 = build_x();
				res.addChild(node0);

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state v");
			}
		}
	}

	private Node_f build_f() throws Exception {
		Node_f res = new Node_f();
		switch (lexer.getCurrToken()) {
			case VAR: {
				if (PythonTokens.VAR != lexer.getCurrToken()) {
					throw new Exception("Expected VAR token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("VAR"));
				lexer.toNextToken();

				return res;
			}
			case NUM: {
				if (PythonTokens.NUM != lexer.getCurrToken()) {
					throw new Exception("Expected NUM token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("NUM"));
				lexer.toNextToken();

				return res;
			}
			case LBRACKET: {
				if (PythonTokens.LBRACKET != lexer.getCurrToken()) {
					throw new Exception("Expected LBRACKET token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("LBRACKET"));
				lexer.toNextToken();

				Node_e node0 = build_e();
				res.addChild(node0);

				if (PythonTokens.RBRACKET != lexer.getCurrToken()) {
					throw new Exception("Expected RBRACKET token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("RBRACKET"));
				lexer.toNextToken();

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state f");
			}
		}
	}

	private Node_x build_x() throws Exception {
		Node_x res = new Node_x();
		switch (lexer.getCurrToken()) {
			case $:
			case COLON: {
				return res;
			}
			case COMMA: {
				if (PythonTokens.COMMA != lexer.getCurrToken()) {
					throw new Exception("Expected COMMA token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("COMMA"));
				lexer.toNextToken();

				if (PythonTokens.VAR != lexer.getCurrToken()) {
					throw new Exception("Expected VAR token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("VAR"));
				lexer.toNextToken();

				Node_x node0 = build_x();
				res.addChild(node0);

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state x");
			}
		}
	}

	private Node_m build_m() throws Exception {
		Node_m res = new Node_m();
		switch (lexer.getCurrToken()) {
			case MUL: {
				if (PythonTokens.MUL != lexer.getCurrToken()) {
					throw new Exception("Expected MUL token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("MUL"));
				lexer.toNextToken();

				Node_f node0 = build_f();
				res.addChild(node0);

				Node_m node1 = build_m();
				res.addChild(node1);

				return res;
			}
			case DIV: {
				if (PythonTokens.DIV != lexer.getCurrToken()) {
					throw new Exception("Expected DIV token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("DIV"));
				lexer.toNextToken();

				Node_f node0 = build_f();
				res.addChild(node0);

				Node_m node1 = build_m();
				res.addChild(node1);

				return res;
			}
			case ADD:
			case SUB:
			case $:
			case RBRACKET: {
				return res;
			}
			default: {
				throw new Exception("Unexpected token in state m");
			}
		}
	}

}