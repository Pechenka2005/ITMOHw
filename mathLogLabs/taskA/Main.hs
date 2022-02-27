module Main where
import qualified Data.Map as M
import Lexer
import Parser

instance Show Expr where
    show (ShowImpl a b) = "(->," ++ show a ++ "," ++ show b ++ ")"
    show (ShowDisj a b) = "(|," ++ show a ++ "," ++ show b ++ ")"
    show (ShowConj a b) = "(&," ++ show a ++ "," ++ show b ++ ")"
    show (ShowNeg exp) = "(!" ++ show exp ++ ")"
    show (Var var) = var
    show (Br exp) = show exp
    

main :: IO ()
main = do 
    s <- getLine
    putStrLn $ show $ parser $ alexScanTokens s
