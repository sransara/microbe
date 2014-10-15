/**
 * Created by Sam Abeysiriwardane
 */
import AST.AstNode;
import IR.IrCode;
import IR.IrCodeState;
import SymbolScope.SymbolScopeTree;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
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
            IrCodeState.CURRENT_SCOPE = sst.GlobalScope.children.get("main");
            IrCodeState.CURRENT_SCOPE.generateIrCode();
            for(IrCode n : IrCodeState.CURRENT_SCOPE.irCodeList) {
                n.PrintIrCode();
            }

            Set<String> symbolNames = sst.GlobalScope.symbolTable.keySet();
            for (String symbolName : symbolNames) {
                System.out.println(sst.GlobalScope.symbolTable.get(symbolName));
            }
            for(IrCode n : IrCodeState.CURRENT_SCOPE.irCodeList) {
                n.PrintAssembly();
            }
            System.out.println("sys halt");
            System.out.println("end");
        }
        catch (IOException ex) {
            System.err.print("To be or not to be. The file thought to not to be.");
            return;
        }
    }

    private static MicrobeParser parseSource(ANTLRInputStream input) {
        MicrobeLexer lexer = new MicrobeLexer(input);
        MicrobeParser parser = new MicrobeParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new MicroErrorStrategy());

        ParseTree tree = null;
        Boolean success = true;
        try {
            tree = parser.program();
        } catch (ParseCancellationException e) {
            System.err.print("Not accepted");
        }
        return parser;
    }
}
