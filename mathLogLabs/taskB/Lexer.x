{
module Lexer where
}

%wrapper "basic"

$digit = 0-9
$alpha = [A-Z]

tokens :-

    $white                      ;
	\!                       { \s -> TokenNot   }
    "|-"                     { \s -> TokenTurn }
    \,                       { \s -> TokenComma }
	\&                       { \s -> TokenAnd   }
	\|                       { \s -> TokenOr    }
	"->"                     { \s -> TokenArrow }
	\(                       { \s -> TokenLeftBr  }
	\)                       { \s -> TokenRightBr  }
    $alpha [$alpha $digit ']* { \s -> TVar s }     

{
data Token = TokenTurn 
            | TokenComma 
            | TokenArrow 
            | TokenNot 
            | TokenAnd 
            | TokenOr 
            | TokenLeftBr 
            | TokenRightBr 
            | TVar String deriving (Eq, Show)
}