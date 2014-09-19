/**
 * Created by Sam Abeysiriwardane
 */
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.HashMap;

public class Micro {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("File argument was not provided.");
            return;
        }
        try {
            ANTLRInputStream input = new ANTLRFileStream(args[0]);
            ParseSource(input);
        }
        catch (IOException ex) {
            System.err.print("To be or not to be. The file thought to not to be.");
            ex.printStackTrace();
            return;
        }
    }

    private static ParseTree ParseSource(ANTLRInputStream input) {
        MicrobeLexer lexer = new MicrobeLexer(input);
        MicrobeParser parser = new MicrobeParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new CustomErrorStrategy());

        ParseTree tree = null;
        Boolean success = true;
        try {
            tree = parser.program();
        } catch (ParseCancellationException e) {
            success = false;
            System.out.print("Not accepted");
        }
        if (success) {
            System.out.print("Accepted");
        }
        return tree;
    }
}
