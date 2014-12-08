/**
 * Created by Sam Abeysiriwardane
 */

import SymbolScope.SymbolScopeTree;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
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
        SymbolScopeTree.GlobalScope.generateIrCode();
        SymbolScopeTree.GlobalScope.printIrCode();
    }

    private static void printTinyCode(SymbolScopeTree sst) {
        SymbolScopeTree.GlobalScope.printTinyCode();
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
