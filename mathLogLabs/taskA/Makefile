SOURCES=Main.hs Lexer.hs Parser.hs
  
.PHONY: all run 

all: delete recompile parser

run:
	./parser

parser: $(SOURCES)
	ghc  -O2 Main.hs -o parser

delete: 
	rm -f *.hi *.o parser 

recompile:
	rm -f Lexer.hs Parser.hs
	alex Lexer.x
	happy Parser.y