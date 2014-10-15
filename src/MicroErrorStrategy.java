import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.*;


public class MicroErrorStrategy extends DefaultErrorStrategy {
    // Make no judgements this was a project step requirement,
    // and error reporting is not a big deal at this point of development
    @Override
    public void reportError(Parser recognizer, RecognitionException e)
    {
    throw new ParseCancellationException(e);
    }

    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException
    {
    InputMismatchException e = new InputMismatchException(recognizer);
    throw new ParseCancellationException(e);
    }

    @Override
    public void sync(Parser recognizer) { }
}
