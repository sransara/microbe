LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Microbe
PATH_SEP := ;
all: parser compiler

parser:
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o src $(ANTLR_SCRIPT).g4

compiler:
	rm -rf classes
	mkdir classes
	javac -d classes src/SymbolScope/*.java src/AST/*.java src/IR/*.java src/Nucleus/*.java
	javac -cp $(LIB_ANTLR)$(PATH_SEP)classes$(PATH_SEP) -d classes src/*.java

test:
	touch 'test/temp.myout'
	touch 'test/temp.out'
	rm -f test/*.myout
	rm -f test/*.out
	python test/testall.py

rsubmit:
	make clean
	mkdir sabeysir
	cp tosubmit_makefile sabeysir/Makefile
	cp $(ANTLR_SCRIPT).g4 sabeysir/
	cp -r src sabeysir/
	cp -r lib sabeysir/
	cp -r test sabeysir/
	@echo "Folder sabeysir is ready to be submitted"

submit:
	@sh submit.bash

clean:
	touch 'test/temp.myout'
	touch 'test/temp.out'
	touch 'test/temp.m.out'
	touch 'test/temp.t.out'
	rm -f test/*.myout
	rm -f test/*.out
	rm -rf test/*.t.out
	rm -rf test/*.m.out
	rm -rf classes sabeysir
	touch 'src/Microbe.temp'
	rm -f src/Microbe*

.PHONY: all parser compiler clean test rsubmit submit
