grammar Grammar;

@header {
    import generator.helpers.*;
    import generator.Grammar;
}

start returns [Grammar grammar]
@init {
    $grammar = new Grammar();
} : constructGrammar[$grammar] EOF;

constructGrammar[Grammar grammar] : header[$grammar] startState[$grammar] state[$grammar] tokens[$grammar];

header[Grammar grammar] : '@grammar' NAME ';' { $grammar.name = $NAME.text; };


startState[Grammar grammar] : '@startState' '=' NAME ';' { $grammar.startStateName = $NAME.text; };


state[Grammar grammar]  : '@states' '{' (findState { $grammar.addState($findState.currState); })+ '}';

findState returns [CustomState currState]
@init {
    $currState = new CustomState();
} : NAME { $currState.name = $NAME.text; } ('[' inhState[$currState] ']')? ('returns' '[' syntState[$currState] ']')?
    ':'
    (r0 = findRule { $currState.addRule($r0.currRule); }) ('|' rNext = findRule { $currState.addRule($rNext.currRule); })* ';';

inhState[CustomState currState] : (type0 = NAME name0 = NAME { $currState.addParameter($type0.text, $name0.text); })
                                  (',' typeNext = NAME nameNext = NAME { $currState.addParameter($typeNext.text, $nameNext.text); })*;

syntState[CustomState currState] : (type0 = NAME name0 = NAME { $currState.addReturnValue($type0.text, $name0.text); })
                                   (',' typeNext = NAME nameNext = NAME { $currState.addReturnValue($typeNext.text, $nameNext.text); })*;

findRule returns [CustomRule currRule] locals [StringBuilder parameters, StringBuilder codeLines]
@init {
    $currRule = new CustomRule();
    $parameters = new StringBuilder();
    $codeLines = new StringBuilder();
} : (NAME ('[' inhParameters[$parameters] ']')? (codeLine[$codeLines])? {
    $currRule.addRule($NAME.text, $parameters.toString(), $codeLines.toString());
    $parameters = new StringBuilder();
    $codeLines = new StringBuilder();
})+;

inhParameters[StringBuilder parameters] : (name0 = NAME { $parameters.append($name0.text); })
                                          (',' nameNext = NAME { $parameters.append(", " + $nameNext.text); })*;

codeLine[StringBuilder codeLines] : CODE { $codeLines.append($CODE.text.substring(1, $CODE.text.length() - 1)); };

tokens[Grammar grammar]  : '@tokens' '{' (findToken[grammar])* '}';

findToken[Grammar grammar] locals [boolean isSkipToken]
@init {
    $isSkipToken = false;
} : NAME ':' REGEXP ('->' 'skip' { $isSkipToken = true; })? ';' { $grammar.addToken($NAME.text, $REGEXP.text, $isSkipToken); };

SPACE : [ \t\n\r]+ -> skip;
NAME : [a-zA-Z]+;
REGEXP : '"' (~('"'))* '"';
CODE: '`' (~('`'))+ '`';