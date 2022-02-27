module Main where
import Lexer
import Parser
import qualified Data.Map as Map
import qualified Data.Set as Set
import Data.List
import Data.Maybe
import GHC.Show

data Transition = Axiom Int Int | Hypot Int Int | MP Int Int Int deriving (Eq, Ord)

instance Show Expr where
    show (ShowImpl a b) = "(" ++ show a ++ " -> " ++ show b ++ ")"
    show (ShowDisj a b) = "(" ++ show a ++ " | " ++ show b ++ ")"
    show (ShowConj a b) = "(" ++ show a ++ " & " ++ show b ++ ")"
    show (ShowNeg exp) = "( " ++ show exp ++ " -> _|_)"
    show (Var var) = var
    show Bottom = "_|_"


main :: IO()
main = interact (unlines . solve . lines)

solve :: [String] -> [String]
solve (h : rest) = do
    let (hypotises, toProof) = (parse . alexScanTokens) h
    let hyps = Map.fromList (zip (reverse hypotises) [1..(length hypotises)])
    let proof = map (parseProof . alexScanTokens) rest
    checkAllLines hypotises toProof Map.empty hyps Map.empty proof 1 Map.empty

showRowProof :: Int -> [Expr] ->  Expr ->  String -> String
showRowProof depth hyps expr def = "[" ++ show depth ++ "] " ++ listShow hyps ++ "|-" ++ show expr ++ " " ++ def

listShow :: [Expr] -> String
listShow list =   if length list == 0
                            then ""
                            else show (head list) ++ "," ++ listShow (tail list)

addToMap :: Expr -> Map.Map Expr [(Expr, Int)] -> Int -> Map.Map Expr [(Expr, Int)]
addToMap expr impls ind = case expr of
    (ShowImpl v k) -> case Map.lookup k impls of
                        Just vs -> Map.insert k ((v, ind) : vs) impls
                        Nothing -> Map.insert k [(v, ind)] impls
    _ -> impls

justToValue :: Maybe a -> a
justToValue (Just a) = a
    
getResult :: [Expr] -> Expr -> Map.Map Int Expr -> Map.Map Expr Transition -> Int -> [String] -> [String]
getResult hyps expr indexToExprs mapProofsLines depth proof =
    case Map.lookup expr mapProofsLines of
        (Just (Axiom _ i)) -> showAxiom depth hyps expr i
        (Just (Hypot _ _)) -> (showRowProof depth hyps expr "[Ax]") : proof
        (Just (MP _ i j)) -> getResult hyps (justToValue (Map.lookup i indexToExprs)) indexToExprs mapProofsLines (depth + 1) [] ++ getResult hyps (justToValue (Map.lookup j indexToExprs)) indexToExprs mapProofsLines (depth + 1) [] ++ (showRowProof depth hyps expr "[E->]") : proof
        
