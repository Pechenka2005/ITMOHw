{
module Lexer where
}

%wrapper "basic"

$digit = 0-9
$alpha = [A-Z]

tokens :-

    $white                      ;
	\!                       { \s -> TokenNot   }
	\&                       { \s -> TokenAnd   }
	\|                       { \s -> TokenOr    }
	"->"                     { \s -> TokenArrow }
	\(                       { \s -> TokenLeftBr  }
	\)                       { \s -> TokenRightBr  }
    $alpha [$alpha $digit ']* { \s -> TVar s }     

{
data Token = TokenArrow | TokenNot | TokenAnd | TokenOr | TokenLeftBr | TokenRightBr | TVar String deriving (Eq, Show)
}