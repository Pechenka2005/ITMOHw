{-# OPTIONS_GHC -Wno-incomplete-patterns #-}
module Main where
import Lexer
import Parser
import qualified Data.Map as Map
import qualified Data.Set as Set
import Data.List
import Data.Maybe
import GHC.Show

data Line = MP Int Int Int | Hypot Int Int | Axiom Int Int deriving(Eq, Ord)

instance Show Expr where
    show (a :->: b) = "(" ++ show a ++ " -> " ++ show b ++ ")"
    show (a :&: b) = "(" ++ show a ++ " & " ++ show b ++ ")"
    show (a :|: b) = "(" ++ show a ++ " | " ++ show b ++ ")"
    show (Neg expr) = "(" ++ show expr ++ " -> " ++ "_|_" ++ ")"
    show (Var x) = x

solve :: [String] -> [String]
solve (h : rest) = do
    let (hypotises, toProof) = parseHead $ alexScanTokens h
    let n = length hypotises
    let hypots = Map.fromList (zip (reverse hypotises) [1..n])

    let proof = map (parseProof . alexScanTokens) rest

    case checkProof toProof Map.empty hypots Map.empty proof 1 Map.empty of
        (False, _ ,    _,   ind2Expr, ind) -> do
          if ind == -1 then ["The proof does not prove the required expression"]
          else ["Proof is incorrect at line" ++ " " ++ show (ind + 1)]
        (True, proven, impls, ind2Expr, _) -> do
            treeGeneration proven hypotises ind2Expr toProof [] 0
main :: IO()
main = interact (unlines . solve . lines)

treeGeneration :: Map.Map Expr Line -> [Expr] -> Map.Map Int Expr -> Expr -> [String] -> Int -> [String]

treeGeneration e2l hypots i2e ex result height = do
  case e2l `get` ex of
    (MP _ leftInd rightInd) -> do
      treeGeneration e2l hypots i2e (i2e `get` leftInd) [] (height + 1) ++ treeGeneration e2l hypots i2e (i2e `get` rightInd) [] (height + 1) ++ exprToString height hypots ex "[E->]" : result
    (Hypot _ number) ->  hypotToString height hypots ex "[Ax]" : result
    (Axiom _ number) -> do
      axiomToString number ex height hypots

exprToString :: Int -> [Expr] -> Expr -> String -> String
exprToString height exprs expr rule = "[" ++ show height ++ "]" ++ listToString exprs " " ++ " |- " ++ show expr ++ rule

hypotToString :: Int -> [Expr] -> Expr -> String -> String
hypotToString = exprToString

listToString :: [Expr] -> String -> String
listToString [] ", "  = ""
listToString [] " " = ""
listToString (h : list) ", " = show h ++ intercalate ", " (map show list) ++ ", "
listToString (h : list) " " = show h ++ intercalate ", " (map show list)

axiomToString :: Int -> Expr -> Int -> [Expr] -> [String]
axiomToString index expr height hypots = do
  case(index) of
    (1) -> let (a :->: (b :->: a')) = expr in
      [
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show(b) ++ " |- " ++ show(a) ++ "[Ax]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a) ++ " |- " ++ show(b :->: a) ++ "[I->]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show(a :->: (b :->: a)) ++ "[I->]"
      ]

    (2) -> let ((a :->: b) :->: ((a' :->: (b' :->: c)) :->: (a'' :->: c'))) = expr in
      [
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ ", " ++ show(a) ++ " |- " ++ show(a :->: (b :->: c)) ++ "[Ax]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ ", " ++ show(a) ++ " |- " ++ show(c) ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ ", " ++ show(a) ++ " |- " ++ show(b :->: c) ++ "[E->]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ ", " ++ show(a) ++ " |- " ++ show(a :->: b) ++ "[Ax]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ ", " ++ show(a) ++ " |- " ++ show(a) ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ ", " ++ show(a) ++ " |- " ++ show(b) ++ "[E->]",
        "[" ++ show(height + 3) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ ", " ++ show(a) ++ " |- " ++ show(c) ++ "[I->]",
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (b :->: c)) ++ " |- " ++ show(a :->: c) ++ "[I->]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ " |- " ++ show(((a :->: (b :->: c)) :->: (a :->: c))) ++ "[I->]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show((a :->: b) :->: ((a :->: (b :->: c)) :->: (a :->: c))) ++ "[I->]"
      ]

    (3) -> let (a :->: (b :->: (a' :&: b'))) = expr in
      [
        "[" ++ show(height + 3) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show(b) ++ " |- " ++ show(a) ++ "[Ax]",
        "[" ++ show(height + 3) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show(b) ++ " |- " ++ show(b) ++ "[Ax]",
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show(b) ++ " |- " ++ show(a :&: b) ++ "[I&]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a) ++ " |- " ++ show(b :->: (a :&: b)) ++ "[I->]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show(a :->: (b :->: (a :&: b))) ++ "[I->]"
      ]

    (4) -> let ((a :&: b) :->: a') = expr in
      [
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a :&: b) ++ " |- " ++ show(a :&: b) ++ "[Ax]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a :&: b) ++ " |- " ++ show(a) ++ "[El&]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show((a :&: b) :->: a) ++ "[I->]"
      ]
    (5) -> let ((a :&: b) :->: b') = expr in
      [
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a :&: b) ++ " |- " ++ show(a :&: b) ++ "[Ax]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a :&: b) ++ " |- " ++ show(b) ++ "[Er&]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show((a :&: b) :->: b) ++ "[I->]"
      ]
    (6) -> let (a :->: (a' :|: b)) = expr in
      [
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show (a) ++ " |- " ++ show(a) ++ "[Ax]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show (a) ++ " |- " ++ show(a :|: b) ++ "[Il|]" ,
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show(a :->: (a :|: b)) ++ "[I->]"
      ]

    (7) -> let (b' :->: (a :|: b)) = expr in
      [
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show (b) ++ " |- " ++ show(b) ++ "[Ax]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show (b) ++ " |- " ++ show(a :|: b) ++ "[Ir|]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show(b :->: (a :|: b)) ++ "[I->]"
      ]

    (8) -> let ((a :->: c) :->: ((b :->: c') :->: ((a' :|: b') :->: c''))) = expr in
      [
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ ", " ++ show(a) ++ " |- " ++ show(a :->: c) ++ "[Ax]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ ", " ++ show(a) ++ " |- " ++ show(a) ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ ", " ++ show(a) ++ " |- " ++ show(c) ++ "[E->]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ ", " ++ show(b) ++ " |- " ++ show(b :->: c) ++ "[Ax]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ ", " ++ show(b) ++ " |- " ++ show(b) ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ ", " ++ show(b) ++ " |- " ++ show(c) ++ "[E->]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ " |- " ++ show(a :|: b) ++ "[Ax]",
        "[" ++ show(height + 3) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ ", " ++ show(a :|: b) ++ " |- " ++ show(c) ++ "[E|]",
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ ", " ++ show(b :->: c) ++ " |- " ++ show((a :|: b) :->: c) ++ "[I->]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a :->: c) ++ " |- " ++ show((b :->: c) :->: ((a :|: b) :->: c)) ++ "[I->]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show((a :->: c) :->: ((b :->: c) :->: ((a :|: b) :->: c))) ++ "[I->]"
      ]

    (9) -> let ((a :->: b) :->: ((a' :->: Neg (b')) :->: Neg (a''))) = expr in
      [
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ ", " ++ show(a) ++ " |- " ++ show(a :->: (Neg b)) ++ "[Ax]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ ", " ++ show(a) ++ " |- " ++ show(a) ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ ", " ++ show(a) ++ " |- " ++ show(Neg b) ++ "[E->]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ ", " ++ show(a) ++ " |- " ++ show(a :->: b) ++ "[Ax]",
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ ", " ++ show(a) ++ " |- " ++ show(a) ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ ", " ++ show(a) ++ " |- " ++ show(b) ++ "[E->]",
        "[" ++ show(height + 3) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ ", " ++ show(a) ++ " |- " ++ "_|_" ++ "[E->]",
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++ ", " ++ show(a :->: (Neg b)) ++ " |- " ++ show(Neg a) ++ "[I->]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a :->: b) ++  " |- " ++ show((a :->: (Neg b)) :->: (Neg a)) ++ "[I->]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show((a :->: b) :->: ((a :->: (Neg b)) :->: (Neg a))) ++ "[I->]"
      ]

    (10) -> let (a :->: (Neg(a') :->: b)) = expr in
      [
        "[" ++ show(height + 5) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show((Neg a)) ++ ", " ++ "_|_" ++ " |- " ++ "_|_" ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show((Neg a)) ++ ", " ++ "_|_" ++ " |- " ++ show(b) ++ "[E_|_]",
        "[" ++ show(height + 3) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show((Neg a)) ++ " |- " ++ "( _|_ -> " ++ show(b) ++ ")" ++ "[I->]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show((Neg a)) ++ " |- " ++ "_|_" ++ "[Ax]",
        "[" ++ show(height + 4) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show((Neg a)) ++ " |- " ++ show(Neg a) ++ "[Ax]",
        "[" ++ show(height + 3) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show((Neg a)) ++ " |- " ++ "_|_" ++ "[E->]",
        "[" ++ show(height + 2) ++ "]" ++ listToString hypots ", " ++ show(a) ++ ", " ++ show((Neg a)) ++ " |- " ++ show(b) ++ "[E->]",
        "[" ++ show(height + 1) ++ "]" ++ listToString hypots ", " ++ show(a) ++ " |- " ++ show((Neg a) :->: b) ++ "[I->]",
        "[" ++ show(height + 0) ++ "]" ++ listToString hypots " " ++ " |- " ++ show(a :->: ((Neg a) :->: b)) ++ "[I->]"
      ]


axiom :: Expr -> Int -> Map.Map Expr Line -> Maybe (Map.Map Expr Line)
axiom expr@(a :->: (b :->: a')) ind proven
 | a == a' = axiom' ind expr proven (Just 1)
axiom expr@((a :->: b) :->: ((a' :->: (b' :->: c)) :->: (a'' :->: c'))) ind proven
 | a == a' && a == a'' && b == b' && c == c' = axiom' ind expr proven (Just 2)
axiom expr@((a :&: b) :->: a') ind proven
 | a == a' = axiom' ind expr proven (Just 4)
axiom expr@((a :&: b) :->: b') ind proven
 | b == b' = axiom' ind expr proven (Just 5)
axiom  expr@(a :->: (b :->: (a' :&: b'))) ind proven
 | a == a' && b == b' = axiom' ind expr proven (Just 3)
axiom  expr@(a :->: (a' :|: b)) ind proven
 | a == a' = axiom' ind expr proven (Just 6)
axiom  expr@(b' :->: (a :|: b)) ind proven
 | b == b' = axiom' ind expr proven (Just 7)
axiom  expr@((a :->: c) :->: ((b :->: c') :->: ((a' :|: b') :->: c''))) ind proven
 | a == a' && b == b' && c == c' && c == c'' = axiom' ind expr proven (Just 8)
axiom  expr@((a :->: b) :->: ((a' :->: Neg (b')) :->: Neg (a''))) ind proven
 | a == a' && a == a'' && b == b' = axiom' ind expr proven (Just 9)
axiom expr@(a :->: (Neg(a') :->: b)) ind proven
 | a == a' = axiom' ind expr proven (Just 10)
axiom _ _ _ = Nothing

axiom' :: Int -> Expr -> Map.Map Expr Line -> Maybe Int -> Maybe (Map.Map Expr Line)
axiom' _ _ _ Nothing = Nothing
axiom' ind expr proven (Just num) = Just (Map.insert expr (Axiom ind num) proven)

getAdd :: Map.Map Expr [(Expr, Int)]-> Expr -> Expr -> Int -> Map.Map Expr [(Expr, Int)]
getAdd map k v ind = case (Map.lookup k map) of
        Just vs -> Map.insert k ((v, ind) : vs) map
        Nothing -> Map.insert k [(v, ind)] map

addImpl :: Expr -> Map.Map Expr [(Expr, Int)] -> Int -> Map.Map Expr [(Expr, Int)]
addImpl (l :->: r) impls ind = getAdd impls r l ind
addImpl _ impls _            = impls

isHypot :: Map.Map Expr Int -> Expr -> Map.Map Expr Line -> Int -> Maybe (Map.Map Expr Line)
isHypot hypots expr proven ind = case (Map.lookup expr hypots) of
    Just x -> Just (Map.insert expr (Hypot ind x) proven)
    Nothing -> Nothing

checkModusPonens' :: [(Expr, Int)] -> Map.Map Expr Line -> Maybe (Int, Int)
checkModusPonens' exprs proven = case (filter (\(expr, indx) -> ((Map.lookup expr proven) /= Nothing)) exprs) of
    ((ex, ind) : _) -> case (Map.lookup ex proven) of
        (Just (MP lineIndex a b)) -> Just (ind, lineIndex)
        (Just (Hypot lineIndex num)) -> Just (ind, lineIndex)
        (Just (Axiom lineIndex num)) -> Just (ind, lineIndex)
    []       -> Nothing

checkModusPonens :: Map.Map Expr [(Expr, Int)] -> Expr -> Map.Map Expr Line -> Int -> Maybe (Map.Map Expr Line)
checkModusPonens impls expr proven ind = case (Map.lookup expr impls) of
    Just lefts -> case (checkModusPonens' lefts proven) of
        (Just (ind1, ind2)) -> Just (Map.insert expr (MP ind ind1 ind2) proven)
        Nothing -> Nothing
    Nothing -> Nothing

verifyLine :: Map.Map Expr Line -> Map.Map Expr Int -> Map.Map Expr [(Expr, Int)]  -> Expr -> Int -> Maybe  (Map.Map Expr Line)
verifyLine proven hypots impls expr ind = case (Map.lookup expr proven) of
    (Just _) -> (Just proven)
    Nothing -> case isHypot hypots expr proven ind of
        (Just proven') -> (Just proven')
        Nothing        -> case (axiom expr ind proven) of
            (Just proven') -> (Just proven')
            Nothing -> checkModusPonens impls expr proven ind

checkProof :: Expr -> Map.Map Expr Line -> Map.Map Expr Int -> Map.Map Expr [(Expr, Int)] -> [Expr] -> Int -> (Map.Map Int Expr) -> (Bool, Map.Map Expr Line, Map.Map Expr [(Expr, Int)], Map.Map Int Expr, Int)
checkProof toProof proven hypots impls (expr : []) ind  ind2Expr = case (toProof == expr) of
    True -> case (verifyLine proven hypots impls expr ind) of
        (Just proven') -> (True,  proven', impls, (Map.insert ind expr ind2Expr), ind)
        Nothing        -> (False, proven, impls, ind2Expr, ind)
    False -> (False, proven, impls, ind2Expr, -1)
checkProof toProof proven hypots impls (expr : exprs) ind ind2Expr =
    case (verifyLine proven hypots impls expr ind) of
        (Just proven') -> checkProof toProof proven' hypots (addImpl expr impls ind) exprs (ind + 1) (Map.insert ind expr ind2Expr)
        Nothing        -> (False, proven, impls, ind2Expr, ind)

get m k = case (Map.lookup k m) of
    (Just v) -> v
    Nothing  -> error (show k)