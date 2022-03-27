grammar ToImp;

@header {
package translator;
}

st returns [Env _env]
:
    body {
        $_env = new Env(null, $body._body);
        translator.Utils.st($_env);
    }
    | condition {
        $_env = new Env($condition._cond, null);
        translator.Utils.st($_env);
    }
;

env returns [Env _env]
:
    body {
        $_env = new Env(null, $body._body);
    }
    | condition {
        $_env = new Env($condition._cond, null);
    }
;

body returns [Body _body]
:
    PRINT expr {$_body = new Body($expr._expr);}
    | '=' VAR expr {$_body = new Body($expr._expr, $VAR.text);}
;

condition returns [Cond _cond]
:
    IF ifToken expr1 = expr expr2 = expr envIf = env envElse = env {
        $_cond = new Cond($ifToken.text, $expr1._expr, $expr2._expr, $envIf._env, $envElse._env);
    }
    | IF ifToken expr1 = expr expr2 = expr envIf = env {
        $_cond = new Cond($ifToken.text, $expr1._expr, $expr2._expr, $envIf._env, null);
    }
;

expr returns [Expr _expr]
:
    exprToken expr1 = expr expr2 = expr expr3 = expr {
        $_expr = new Expr($exprToken.text, $expr1._expr, $expr2._expr, $expr3._expr);
    }
    | exprToken expr1 = expr expr2 = expr {
        $_expr = new Expr($exprToken.text, $expr1._expr, $expr2._expr);
    }
    | token {
        $_expr = new Expr($token.text)
    }
;


exprToken returns [String _token]
:
    EXPR_TOKEN {$_token = $EXPR_TOKEN.text;}
;

ifToken returns [String _token]
:
    IF_TOKEN {$_token = $IF_TOKEN.text;}
;



token returns [String _token]
:
    NUM {$_token = $NUM.text;}
    | VAR {$_token = $VAR.text;}
;

IF: 'if';
PRINT: 'print';
IF_TOKEN: ('||'|'&&'|'<='|'>='|'=='|'<'|'>');
EXPR_TOKEN: ('*'|'/'|'+'|'-'|'max');
VAR: [a-zA-Z][a-zA-Z0-9]*;
NUM: [1-9][0-9]*;
SPACE_SKIP: ' ' -> skip;
