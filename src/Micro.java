/**
 * Created by Sam Abeysiriwardane
 */
import SymbolScope.ScopeNode;
import SymbolScope.SymbolScopeTree;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.IOException;
import java.util.Set;

public class Micro {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("File argument was not provided.");
            return;
        }
        try {
            ANTLRInputStream input = new ANTLRFileStream(args[0]);
            MicrobeParser mParser = parseSource(input);
            if(mParser == null) {
              return;
            }
            SymbolScopeTree sst = mParser.sst;
            printIrCode(sst);
            printTinyCode(sst);
        }
        catch (IOException ex) {
            System.err.print("To be or not to be. The file thought to not to be.");
        }
    }

    private static void printIrCode(SymbolScopeTree sst) {
        // main
        SymbolScopeTree.GlobalScope.generateIrCode();
        SymbolScopeTree.GlobalScope.irCode.PrintIrCode();

        // rest of them
        Set<String> keys = SymbolScopeTree.GlobalScope.children.keySet();
        for(String key: keys) {
            SymbolScopeTree.GlobalScope.children.get(key).generateIrCode();
            SymbolScopeTree.GlobalScope.children.get(key).irCode.PrintIrCode();
        }
    }

    private static void printTinyCode(SymbolScopeTree sst) {
        Set<String> symbolNames = SymbolScopeTree.GlobalScope.symbolTable.keySet();
        for (String symbolName : symbolNames) {
            System.out.println(SymbolScopeTree.GlobalScope.symbolTable.get(symbolName));
        }
        SymbolScopeTree.GlobalScope.irCode.PrintTiny();
        // end global scope code

        // rest of them
        Set<String> keys = SymbolScopeTree.GlobalScope.children.keySet();
        for(String key: keys) {
            SymbolScopeTree.GlobalScope.children.get(key).irCode.PrintTiny();
        }
        System.out.println("end");
    }

    private static MicrobeParser parseSource(ANTLRInputStream input) {
        MicrobeLexer lexer = new MicrobeLexer(input);
        MicrobeParser parser = new MicrobeParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new MicroErrorStrategy());
        try {
            parser.program();
        } catch (ParseCancellationException e) {
            System.err.print("Not accepted");
        }
        return parser;
    }
}
