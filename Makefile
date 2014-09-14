LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := Microbe
all: parser compiler

parser:
	java -cp $(LIB_ANTLR) org.antlr.v4.Tool -o src $(ANTLR_SCRIPT).g4

compiler:
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java

test:
	touch 'test/temp.myout'
	rm -rf 'test/*.myout'
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
	rm -rf 'test/*.myout'
	rm -rf classes sabeysir
	touch 'src/Microbe.temp'
	rm -rf 'src/Microbe*'

.PHONY: all parser compiler clean test rsubmit submit
