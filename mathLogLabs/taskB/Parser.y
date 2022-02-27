{
module Parser where
import Lexer	
}

%name parseProof Expr
%name parse

%tokentype { Token }
%error { parseError }

%token
    '!'     { TokenNot   }
    "|-"    { TokenTurn }
    ','     { TokenComma }
    '&'     { TokenAnd   }
    '|'     { TokenOr    }
    "->"    { TokenArrow }
    var     { TVar $$}
    '('     { TokenLeftBr  }
    ')'     { TokenRightBr  }

%%

Title:
    Exprs "|-" Expr { ($1, $3) }
    | "|-" Expr { ([], $2) }

Exprs:
    Exprs ',' Expr { ($3 : $1)  }
    | Expr { ($1 : [])  }

Expr: 
    Disj "->" Expr { ShowImpl $1 $3 }
    | Disj { $1 }

Disj:
    Disj '|' Conj { ShowDisj $1 $3 }
    | Conj {$1}

Conj: 
    Conj '&' Neg { ShowConj $1 $3 }
    | Neg { $1 }

Neg: 
   '!' Neg { ShowNeg $2 }
   | var {Var $1 }
   | '(' Expr ')' { $2 }


{
parseError :: [Token] -> a
parseError _  = error "Parse error"

data Expr = ShowImpl Expr Expr 
            | ShowDisj Expr Expr 
            | ShowConj Expr Expr 
            | ShowNeg Expr 
            | Var String 
            | Bottom deriving (Ord, Eq)
}

