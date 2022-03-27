package calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorParser {
	public CalculatorLexer lexer;
	public Node tree;

	public CalculatorParser(CalculatorLexer lexer) throws Exception {
		this.lexer = lexer;
		buildTree();
	}

	public void buildTree() throws Exception {
		tree = build_s();
		if (lexer.getCurrToken() != CalculatorTokens.$) {
			throw new Exception("The end of the input. Expected $, but get " + lexer.getCurrToken());
		}
	}

	public int getValue() throws Exception {
		return ((Node_s)tree).value;
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

	private class Node_divOp extends Node {
		public int value;
		public Node_divOp() {
			super("divOp");
		}
	}

	private class Node_s extends Node {
		public int value;
		public Node_s() {
			super("s");
		}
	}

	private class Node_mulOp extends Node {
		public int value;
		public Node_mulOp() {
			super("mulOp");
		}
	}

	private class Node_restart extends Node {
		public int value;
		public Node_restart() {
			super("restart");
		}
	}

	private class Node_addOp extends Node {
		public int value;
		public Node_addOp() {
			super("addOp");
		}
	}

	private class Node_powOp extends Node {
		public int value;
		public Node_powOp() {
			super("powOp");
		}
	}

	private class Node_addOrSub extends Node {
		public int value;
		public Node_addOrSub() {
			super("addOrSub");
		}
	}

	private class Node_mulOrDiv extends Node {
		public int value;
		public Node_mulOrDiv() {
			super("mulOrDiv");
		}
	}

	private class Node_Pow extends Node {
		public int value;
		public Node_Pow() {
			super("Pow");
		}
	}

	private class Node_subOp extends Node {
		public int value;
		public Node_subOp() {
			super("subOp");
		}
	}

	private class Node_betweenSecond extends Node {
		public int value;
		public Node_betweenSecond() {
			super("betweenSecond");
		}
	}

	private class Node_between extends Node {
		public int value;
		public Node_between() {
			super("between");
		}
	}

	private Node_divOp build_divOp(int l,int r) throws Exception {
		Node_divOp res = new Node_divOp();
		switch (lexer.getCurrToken()) {
			case DIV:
			case RB:
			case ADD:
			case $:
			case MUL:
			case MINUS: {
				res.value = l / r;
				return res;
			}
			default: {
				throw new Exception("Unexpected token in state divOp");
			}
		}
	}

	private Node_s build_s() throws Exception {
		Node_s res = new Node_s();
		switch (lexer.getCurrToken()) {
			case SQR:
			case LB:
			case NUM:
			case MINUS: {
				Node_betweenSecond node0 = build_betweenSecond();
				res.addChild(node0);
				int first = node0.value;

				Node_mulOrDiv node1 = build_mulOrDiv(first);
				res.addChild(node1);
				int after = node1.value;

				Node_addOrSub node2 = build_addOrSub(after);
				res.addChild(node2);
				res.value = node2.value;

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state s");
			}
		}
	}

	private Node_mulOp build_mulOp(int l,int r) throws Exception {
		Node_mulOp res = new Node_mulOp();
		switch (lexer.getCurrToken()) {
			case DIV:
			case RB:
			case ADD:
			case $:
			case MUL:
			case MINUS: {
				res.value = l * r;
				return res;
			}
			default: {
				throw new Exception("Unexpected token in state mulOp");
			}
		}
	}

	private Node_restart build_restart() throws Exception {
		Node_restart res = new Node_restart();
		switch (lexer.getCurrToken()) {
			case NUM: {
				if (CalculatorTokens.NUM != lexer.getCurrToken()) {
					throw new Exception("Expected NUM token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("NUM"));
				res.value = Integer.parseInt(lexer.getCurrTokenStringRepresentation());
				lexer.toNextToken();

				return res;
			}
			case MINUS: {
				if (CalculatorTokens.MINUS != lexer.getCurrToken()) {
					throw new Exception("Expected MINUS token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("MINUS"));
				lexer.toNextToken();

				Node_restart node0 = build_restart();
				res.addChild(node0);
				res.value = -1 * node0.value;

				return res;
			}
			case LB: {
				if (CalculatorTokens.LB != lexer.getCurrToken()) {
					throw new Exception("Expected LB token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("LB"));
				lexer.toNextToken();

				Node_s node0 = build_s();
				res.addChild(node0);
				res.value = node0.value;

				if (CalculatorTokens.RB != lexer.getCurrToken()) {
					throw new Exception("Expected RB token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("RB"));
				lexer.toNextToken();

				return res;
			}
			case SQR: {
				if (CalculatorTokens.SQR != lexer.getCurrToken()) {
					throw new Exception("Expected SQR token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("SQR"));
				lexer.toNextToken();

				if (CalculatorTokens.LB != lexer.getCurrToken()) {
					throw new Exception("Expected LB token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("LB"));
				lexer.toNextToken();

				Node_s node0 = build_s();
				res.addChild(node0);
				res.value = node0.value * node0.value;

				if (CalculatorTokens.RB != lexer.getCurrToken()) {
					throw new Exception("Expected RB token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("RB"));
				lexer.toNextToken();

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state restart");
			}
		}
	}

	private Node_addOp build_addOp(int l,int r) throws Exception {
		Node_addOp res = new Node_addOp();
		switch (lexer.getCurrToken()) {
			case RB:
			case ADD:
			case $:
			case MINUS: {
				res.value = l + r;
				return res;
			}
			default: {
				throw new Exception("Unexpected token in state addOp");
			}
		}
	}

	private Node_powOp build_powOp(int l,int r) throws Exception {
		Node_powOp res = new Node_powOp();
		switch (lexer.getCurrToken()) {
			case DIV:
			case RB:
			case ADD:
			case $:
			case MUL:
			case MINUS: {
				res.value = (int) Math.pow(l, r);
				return res;
			}
			default: {
				throw new Exception("Unexpected token in state powOp");
			}
		}
	}

	private Node_addOrSub build_addOrSub(int l) throws Exception {
		Node_addOrSub res = new Node_addOrSub();
		switch (lexer.getCurrToken()) {
			case RB:
			case $: {
				res.value = l;
				return res;
			}
			case ADD: {
				if (CalculatorTokens.ADD != lexer.getCurrToken()) {
					throw new Exception("Expected ADD token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("ADD"));
				lexer.toNextToken();

				Node_between node0 = build_between();
				res.addChild(node0);
				int r = node0.value;

				Node_addOp node1 = build_addOp(l, r);
				res.addChild(node1);
				int acc = node1.value;

				Node_addOrSub node2 = build_addOrSub(acc);
				res.addChild(node2);
				res.value = node2.value;

				return res;
			}
			case MINUS: {
				if (CalculatorTokens.MINUS != lexer.getCurrToken()) {
					throw new Exception("Expected MINUS token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("MINUS"));
				lexer.toNextToken();

				Node_between node0 = build_between();
				res.addChild(node0);
				int r = node0.value;

				Node_subOp node1 = build_subOp(l, r);
				res.addChild(node1);
				int acc = node1.value;

				Node_addOrSub node2 = build_addOrSub(acc);
				res.addChild(node2);
				res.value = node2.value;

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state addOrSub");
			}
		}
	}

	private Node_mulOrDiv build_mulOrDiv(int l) throws Exception {
		Node_mulOrDiv res = new Node_mulOrDiv();
		switch (lexer.getCurrToken()) {
			case RB:
			case ADD:
			case $:
			case MINUS: {
				res.value = l;
				return res;
			}
			case MUL: {
				if (CalculatorTokens.MUL != lexer.getCurrToken()) {
					throw new Exception("Expected MUL token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("MUL"));
				lexer.toNextToken();

				Node_betweenSecond node0 = build_betweenSecond();
				res.addChild(node0);
				int r = node0.value;

				Node_mulOp node1 = build_mulOp(l, r);
				res.addChild(node1);
				int acc = node1.value;

				Node_mulOrDiv node2 = build_mulOrDiv(acc);
				res.addChild(node2);
				res.value = node2.value;

				return res;
			}
			case DIV: {
				if (CalculatorTokens.DIV != lexer.getCurrToken()) {
					throw new Exception("Expected DIV token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("DIV"));
				lexer.toNextToken();

				Node_betweenSecond node0 = build_betweenSecond();
				res.addChild(node0);
				int r = node0.value;

				Node_divOp node1 = build_divOp(l, r);
				res.addChild(node1);
				int acc = node1.value;

				Node_mulOrDiv node2 = build_mulOrDiv(acc);
				res.addChild(node2);
				res.value = node2.value;

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state mulOrDiv");
			}
		}
	}

	private Node_Pow build_Pow(int calculated) throws Exception {
		Node_Pow res = new Node_Pow();
		switch (lexer.getCurrToken()) {
			case DIV:
			case RB:
			case ADD:
			case $:
			case MUL:
			case MINUS: {
				res.value = calculated;
				return res;
			}
			case POW: {
				if (CalculatorTokens.POW != lexer.getCurrToken()) {
					throw new Exception("Expected POW token, but get " + lexer.getCurrToken());
				}
				res.addChild(new Node("POW"));
				lexer.toNextToken();

				Node_restart node0 = build_restart();
				res.addChild(node0);
				int newValue = node0.value;

				Node_Pow node1 = build_Pow(newValue);
				res.addChild(node1);
				int acc = node1.value;

				Node_powOp node2 = build_powOp(calculated, acc);
				res.addChild(node2);
				res.value = node2.value;

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state Pow");
			}
		}
	}

	private Node_subOp build_subOp(int l,int r) throws Exception {
		Node_subOp res = new Node_subOp();
		switch (lexer.getCurrToken()) {
			case RB:
			case ADD:
			case $:
			case MINUS: {
				res.value = l - r;
				return res;
			}
			default: {
				throw new Exception("Unexpected token in state subOp");
			}
		}
	}

	private Node_betweenSecond build_betweenSecond() throws Exception {
		Node_betweenSecond res = new Node_betweenSecond();
		switch (lexer.getCurrToken()) {
			case SQR:
			case LB:
			case NUM:
			case MINUS: {
				Node_restart node0 = build_restart();
				res.addChild(node0);
				int first = node0.value;

				Node_Pow node1 = build_Pow(first);
				res.addChild(node1);
				res.value = node1.value;

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state betweenSecond");
			}
		}
	}

	private Node_between build_between() throws Exception {
		Node_between res = new Node_between();
		switch (lexer.getCurrToken()) {
			case SQR:
			case LB:
			case NUM:
			case MINUS: {
				Node_betweenSecond node0 = build_betweenSecond();
				res.addChild(node0);
				int first = node0.value;

				Node_mulOrDiv node1 = build_mulOrDiv(first);
				res.addChild(node1);
				res.value = node1.value;

				return res;
			}
			default: {
				throw new Exception("Unexpected token in state between");
			}
		}
	}

}