axiom :: Expr -> Int -> Map.Map Expr Transition -> Maybe (Map.Map Expr Transition)
axiom expr ind mapProofsLines = case expr of
    (ShowImpl a (ShowImpl b a')) | a == a' -> helpAxiom 1 expr ind mapProofsLines
    (ShowImpl (ShowImpl a b) (ShowImpl (ShowImpl a' (ShowImpl b' c)) (ShowImpl a'' c'))) | a == a' && a == a'' && b == b' && c == c' -> helpAxiom 2 expr ind mapProofsLines
    (ShowImpl a (ShowImpl b (ShowConj a' b'))) | a == a' && b == b' -> helpAxiom 3 expr ind mapProofsLines
    (ShowImpl (ShowConj a b) a') | a == a' -> helpAxiom 4 expr ind mapProofsLines 
    (ShowImpl (ShowConj a b) b') | b == b' -> helpAxiom 5 expr ind mapProofsLines 
    (ShowImpl a (ShowDisj a' b)) | a == a' -> helpAxiom 6 expr ind mapProofsLines
    (ShowImpl b' (ShowDisj a b)) | b == b' -> helpAxiom 7 expr ind mapProofsLines
    (ShowImpl (ShowImpl a c) (ShowImpl (ShowImpl b c') (ShowImpl (ShowDisj a' b') c''))) | a == a' && b == b' && c == c' && c == c'' -> helpAxiom 8 expr ind mapProofsLines
    (ShowImpl (ShowImpl a b) (ShowImpl (ShowImpl a' (ShowNeg b')) (ShowNeg a''))) | a == a' && a == a'' && b == b' -> helpAxiom 9 expr ind mapProofsLines 
    (ShowImpl a (ShowImpl (ShowNeg a') b)) | a == a' -> helpAxiom 10 expr ind mapProofsLines 
    _ -> Nothing
    
helpAxiom :: Int -> Expr -> Int -> Map.Map Expr Transition ->  Maybe (Map.Map Expr Transition)
helpAxiom numAxiom expr ind mapProofsLines = Just (Map.insert expr (Axiom ind numAxiom) mapProofsLines)

modusPonens :: Map.Map Expr [(Expr, Int)] -> Expr -> Map.Map Expr Transition -> Int -> Maybe (Map.Map Expr Transition)
modusPonens impls expr mapProofsLines index = case Map.lookup expr impls of
    Just exprs -> case filter (\(expr, indx) -> isJust (Map.lookup expr mapProofsLines)) exprs of
                    ((ex, ind) : _) -> 
                        case Map.lookup ex mapProofsLines of
                            (Just (MP lineIndex a b)) -> Just (Map.insert expr (MP index ind lineIndex) mapProofsLines)
                            (Just (Hypot lineIndex num)) -> Just (Map.insert expr (MP index ind lineIndex) mapProofsLines)
                            (Just (Axiom lineIndex num)) -> Just (Map.insert expr (MP index ind lineIndex) mapProofsLines)
                    []       -> Nothing
    Nothing -> Nothing

showAxiom :: Int -> [Expr] -> Expr -> Int -> [String]
showAxiom depth hyps expr ind = case expr of
    (ShowImpl a (ShowImpl b a')) | ind == 1 ->  
        [(showRowProof (depth + 2) (a : b : hyps) a' "[Ax]"),
        (showRowProof (depth + 1) (a : hyps) (ShowImpl b a') "[I->]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl (ShowImpl a b) (ShowImpl (ShowImpl a' (ShowImpl b' c)) (ShowImpl a'' c'))) | ind == 2 ->  
        [(showRowProof (depth + 5)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : a'' : hyps) (ShowImpl a (ShowImpl b c')) "[Ax]"),
        (showRowProof (depth + 5)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : a'' : hyps) a "[Ax]"),
        (showRowProof (depth + 4)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : a'' : hyps) (ShowImpl b c') "[E->]"),
        (showRowProof (depth + 5)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : a'' : hyps) (ShowImpl a b) "[Ax]"),
        (showRowProof (depth + 5)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : a'' : hyps) a "[Ax]"),
        (showRowProof (depth + 4)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : a'' : hyps) b "[E->]"),
        (showRowProof (depth + 3)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : a'' : hyps) c' "[E->]"),
        (showRowProof (depth + 2)  ((ShowImpl a b) : (ShowImpl a' (ShowImpl b' c)) : hyps) (ShowImpl a'' c') "[I->]"),
        (showRowProof (depth + 1)  ((ShowImpl a b): hyps) (ShowImpl (ShowImpl a' (ShowImpl b' c)) (ShowImpl a'' c')) "[I->]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl a (ShowImpl b (ShowConj a' b'))) | ind == 3 -> 
        [(showRowProof (depth + 3) (a : b : hyps) a "[Ax]"),
        (showRowProof (depth + 3) (a : b : hyps) b "[Ax]"),
        (showRowProof (depth + 2) (a : b : hyps) (ShowConj a' b') "[I&]"),
        (showRowProof (depth + 1) (a : hyps) (ShowImpl b (ShowConj a' b')) "[I->]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl (ShowConj a b) a') | ind == 4 -> 
        [(showRowProof (depth + 2) ((ShowConj a b) : hyps) (ShowConj a b) "[Ax]"),
        (showRowProof (depth + 1) ((ShowConj a b) : hyps) a "[El&]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl (ShowConj a b) b') | ind == 5 -> 
        [(showRowProof (depth + 2) ((ShowConj a b) : hyps) (ShowConj a b) "[Ax]"),
        (showRowProof (depth + 1) ((ShowConj a b) : hyps) b "[Er&]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl a (ShowDisj a' b)) | ind == 6 -> 
        [(showRowProof (depth + 2) (a : hyps) a' "[Ax]"),
        (showRowProof (depth + 1) (a : hyps) (ShowDisj a' b) "[Il|]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl b (ShowDisj a b')) | ind == 7 -> 
        [(showRowProof (depth + 2) (b : hyps) b "[Ax]"),
        (showRowProof (depth + 1) (b : hyps) (ShowDisj a b) "[Ir|]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl (ShowImpl a c) (ShowImpl (ShowImpl b  c') (ShowImpl (ShowDisj a' b') c''))) | ind == 8 -> 
        [(showRowProof (depth + 5) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b) : a: hyps) (ShowImpl a c) "[Ax]"),
        (showRowProof (depth + 5) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b) : a: hyps) a "[Ax]"),
        (showRowProof (depth + 4) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b) : a: hyps) c "[E->]"),
        (showRowProof (depth + 5) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b) : b: hyps) (ShowImpl b c) "[Ax]"),
        (showRowProof (depth + 5) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b) : b: hyps) b "[Ax]"),
        (showRowProof (depth + 4) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b) : b: hyps) c "[E->]"),
        (showRowProof (depth + 4) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b): hyps) (ShowDisj a b) "[Ax]"),
        (showRowProof (depth + 3) ((ShowImpl a c) : (ShowImpl b c) : (ShowDisj a b): hyps) c "[E|]"),
        (showRowProof (depth + 2) ((ShowImpl a c) : (ShowImpl b c): hyps) (ShowImpl (ShowDisj a b)  c) "[I->]"),
        (showRowProof (depth + 1) ((ShowImpl a c): hyps) (ShowImpl (ShowImpl b c) (ShowImpl (ShowDisj a b) c)) "[I->]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl (ShowImpl a b) (ShowImpl (ShowImpl a' (ShowNeg b')) (ShowNeg a''))) | ind == 9 -> 
        [(showRowProof (depth + 5) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : a : hyps) (ShowImpl a (ShowNeg b)) "[Ax]"),
        (showRowProof (depth + 5) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : a : hyps) a "[Ax]"),
        (showRowProof (depth + 4) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : a : hyps) (ShowNeg b) "[E->]"),
        (showRowProof (depth + 5) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : a : hyps) (ShowImpl a b) "[Ax]"),
        (showRowProof (depth + 5) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : a : hyps) a "[Ax]"),
        (showRowProof (depth + 4) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : a : hyps) b "[E->]"),
        (showRowProof (depth + 3) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : a : hyps) Bottom "[E->]"),
        (showRowProof (depth + 2) ((ShowImpl a b) : (ShowImpl a' (ShowNeg b')) : hyps) (ShowNeg a'') "[I->]"),
        (showRowProof (depth + 1) ((ShowImpl a b) : hyps) (ShowImpl (ShowImpl a' (ShowNeg b')) (ShowNeg a'')) "[I->]"),
        (showRowProof depth hyps expr "[I->]")]
    (ShowImpl a (ShowImpl (ShowNeg a') b)) | ind == 10 -> 
        [(showRowProof (depth + 5) (a : (ShowNeg a') : Bottom : hyps)  Bottom "[Ax]"),
        (showRowProof (depth + 4) (a : (ShowNeg a') : Bottom : hyps)  b "[E_|_]"),
        (showRowProof (depth + 3) (a : (ShowNeg a') : hyps) (ShowImpl Bottom b) "[I->]"),
        (showRowProof (depth + 4) (a : (ShowNeg a') : hyps) (ShowNeg a) "[Ax]"),
        (showRowProof (depth + 4) (a : (ShowNeg a') : hyps) a "[Ax]"),
        (showRowProof (depth + 3) (a : (ShowNeg a') : hyps) Bottom "[E->]"),
        (showRowProof (depth + 2) (a : (ShowNeg a') : hyps) b "[E->]"),
        (showRowProof (depth + 1) (a : hyps) (ShowImpl (ShowNeg a') b) "[I->]"),
        (showRowProof depth hyps expr "[I->]")]

checkLine :: Map.Map Expr Transition -> Map.Map Expr Int -> Map.Map Expr [(Expr, Int)]  -> Expr -> Int -> Maybe  (Map.Map Expr Transition)
checkLine mapProofsLines hyps impls expr ind = do 
    let getMapProof = Map.lookup expr mapProofsLines
    let getMapHypots = case Map.lookup expr hyps of
                        Just x -> Just (Map.insert expr (Hypot ind x) mapProofsLines)
                        Nothing -> Nothing
    let getMapAxiom = axiom expr ind mapProofsLines
    case (getMapProof, getMapHypots, getMapAxiom) of
        ((Just _), _, _) -> Just mapProofsLines
        (Nothing, (Just x), _) -> Just x
        (Nothing, Nothing, (Just x)) -> Just x
        (Nothing, Nothing, Nothing) -> modusPonens impls expr mapProofsLines ind


checkAllLines :: [Expr] -> Expr -> Map.Map Expr Transition -> Map.Map Expr Int -> Map.Map Expr [(Expr, Int)] -> [Expr] -> Int -> Map.Map Int Expr -> [String]
checkAllLines hypotises toProof mapProofsLines hyps impls exprs ind indexToExprs = case exprs of
    (head: []) | toProof == head -> case checkLine mapProofsLines hyps impls head ind of
                                        (Just mapProofsLines') -> getResult hypotises toProof (Map.insert ind head indexToExprs) mapProofsLines' 0 [] 
                                        Nothing        -> ["Proof is incorrect at line " ++ show (ind + 1)]
    (_ : []) -> ["The proof does not prove the required expression"]
    (expr: exprs) -> case checkLine mapProofsLines hyps impls expr ind of
                (Just mapProofsLines') -> checkAllLines hypotises toProof mapProofsLines' hyps (addToMap expr impls ind) exprs (ind + 1) (Map.insert ind expr indexToExprs)
                Nothing        -> ["Proof is incorrect at line " ++ show (ind + 1)]
