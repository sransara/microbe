LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := MicroParser.g4

all: parser compiler

parser:
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o src $(ANTLR_SCRIPT)

compiler:
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java

clean:
	rm -rf classes 
	rm src/MicroParser.java src/MicroParser.tokens

test:
	py test/testall.py	

.PHONY: all parser compiler clean test
