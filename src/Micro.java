/**
 * Created by Sam Abeysiriwardane
 */
import org.antlr.v4.runtime.*;
import java.io.IOException;
import java.util.HashMap;

public class Micro {
    public static final HashMap<Integer, String> TOKEN_NAMES = new HashMap<Integer, String>();
    static {
        TOKEN_NAMES.put(MicroParser.KEYWORD, "KEYWORD");
        TOKEN_NAMES.put(MicroParser.OPERATOR, "OPERATOR");
        TOKEN_NAMES.put(MicroParser.IDENTIFIER, "IDENTIFIER");
        TOKEN_NAMES.put(MicroParser.FLOATLITERAL, "FLOATLITERAL");
        TOKEN_NAMES.put(MicroParser.INTLITERAL, "INTLITERAL");
        TOKEN_NAMES.put(MicroParser.STRINGLITERAL, "STRINGLITERAL");
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("File argument was not provided.");
        }
        ScanSource(args[0]);
    }

    private static void ScanSource(String filename) {
        try {
            CharStream src = new ANTLRFileStream(filename);
            MicroParser lexer = new MicroParser(src);
            while(true) {
                Token token = lexer.nextToken();
                if(token.getType() == MicroParser.EOF) {
                    break;
                }
                System.out.println("Token Type: " + TOKEN_NAMES.get(token.getType()));
                System.out.println("Value: " + token.getText());
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
