LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := MicroScanner

all: parser compiler

parser:
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o src $(ANTLR_SCRIPT).g4

compiler:
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java

test:
	rm -rf test/*.myout
	py test/testall.py

submit:
	make clean
	mkdir sabeysir
	cp Makefile.s sabeysir/Makefile
	cp $(ANTLR_SCRIPT).g4 sabeysir/
	cp -r src sabeysir/
	cp -r lib sabeysir/
	echo "Folder sabeysir is ready to be submitted"

clean:
	rm -rf classes sabeysir
	rm -f src/$(ANTLR_SCRIPT).java src/$(ANTLR_SCRIPT).tokens

.PHONY: all parser compiler clean test submit
