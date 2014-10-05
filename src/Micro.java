/**
 * Created by Sam Abeysiriwardane
 */
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;

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
            sst.printTree("GLOBAL", sst.globalScope);
        }
        catch (IOException ex) {
            System.err.print("To be or not to be. The file thought to not to be.");
            return;
        }
    }

    private static MicrobeParser parseSource(ANTLRInputStream input) {
        MicrobeLexer lexer = new MicrobeLexer(input);
        MicrobeParser parser = new MicrobeParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new CustomErrorStrategy());

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
