{
module Parser where
import Lexer	
}

%name parser

%tokentype { Token }
%error { parseError }

%token
    '!'     { TokenNot   }
    '&'     { TokenAnd   }
    '|'     { TokenOr    }
    "->"    { TokenArrow }
    var     { TVar $$}
    '('     { TokenLeftBr  }
    ')'     { TokenRightBr  }

%%

Expr: 
    Disj "->" Expr {ShowImpl $1 $3}
    | Disj {$1}

Disj:
    Disj '|' Conj {ShowDisj $1 $3}
    | Conj {$1}

Conj: 
    Conj '&' Neg {ShowConj $1 $3}
    | Neg {$1}

Neg: 
   '!' Neg {ShowNeg $2}
   | var {Var $1 }
   | '(' Expr ')' {Br $2}


{
parseError :: [Token] -> a
parseError _  = error "Parse error"

data Expr = ShowImpl Expr Expr 
            | ShowDisj Expr Expr 
            | ShowConj Expr Expr 
            | ShowNeg Expr 
            | Var String 
            | Br Expr deriving (Ord, Eq)
}

